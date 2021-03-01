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

package droidsafe.transforms.objsensclone;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import droidsafe.analyses.pta.PTABridge;
import droidsafe.analyses.strings.JSAStrings;
import droidsafe.android.app.Project;
import droidsafe.android.system.API;
import droidsafe.main.Config;
import droidsafe.speclang.Method;
import droidsafe.utils.SootMethodList;
import droidsafe.utils.SootUtils;
import soot.Body;
import soot.Modifier;
import soot.RefType;
import soot.Scene;
import soot.SootClass;
import soot.SootField;
import soot.SootFieldRef;
import soot.SootMethod;
import soot.SootMethodRef;
import soot.Type;
import soot.Value;
import soot.ValueBox;
import soot.jimple.FieldRef;
import soot.jimple.InvokeExpr;
import soot.jimple.NewExpr;
import soot.jimple.SpecialInvokeExpr;
import soot.jimple.Stmt;
import soot.jimple.StmtBody;
import soot.options.Options;
import soot.util.Chain;

/**
 * This transformation will create a clone of a given class that is appropriate for separating the cloned
 * class from the parent in the points to analysis, such that we can introduce Object Sensitivity.
 * 
 * In the original class, all private fields are made protected, so that code in the clone can access them.
 * 
 * The clone that is created does not include fields from the ancestor classes.
 * 
 * All non-static methods from the original class plus its ancestors are added to the clone by using the
 * CloneInheritedMethods transformation (they are added in a way such that method inheritance is correctly 
 * observed from the original hierarchy).  
 * 
 * @author mgordon
 *
 */
public class ClassCloner {
	/** static logger class */
	private static final Logger logger = LoggerFactory.getLogger(ClassCloner.class);
	/** original soot class which we are cloning */
	private SootClass original;
	/** the clone we have created */
	private SootClass clone;
	/** unique ID used for introduced fields */
	private static int uniqueID = 0;
	/** appended to name cloned classes */
	public static final String CLONE_POSTFIX = "_ds_clone_";
	/** transform to clone inherited methods and remove dynamic dispatch */
	private CloneInheritedMethods cim;
	/** if true, clone all methods, otherwise clone only reachable methods */
	private boolean cloneAllMethods;

	/**
	 * Private constructor for a specific class cloner.
	 */
	private ClassCloner(SootClass org, boolean allMethods) {
		this.original = org;
		cloneAllMethods = allMethods;
	}

	/**
	 * Return the cloned class.
	 */
	public SootClass getClonedClass() {
		return clone;
	}


	/**
	 * Return set of cloned methods that are reachable based on whether the original method 
	 * that was clone was reachable based on the current pta result.
	 * @return
	 */
	public Set<SootMethod> getReachableClonedMethods() {
		return cim.getReachableClonedMethods();
	}

	public Map<SootMethod,SootMethod> getCloneToOriginalMap() {
		return cim.getCloneToOriginalMap();
	}

	/** 
	 * Static call to clone a particular class, and return the clone. 
	 * 
	 * If isAPIClass is true, then treat the cloned class as an api class and add it to the list of api classes, and
	 * set its methods as safe,spec,ban based on ancestors.
	 * 
	 * if allMethods is true, then clone all methods from ancestor classes, otherwise if false, clone only reachables
	 */
	public static ClassCloner cloneClassAndInheritedMethods(SootClass original, boolean allMethods) {
		ClassCloner c = new ClassCloner(original, allMethods);
		c.cloneClassCloneInheritedMethodsAndInstallClass();
		return c;
	}

	/**
	 * Be very careful when using this method to clone a class.  It clones the class, and creates clones
	 * of all methods and fields in the class (but not inherited from superclasses).  The new clone becomes
	 * a subclass of the original.
	 */
	public static ClassCloner cloneClassAndCloneMethodsAndFields(SootClass original) {
		ClassCloner c = new ClassCloner(original, false);
		c.cloneClassCloneMethodsFieldsAndInstallClass();
		return c;
	}

