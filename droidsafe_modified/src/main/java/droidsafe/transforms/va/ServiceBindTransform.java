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

package droidsafe.transforms.va;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import droidsafe.analyses.pta.PTABridge;
import droidsafe.analyses.value.ImplicitIntentModel;
import droidsafe.analyses.value.IntentUtils;
import droidsafe.analyses.value.ResolvedExplicitIntent;
import droidsafe.analyses.value.UnresolvedIntent;
import droidsafe.analyses.value.VAModel;
import droidsafe.analyses.value.VAUtils;
import droidsafe.analyses.value.ValueAnalysis;
import droidsafe.android.app.Harness;
import droidsafe.android.app.Hierarchy;
import droidsafe.android.app.Project;
import droidsafe.android.system.AndroidComponents;
import droidsafe.reports.AnalysisReport;
import droidsafe.reports.ICCMap;
import droidsafe.stats.IntentResolutionStats;
import droidsafe.utils.SootUtils;
import soot.Body;
import soot.Local;
import soot.RefType;
import soot.Scene;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.SootMethodRef;
import soot.Type;
import soot.Value;
import soot.VoidType;
import soot.jimple.AssignStmt;
import soot.jimple.InvokeExpr;
import soot.jimple.Jimple;
import soot.jimple.Stmt;
import soot.jimple.toolkits.pta.IAllocNode;

public class ServiceBindTransform implements VATransform {
    private final static Logger logger = LoggerFactory.getLogger(ServiceBindTransform.class);
    private Set<String> sigsOfInvokesToTransform;
    private Set<Stmt> modified = new HashSet<Stmt>();
    private final String DS_ONBIND_SUBSIG = "void droidSafeOnBind(android.content.Intent,android.content.ServiceConnection)";
    /** used for locals to create a unique local */
    private int uniqueID = 0;

    public ServiceBindTransform() {

    }

    @Override
    public void tranformsInvoke(SootMethod containingMthd, SootMethod callee,
                                InvokeExpr invokeExpr, Stmt stmt, Body body) {

        if(!Project.v().isSrcClass(containingMthd.getDeclaringClass())){
            return;
        }

        if (modified.contains(stmt)) {
            return;
        }
        modified.add(stmt);

        IntentResolutionStats.v().intentCalls++;
        
        logger.info("Found call to transform {} in {}\n", stmt, containingMthd);
        InvokeExpr invoke = stmt.getInvokeExpr();

        //add call to droidSafeOnBind(Intent, ServiceConnection)
        //for all service destinations from the intent 
        //for the services created in the harness
        Set<? extends IAllocNode> intentNodes = PTABridge.v().getPTSetIns(invoke.getArg(0));
        
        boolean allIntentsNodeResolved = true;
        boolean noInAppTarget = false;
        Set<SootField> inAppTargets = new HashSet<SootField>();
        
        for (IAllocNode intentNode : intentNodes) { 
            
            IntentResolutionStats.v().intentObjects++;
            
            Set<SootField> targetHarnessFields = IntentUtils.v().getIntentTargetHarnessFields(AndroidComponents.SERVICE, stmt, callee, intentNode);
            
            //if we don't have a resolved explicit intent or if we have a resolved explicit intent with 
            //no in app target, then we don't have all resolved in app explicit
            if (IntentUtils.v().getIntentModel(intentNode) instanceof UnresolvedIntent) {
                allIntentsNodeResolved = false;
            } else {
                //resolved
                if (IntentUtils.v().getIntentModel(intentNode) instanceof ImplicitIntentModel) {
                    IntentResolutionStats.v().resolvedImplictIntents++;
                } else {
                    IntentResolutionStats.v().resolvedExplicitIntents++;
                }
                if (targetHarnessFields.isEmpty()) {
                    noInAppTarget = true;
                }
            }
            
            inAppTargets.addAll(targetHarnessFields);
            
            for (SootField serviceFld : targetHarnessFields) {
                if (serviceFld == null || !(serviceFld.getType() instanceof RefType))
                    continue;           

                SootClass serviceClz = ((RefType)serviceFld.getType()).getSootClass();

                if (!Hierarchy.inheritsFromAndroidService(serviceClz))
                    continue;

                logger.info("Adding call to droidSafeOnBind() with target {}", serviceClz);

                //local = field from harness 
                Local fieldLocal = Jimple.v().newLocal("_$SERVICE_LOCAL" + uniqueID++, serviceFld.getType());
                body.getLocals().add(fieldLocal);
                AssignStmt localAssign = Jimple.v().newAssignStmt(
                    fieldLocal, 
                    Jimple.v().newStaticFieldRef(serviceFld.makeRef()));

                body.getUnits().insertAfter(localAssign, stmt);

                //local.droidSafeOnBind(invoke.arg(0), invoke.arg(1))
                SootMethodRef dsOnBind = makeDSOnbindRef(serviceClz);

                List<Value> params = new LinkedList<Value>();

                params.add(invoke.getArg(0));
                params.add(invoke.getArg(1));

                Stmt call = Jimple.v().newInvokeStmt(
                    Jimple.v().newVirtualInvokeExpr(fieldLocal, dsOnBind, params));

                //insert the statement
                body.getUnits().insertAfter(call, localAssign);

                //report in ICCMap
                try {
                    SootMethod onBindMethod = Scene.v().getSootClass("android.app.Service").getMethodByName("onBind");
                    SootMethod resolved = Scene.v().getActiveHierarchy().resolveConcreteDispatch(serviceClz, onBindMethod);
                    ICCMap.v().addInfo(containingMthd.getDeclaringClass(), serviceClz, stmt, resolved);
                } catch (Exception e) {
                    logger.debug("Error resolving onBind.", e);
                    AnalysisReport.v().addEntry("Service bind() with unresolved Intent", stmt, AnalysisReport.Level.HIGH);
                }                   
            }
        }
        
        if (allIntentsNodeResolved) {
            IntentResolutionStats.v().callsWithResolvedIntents++;
            if (noInAppTarget) {
                IntentResolutionStats.v().callsTargetNotInApp++;
                AnalysisReport.v().addEntry("Service bind() with no in-app component target", stmt, AnalysisReport.Level.HIGH);
            }
            IntentResolutionStats.v().inAppComponentsTotalTargets += inAppTargets.size();
        } else {
        	AnalysisReport.v().addEntry("Service bind() with unresolved Intent", stmt, AnalysisReport.Level.HIGH);
            IntentResolutionStats.v().callsWithUnresolvedIntent++;
        }
    }

    private SootMethodRef makeDSOnbindRef(SootClass serC) {
        List<Type> params = new LinkedList<Type>();
        params.add(RefType.v("android.content.Intent"));
        params.add(RefType.v("android.content.ServiceConnection"));

        return Scene.v().makeMethodRef(serC, "droidSafeOnBind", params, VoidType.v(), false);
    }

    @Override
    public Set<String> sigsOfInvokesToTransform() {
        // context impl
        // context
        // context wrapper

        if (sigsOfInvokesToTransform == null) {
            sigsOfInvokesToTransform = new HashSet<String>();
            sigsOfInvokesToTransform.add("<android.content.Context: boolean bindService(android.content.Intent,android.content.ServiceConnection,int)>");
            sigsOfInvokesToTransform.add("<android.content.ContextWrapper: boolean bindService(android.content.Intent,android.content.ServiceConnection,int)>");
            sigsOfInvokesToTransform.add("<android.app.ContextImpl: boolean bindService(android.content.Intent,android.content.ServiceConnection,int)>");
        }
        return sigsOfInvokesToTransform;
    }

}
