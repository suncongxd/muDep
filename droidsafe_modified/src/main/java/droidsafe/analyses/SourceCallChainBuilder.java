/*
 * Copyright (C) 2015,  Massachusetts Institute of Technology
 * 
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 * Please email droidsafe@lists.csail.mit.edu if you need additional
 * information or have any questions.
 */

package droidsafe.analyses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import soot.MethodOrMethodContext;
import soot.SootClass;
import soot.SootMethod;
import soot.jimple.Stmt;
import soot.jimple.StmtBody;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.callgraph.Edge;
import droidsafe.analyses.cg.StmtEdge;
import droidsafe.analyses.cg.collapsedcg.CollaspedCallGraph;
import droidsafe.analyses.pta.PTABridge;
import droidsafe.android.app.Project;
import droidsafe.android.system.API;

/**
 * Builds a source call tree rooted at a user entry point by processing the call chain
 * at each call statement in the body of the entry point and then processing the call targets
 * recursively.
 *
 */
public class SourceCallChainBuilder {

    /** Logging field */
    private static final Logger logger = LoggerFactory.getLogger(SourceCallChainBuilder.class);

    /** timer so we can timeout after a reasonable amount of time **/
    private StopWatch timer = new StopWatch();

    /** Timeout (milliseconds).  The output will be incomplete **/
    private int timeout;

    /** True if we should look for callbacks through system calls **/
    private boolean process_callbacks;

    /** Rather than creating normal output, dump the entire call graph **/
    private  boolean dump_all_calls = false;  

    /** just ignore duplicate methods rather than merging call chains **/
    private boolean ignore_dup_methods = false;

    /** Call stack to detect recursion **/
    Stack<SootMethod> stack = new Stack<SootMethod>();

    /** set of method names known not to have callbacks **/
    private static HashSet<String> no_callback_methods = new HashSet<String>();

    static {
        no_callback_methods.add ("valueOf");
        no_callback_methods.add ("toString");
    }

    /**
     * Create a SourceCallChainBuilder with specified timeout and process_callbacks flag.
     * 
     * @param timeout - timeout in milliseconds
     * @param process_callbacks - true if we should look for callbacks through system calls
     */
    public SourceCallChainBuilder (int timeout, boolean process_callbacks) {
        this.timeout = timeout;
        this.process_callbacks = process_callbacks;
    }