	public static ClassCloner cloneClass(SootClass original) {
		ClassCloner c = new ClassCloner(original, false);
		c.cloneAndInstallClass();
		return c;
	}

	private void cloneAndInstallClass() {
		clone = new SootClass(original.getName() + CLONE_POSTFIX + uniqueID, 
				original.getModifiers());
		uniqueID++;

		//System.out.printf("Cloning class %s with %s.\n", original, clone);

		//set parent
		if (original.isFinal()) {
			//change final modifier
			logger.info("Changing final modifier on {}", original);
			original.setModifiers(original.getModifiers() ^ Modifier.FINAL);
		}
		clone.setSuperclass(original);

		//install the class
		Scene.v().addClass(clone);
		Scene.v().loadClass(clone.getName(), SootClass.BODIES);
		clone.setApplicationClass();  

		API.v().cloneClassClassifications(original, clone);
	}

	private void cloneClassCloneMethodsFieldsAndInstallClass() {
		cloneAndInstallClass();

		//clone methods
		for (SootMethod ancestorM : original.getMethods()) {
			if (ancestorM.isFinal())
				ancestorM.setModifiers(ancestorM.getModifiers() ^ Modifier.FINAL);

			SootMethod newMeth = new SootMethod(ancestorM.getName(), ancestorM.getParameterTypes(),
					ancestorM.getReturnType(), ancestorM.getModifiers(), ancestorM.getExceptions());

			
			//register method
			clone.addMethod(newMeth);    

			API.v().cloneMethodClassifications(ancestorM, newMeth);

			//clone body
			Body newBody = (Body)ancestorM.retrieveActiveBody().clone();
			newMeth.setActiveBody(newBody);

			//change the type of the this local to the current (child) class
			//don't think this is really necessary because something else seems to do this...
			try {
				if (!ancestorM.isStatic()) {
					newBody.getThisLocal().setType(clone.getType());
				}
			} catch (Exception e) {
				logger.debug("Error during cloning", e);
			}

			JSAStrings.v().updateJSAResults(ancestorM.retrieveActiveBody(), newBody);
		}

		//clone fields
		for (SootField field : original.getFields()) {
			SootField newField = new SootField(field.getName(), field.getType(), field.getModifiers());
			clone.addField(newField);			
		}		
	}

	/**
	 * Perform the work of actually clone class, changing fields and cloning methods.
	 */
	private void cloneClassCloneInheritedMethodsAndInstallClass() {
		cloneAndInstallClass();

		//remove inheritance by cloning
		cim = new CloneInheritedMethods(clone, cloneAllMethods, true);
		cim.transform();


		//for all new expressions in the cloned methods of the clone,
		//replace self creations with creations of clone
		RefType originalType = RefType.v(original);
		RefType cloneType = RefType.v(clone);
		for (SootMethod method : clone.getMethods()) {        
			StmtBody stmtBody = null;
			try {
				stmtBody = (StmtBody)method.retrieveActiveBody();
			}
			catch (Exception ex) {
				logger.info("Exception retrieving method body {}", ex);
				continue;
			}

			for (ValueBox vb : stmtBody.getUseAndDefBoxes()) {
				if (vb.getValue() instanceof NewExpr &&
						((NewExpr)vb.getValue()).getBaseType().equals(originalType)) {
					NewExpr newE = (NewExpr)vb.getValue();
					newE.setBaseType(cloneType);
				}
			}
		}

	}

	public static boolean isClonedClass(SootClass clz) {
		return clz.getName().contains(CLONE_POSTFIX);
	}

	public static String removeClassCloneSuffix(String str) {
		String regex = CLONE_POSTFIX+"[0-9]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.replaceAll("");
	}

	public static SootClass getClonedClassFromClone(SootClass clone) {
		return Scene.v().getSootClass(ClassCloner.removeClassCloneSuffix(clone.getName()));
	}
}
