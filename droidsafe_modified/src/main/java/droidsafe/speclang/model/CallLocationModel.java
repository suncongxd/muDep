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

package droidsafe.speclang.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import soot.MethodOrMethodContext;
import soot.SootMethod;
import soot.jimple.Stmt;
import soot.jimple.toolkits.callgraph.Edge;
import droidsafe.utils.SootUtils;
import droidsafe.utils.SourceLocationTag;

/**
 * A CodeLocationModel that represents the source line of a call edge in the PAG.
 * 
 * @author Limei Gilham (gilham@kestrel.edu)
 *
 */
public class CallLocationModel extends CodeLocationModel {

    /**
     * Serialization id for class.
     */
    private static final long serialVersionUID = -2677646576088927559L;

    /**
     * Standard logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CallLocationModel.class);

    /**
     * Signature of the target method for the underlying call edge.
     */
    private String targetMethodSig = "";

    /**
     * A map from call edges to their associated source line models.
     */
    private static Map<Edge, CallLocationModel> map = new HashMap<Edge, CallLocationModel>();
    
    private Set<String> infoKinds = null;
    
    /**
     * Create a call edge source line model and compute its fields.
     * 
     * @param clz name of the class where the new expression for the AllocNode resides in
     * @param line the line number for the new expression corresponding to the underlying AllocNode
     * @param stmt the underlying call edge
     */
    private CallLocationModel(String clz, int line, Edge edge) {
        super(clz, line);
                
        SootMethod targetMethod = edge.tgt();
            /*
            String subSig = targetMethod.getSubSignature();
            int namePos = subSig.indexOf(targetMethod.getName());
            targetMethodSig = targetMethod.getDeclaringClass().getName() + "." + subSig.substring(namePos) + ": " + targetMethod.getReturnType();
            */
        String sig = targetMethod.getSignature();
        targetMethodSig = sig.substring(1, sig.length() - 1);        
    }

    /**
     * Create a call edge source line model and compute its fields.
     * 
     * @param srcLoc the source location tag for the call expression corresponding to the underlying call edge
     * @param callEdge the underlying call edge
     */
    public CallLocationModel(SourceLocationTag srcLoc, Edge callEdge) {
        this(srcLoc.getClz(), srcLoc.getLine(), callEdge);
    }

    /**
     * Return the signature of the target method for the underlying call edge.
     */
    public String getTargetMethodSig() {
        return targetMethodSig;
    }

    public Set<String> getInfoKinds() {
        return (infoKinds == null) ? Collections.EMPTY_SET : infoKinds;
    }
    
    
    public void setInfoKinds(Set<String> kinds) {
        infoKinds = kinds;
    }
    
    @Override
    public String toString() {
        String clz = getClz();
        int line = getLine();
        return targetMethodSig + " - " + clz + " line: " + line;
    }

    /**
     * Clear the static map from call edges to their associated source line models.
     */
    public static void reset() {
        map.clear() ;
    }

    /**
     * Return the source line model for the give call edge. Return empty set of any problems.
     */
    public static CallLocationModel get(Edge edge) {
        CallLocationModel line = map.get(edge);
        if (line == null) {
            // LWG
            SourceLocationTag srcLoc =  SootUtils.getSourceLocation(edge.srcStmt());
            if (srcLoc == null) {
                logger.debug("Cannot find source location for statement: {}", edge.srcStmt());
                return null;
            }
            line = new CallLocationModel(srcLoc, edge);
            map.put(edge, line);
        }
        return line;
        
    }

}
