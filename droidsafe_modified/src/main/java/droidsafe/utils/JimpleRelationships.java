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

package droidsafe.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import droidsafe.analyses.RequiredModeling;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.ValueBox;
import soot.jimple.Expr;
import soot.jimple.Stmt;
import soot.jimple.StmtBody;
import soot.util.Chain;

/**
 * This class generates maps of relationships between Jimple objects like
 * expressions, statements, and methods.
 * 
 * @author mgordon
 *
 */
public class JimpleRelationships {
    /** logger field */
    private static final Logger logger = LoggerFactory.getLogger(JimpleRelationships.class);
    /** Map of expr to enclosing statement */
    private HashMap<Expr, Stmt> exprToStmt;
    /** Map of value box to enclosing statement */
    private HashMap<ValueBox, Stmt> valueBoxToStmt;
    /** map of statement to enclosing SootMethod */
    private HashMap<Stmt, SootMethod> stmtToMethod;
    /** Static singleton */
    private static JimpleRelationships v;

    /** 
     * Return static singleton object.
     */
    public static JimpleRelationships v() {
        if (v == null) {
            v = new JimpleRelationships();
        }
        return v;
    }

    /**
     * Reset underlying maps.  Used if there has been jimple transforms run.
     */
    public static void reset() {
        v = null;
    }

    /**
     * Return set of all stmts in the program.
     */
    public Set<Stmt> getAllStmts() {
        return Collections.unmodifiableSet(stmtToMethod.keySet());
    }

    /**
     * Regenerate underlying maps.
     */
    private JimpleRelationships() {
        logger.info("Calculating Jimple relationships...");
        exprToStmt = new HashMap<Expr, Stmt>();
        stmtToMethod = new HashMap<Stmt, SootMethod>();
        valueBoxToStmt = new HashMap<ValueBox, Stmt>();
        //build maps of relationships by iterating over all user classes:
        for (SootClass clz : Scene.v().getClasses()) {
            if (clz.isPhantom() || clz.isInterface())
                continue;

            for (SootMethod method : clz.getMethods()) {
                if (!method.isConcrete() || method.isAbstract() || method.isPhantom() || method.isNative()) 
                    continue;
                try {
                    StmtBody stmtBody = (StmtBody)method.retrieveActiveBody();

                    // get body's unit as a chain
                    Chain units = stmtBody.getUnits();

                    Iterator stmtIt = units.iterator(); 

                    while (stmtIt.hasNext()) {
                        Stmt stmt = (Stmt)stmtIt.next();

                        stmtToMethod.put(stmt, method);
                        for (Object box : stmt.getUseAndDefBoxes()) {
                            if (box instanceof ValueBox) 
                                valueBoxToStmt.put((ValueBox)box, stmt);

                            if (((ValueBox) box).getValue() instanceof Expr)
                                exprToStmt.put((Expr)((ValueBox) box).getValue(), stmt);
                        }
                    }                
                } catch (Exception e) {
                    logger.debug("Error in JimpleRelationships. Ignoring: {} {}", method, e);
                }
            }
        }
    } 

    /**
     * Given a value box in the scene, return the enclosing statment
     */
    public Stmt getEnclosingStmt(ValueBox box) {
        return valueBoxToStmt.get(box);
    }

    /**
     * Given an expr in the Scene, return the enclosing statement.
     */
    public Stmt getEnclosingStmt(Expr expr) {
        return exprToStmt.get(expr);
    }

    /** 
     * Given a statement in the Scene, return the enclosing statement.
     */
    public SootMethod getEnclosingMethod(Stmt stmt) {
        return stmtToMethod.get(stmt);
    }

    /**
     * Given an expression in the Scene, return the enclosing method.
     */
    public SootMethod getEnclosingMethod(Expr expr) {
        return stmtToMethod.get(exprToStmt.get(expr));
    }
}