    /**
     * Process the call chain and returns a CallChainInfo that 
     * describes this call and any call that it makes.
     * Ignores any calls that are within the API. Ignores recursive
     * calls and static initializers.  Terminates on system calls
     * that do not contain callbacks.
     * 
     * @param s       - Stmt that called mc
     * @param method      - Method to start at
     */
    public  SourceCallChainInfo process_call_chain (Stmt s, SootMethod method) { 

        if (is_terminal (method))
            return new SourceCallChainInfo (method, s, "syscall");

        if (isSourceMethod(method)) {

            SourceCallChainInfo cci = new SourceCallChainInfo (method, s, "call-chain");
            CallGraph cg = PTABridge.v().getCallGraph();

            Set<SootMethod> processed_methods = new HashSet<SootMethod>();
            List<SourceCallChainInfo> calls = new ArrayList<SourceCallChainInfo>();
            StmtBody stmtBody = (StmtBody)method.getActiveBody();
            Iterator stmtIt = stmtBody.getUnits().snapshotIterator();
            while (stmtIt.hasNext()) {
                if (timeout())
                    break;
                Stmt stmt = (Stmt)stmtIt.next();
                if (stmt.containsInvokeExpr()) {
                	// iterate over the context insensitive edges in the collapsed call graph
                    for (StmtEdge<SootMethod> edge : CollaspedCallGraph.v().getTargetsForStmt(stmt)) {
                        SootMethod callee = edge.getV2();
                        if (ignore_dup_methods) {
                            if (processed_methods.contains(callee)) {
                                logger.info ("pcc: method {}, duplicate callee {}", method, callee);
                                continue;
                            }
                            processed_methods.add (callee);
                        }
                        boolean print_callee = !(is_system(method) && is_system (callee));
                        if (callee.toString().contains ("<clinit>")) {
                            continue;
                        } else if (stack.contains(callee)) {
                            // ignore recursive  call
                        } else { // normal call 
                            stack.push (callee);
                            SourceCallChainInfo calleeCci = process_call_chain (stmt, callee);
                            stack.pop();
                            if (calleeCci != null) {
                                if (print_callee) {
                                    calls.add (calleeCci);
                                } else { // skip callee and just add what it calls
                                    if (ignore_dup_methods) {
                                        for (SourceCallChainInfo callee_call : calleeCci.contents) {
                                            if (!processed_methods.contains (callee_call.method)) {
                                                calls.add (callee_call);
                                                processed_methods.add(callee_call.method);
                                            }
                                        }
                                    } else {
                                        calls.addAll (Arrays.asList(calleeCci.contents));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            cci.contents = calls.toArray(cci.contents);
            if (ignore_dup_methods)
                Arrays.sort(cci.contents);
            else
                cci.merge_contents();
            return cci;
        }
        return null;
    }

    /**
     * Returns true if the specified method should terminate the call graph
     * The call graph is terminated on system calls that do not have callbacks
     * into application code.
     * @param m
     * @return
     */
    public  boolean is_terminal (MethodOrMethodContext mc) {
        Stack<SootMethod> sys_stack = new Stack<SootMethod>();
        boolean result;

        if (!is_system(mc.method()))
            result = false;
        else { // calling system method
            if (!process_callbacks) 
                result = true;
            else { // looking for callbacks
                if ((stack.size() > 0) && is_system(stack.peek()))
                    result = !calls_app_method (mc, sys_stack);
                else { // user calling system, make sure marked IPC
                    if (can_have_callbacks (mc.method()))
                        result = !calls_app_method (mc, sys_stack);
                    else
                        result = true;
                }
            }
        }

        logger.info ("  {} terminal = {}", mc.method(), result);
        return result;
    }

    /**
     * Returns true if the specified method is a method in a source class and it should be included in the source
     * call graph computation. Returns false for droidsafe-generated methods and most compiler-generated methods.
     * @param method
     * @return
     */
    private boolean isSourceMethod(SootMethod method) {
        // include synthetic methods e.g. doInBackground(Object[]) which call the real method defined by the user
        if (!method.isConcrete() || !method.hasActiveBody()) // SootUtils.isSynthetic(method) || 
            return false;
        SootClass cls = method.getDeclaringClass();
        if (!Project.v().isSrcClass(cls))
            return false;
        String methodName = method.getName();
        if (methodName.equals("DS__FAKE__CALLBACKS__") || 
                methodName.startsWith("OBJECTGETCLASS_TO_CLASSCONSTANT_LOCAL") /* || 
    			methodName.matches(".*\\$\\d+.*")*/)
            return false;
        return true;
    }

    /** Returns true if an app method is called directly or indirectly from mc **/
    private boolean calls_app_method (MethodOrMethodContext mc, Stack<SootMethod> stack) {
        CallGraph cg = PTABridge.v().getCallGraph();
        logger.info("  cam: entering iterator, stack = {}", stack);

        if (no_callback_methods.contains (mc.method().getName()))
            return false;
        if (system_depth (stack) > 5) {
            no_callback_methods.add (mc.method().getName());
            return false;
        }
        int ii = 0;
        for (Iterator<Edge> tit = cg.edgesOutOf(mc); tit.hasNext(); ) {
            Edge e = tit.next();
            SootMethod m = e.getTgt().method();
            logger.info("  cam: considering method {} ({})", m, ii++);

            if (no_callback_methods.contains (m.getName()))
                continue;
            if (m.toString().contains("<clinit>"))
                continue;
            if (!is_system (m)) {
                logger.info ("  cam: {} is not a system call");
                return true;
            }
            if (!stack.contains(m)) {
                stack.push(m);
                boolean result = calls_app_method (e.getTgt(), stack);
                stack.pop();
                if (result) {
                    logger.info ("  cam: {} true", m);
                    return true;
                }
            }
        }
        logger.info("  cam: {} is terminal", mc.method());
        no_callback_methods.add (mc.method().getName());
        return false;
    }


    /** Returns the number of calls back to the last non-system call **/
    private static int system_depth (Stack<SootMethod> stack) {
        for (int ii = stack.size()-1; ii > 0; ii--) {
            SootMethod m = stack.get(ii);
            if (!is_system(m))
                return stack.size() - ii;
        }
        return stack.size();
    }

    /** Returns true if the specified method is a system (android or java) class **/
    public static boolean is_system (SootMethod m) {
        Project p = Project.v();
        SootClass c = m.getDeclaringClass();
        return !p.isSrcClass(c) && !p.isLibClass(c);
    }  

    /**
     * Returns true if the specified system method can possibly
     * have callbacks (according to our annotations).  In this case
     * we are primarily interested in IPC callbacks (to other threads)
     * and not indirect callbacks via the GUI (eg. setting up various
     * listeners)
     */
    public boolean can_have_callbacks (SootMethod m) {
        logger.info ("method {} ipcsink = {}, ipcmethod = {}", m, 
            API.v().isIPCSink(m), API.v().isIPCMethod(m));
        return (API.v().isIPCSink(m) || API.v().isIPCMethod(m));
    }
    /** Returns true if we have timed out **/
    public boolean timeout() {
        return (timer.getTime() > timeout);
    }
}
