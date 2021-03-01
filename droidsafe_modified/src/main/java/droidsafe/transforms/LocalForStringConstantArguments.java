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

package droidsafe.transforms;

import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import droidsafe.android.app.Project;

import soot.Body;
import soot.BodyTransformer;
import soot.Local;
import soot.RefType;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Value;
import soot.VoidType;
import soot.jimple.AssignStmt;
import soot.jimple.InvokeExpr;
import soot.jimple.Jimple;
import soot.jimple.Stmt;
import soot.jimple.StmtBody;
import soot.jimple.StringConstant;
import soot.toolkits.graph.PseudoTopologicalOrderer;
import soot.util.Chain;

/**
 * Tranformer called on all application classes that will find all invoke expressions, and
 * if an arg is a string constant, will create a new local for the constant, 
 * and replace the constant in the argument with the local.  Needed for points to analysis.
 * 
 * @author mgordon
 *
 */
public class LocalForStringConstantArguments extends BodyTransformer {
	
	private final static Logger logger = LoggerFactory.getLogger(LocalForStringConstantArguments.class);
	private static int LOCALID = 0;
	public static final String LOCAL_PREFIX = "_$DS_STRING_ARG";

	/**
	 * Call this pass on all application classes in the project.
	 */
	public static void run() {
		LocalForStringConstantArguments transformer = new LocalForStringConstantArguments();
		for (SootClass clz : Scene.v().getClasses()) {
			if (Project.v().isSrcClass(clz.toString())) {
				for (SootMethod meth : clz.getMethods()) {
					if (meth.isConcrete()) {
						try {
							transformer.transform(meth.retrieveActiveBody());
						}
						catch (Exception ex) {
							logger.info("Exception retrieving method body {}", ex);
							continue;
						}

					}
					
				}
			}
		}
	}
	
	/**
	 * Tranform method called on the body that will find all invoke expressions, and
	 * if an arg is a string constant, will create a new local for the constant, 
	 * and replace the constant in the argument with the local.
	 */
	protected void internalTransform(Body b, String phaseName, Map options)  {
		StmtBody stmtBody = (StmtBody)b;

		// get body's unit as a chain
		Chain units = stmtBody.getUnits();

		// get a snapshot iterator of the unit since we are going to
		// mutate the chain when iterating over it.
		Iterator stmtIt = units.snapshotIterator();

		while (stmtIt.hasNext()) {

			Stmt stmt = (Stmt)stmtIt.next();

			if (!stmt.containsInvokeExpr()) {
				continue;
			}

			InvokeExpr expr = (InvokeExpr)stmt.getInvokeExpr();
						
			//iterate over the args and see if any arg is a string constant
			for (int i = 0; i < expr.getArgCount(); i++) {
				Value v = expr.getArg(i);
				if (v instanceof StringConstant) {
					//add a local variable
					Local arg = Jimple.v().newLocal(LOCAL_PREFIX + LOCALID++, RefType.v("java.lang.String"));
					stmtBody.getLocals().add(arg);
					
					//add an assignment of the local to the string constant
					//right before the call
					AssignStmt assignStmt = Jimple.v().newAssignStmt(arg, StringConstant.v(((StringConstant)v).value));
					units.insertBefore(assignStmt, stmt);
					
					//replace the string constant with the local in the call
					expr.setArg(i, arg);
				}
			}			
		}
	}
}

