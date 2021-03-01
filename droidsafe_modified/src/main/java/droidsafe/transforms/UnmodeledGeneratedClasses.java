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

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import droidsafe.analyses.strings.JSAStrings;
import droidsafe.android.app.Harness;
import droidsafe.android.app.Project;
import droidsafe.android.system.API;
import droidsafe.android.system.InfoKind;
import droidsafe.main.Config;
import droidsafe.transforms.objsensclone.ClassCloner;
import droidsafe.transforms.objsensclone.ObjectSensitivityCloner;
import droidsafe.utils.SootUtils;
import soot.ArrayType;
import soot.Body;
import soot.Local;
import soot.Modifier;
import soot.PrimType;
import soot.RefLikeType;
import soot.RefType;
import soot.Scene;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.Type;
import soot.Value;
import soot.VoidType;
import soot.jimple.IntConstant;
import soot.jimple.Jimple;
import soot.jimple.JimpleBody;
import soot.jimple.NullConstant;
import soot.jimple.Stmt;
import soot.jimple.StmtBody;
import soot.jimple.spark.pag.AllocNode;
import soot.jimple.NewExpr;

public class UnmodeledGeneratedClasses {
    private final static Logger logger = LoggerFactory.getLogger(UnmodeledGeneratedClasses.class);

    private static UnmodeledGeneratedClasses v = null;

    /** if true, then when adding a dummy class, add implementors up to the number specified by MAX_IMPLEMENTORS_TO_ADD */
    private static final boolean ADD_SUBS_AND_IMPLEMENTORS = true;
    private static final int MAX_SUBS_AND_IMPLEMENTORS_TO_ADD = 20;

    public static final String DUMMIES_CLASS_NAME = Project.DS_GENERATED_CLASSES_PREFIX + "DroidSafeDummies";

    public static final String UNKNOWN_TAINT_METHOD_PREFIX = "getUnmodeledTaint";

    public static final String DUMMY_FIELD_PREFIX = "__DUMMY_FIELD_";

    public static final String noArgConsSubSig = "void <init>()";

    private SootClass dummyClass;

    private Map<Type,SootField> typeToAddedField;

    private SootMethod dummyInit;

    private SootField dummyObjectField;

    private JimpleBody dummyInitBody;

    private Stmt addBefore;

    public static int localID = 0;

    private Set<SootClass> classesAdded;

    private SootClass lastClonedClass;

    public static void reset() {
        v = null;
    }

    public static UnmodeledGeneratedClasses v() {
        if (v == null) 
            v = new UnmodeledGeneratedClasses();

        return v;
    }

    public SootMethod getInitMethod() {
        return dummyInit;
    }

    private UnmodeledGeneratedClasses() {
        typeToAddedField = new HashMap<Type,SootField>();
        classesAdded = new HashSet<SootClass>();
        //create class with fields, all initialized in a method or static init
        createClass();
    }

    /**
     * Is this node of a created class type?
     */
    public boolean isGeneratedNode(AllocNode node) {
        if (node.getType() instanceof RefType) {
            return classesAdded.contains(((RefType)node.getType()).getSootClass());
        }

        return false;
    }

    private void createClass() {
        //create the harness class
        dummyClass = new SootClass(DUMMIES_CLASS_NAME, Modifier.PUBLIC | Modifier.FINAL);
        dummyClass.setSuperclass(Scene.v().getSootClass("java.lang.Object"));

        API.v().addSystemClass(dummyClass);

        Scene.v().addClass(dummyClass);
        Scene.v().loadClass(dummyClass.getName(), SootClass.BODIES);
        dummyClass.setApplicationClass();    


        dummyObjectField = new SootField(DUMMY_FIELD_PREFIX + "_OBJ_" + localID, 
            RefType.v("java.lang.Object"), Modifier.PUBLIC | Modifier.STATIC);
        dummyClass.addField(dummyObjectField);     

        //create the dummy init methods called by harness
        List<Type> args = new LinkedList<Type>();
        dummyInit = new SootMethod("dummyInit", 
            args, VoidType.v(),
            Modifier.PUBLIC | Modifier.STATIC);

        dummyInit.setDeclaringClass(dummyClass);
        dummyClass.addMethod(dummyInit);

        dummyInitBody = Jimple.v().newBody(dummyInit);
        dummyInit.setActiveBody(dummyInitBody);

        addBefore = Jimple.v().newReturnVoidStmt();

        dummyInitBody.getUnits().add(addBefore);

        API.v().addSafeMethod(dummyInit);
    }

    private void addStmt(Stmt stmt) {
        dummyInitBody.getUnits().insertBefore(stmt, addBefore);
    }

    private SootField addPrimitive(PrimType type) {

        String suffix = Character.toUpperCase(type.toString().charAt(0)) + type.toString().substring(1);

        SootField field = new SootField(DUMMY_FIELD_PREFIX + suffix + localID, type, Modifier.PUBLIC | Modifier.STATIC);

        dummyClass.addField(field);

        //create call in dummy init to initialize value
        SootMethod getTaintMethod = Scene.v().getMethod("<" + Harness.RUNTIME_MODELING_CLASS + ": " + 
                type.toString() + " " + 
                UNKNOWN_TAINT_METHOD_PREFIX + suffix + "()>");

        //add initialization code to dummy init method
        Local local = Jimple.v().newLocal("_$UG" + localID++, type);
        dummyInitBody.getLocals().add(local);

        //local = getTaint()
        //field = local

        addStmt(Jimple.v().newAssignStmt(
            local,
            Jimple.v().newStaticInvokeExpr(getTaintMethod.makeRef())
                ));

        addStmt(Jimple.v().newAssignStmt(Jimple.v().newStaticFieldRef(field.makeRef()), local));

        return field;
    }

    private SootField addArrayType(ArrayType type) {
        Type baseType = SootUtils.getBaseType(type);
        JimpleBody body = dummyInitBody;
        
        logger.debug("type: {}", type);
        logger.debug("Basetype: {}", baseType);
        
        Value baseValue = getSootFieldForType(baseType);
        
        if (baseValue == null || baseValue.equals(NullConstant.v()))
            return null;

        SootField field = new SootField(DUMMY_FIELD_PREFIX + type.getElementType().toString().replace(".", "_") + "_array" + localID, 
            type, Modifier.PUBLIC | Modifier.STATIC);
        dummyClass.addField(field);

        //create a local for the field reference
        Local dummyLocal = Jimple.v().newLocal("_$TU" + localID++, baseValue.getType());
        body.getLocals().add(dummyLocal);

        //create new array to local     
        Local arrayLocal = Jimple.v().newLocal("_$TU" + localID++, type);
        body.getLocals().add(arrayLocal);

        if (type.numDimensions > 1) {
            //multiple dimensions, have to do some crap...
            List<Value> ones = new LinkedList<Value>();
            for (int i = 0; i < type.numDimensions; i++)
                ones.add(IntConstant.v(1));

            addStmt(Jimple.v().newAssignStmt(arrayLocal,
                Jimple.v().newNewMultiArrayExpr(type, ones)));
        } else {
            //single dimension, add new expression
            addStmt(Jimple.v().newAssignStmt(arrayLocal, 
                Jimple.v().newNewArrayExpr(baseType, IntConstant.v(1))));
        }

        //get down to an element through the dimensions
        Local elementPtr = arrayLocal;
        while (((ArrayType)(elementPtr.getType())).getElementType() instanceof ArrayType) {
            Local currentLocal = Jimple.v().newLocal("_$MULTIARRAY" + localID++, ((ArrayType)elementPtr.getType()).getElementType());
            addStmt(Jimple.v().newAssignStmt(
                currentLocal, 
                Jimple.v().newArrayRef(elementPtr, IntConstant.v(0))));
            elementPtr = currentLocal;
        }

        //assign only element of array to the field of the base type we got above
        addStmt(Jimple.v().newAssignStmt(dummyLocal, baseValue));

        //assign the new local to the array access
        addStmt(Jimple.v().newAssignStmt(
            Jimple.v().newArrayRef(elementPtr, IntConstant.v(0)), 
            dummyLocal)); 

        addStmt(Jimple.v().newAssignStmt(Jimple.v().newStaticFieldRef(field.makeRef()), arrayLocal));

        return field;

    }

    private SootField addStringType(RefType type) {
        SootClass clz = type.getSootClass();

        //if character sequence then replace with String
        if (Scene.v().getSootClass("java.lang.CharSequence").equals(clz)) {
            clz = Scene.v().getSootClass("java.lang.String");
        }

        //field and add creation of object
        if (!clz.declaresMethod(noArgConsSubSig)) {
            logger.error("Error during fallback modeling. Class {} does not have a no arg constructor.", clz);
            return null;
        }

        SootField field = new SootField(DUMMY_FIELD_PREFIX + type.toString().replace(".", "_") + localID, 
            type, Modifier.PUBLIC | Modifier.STATIC);
        dummyClass.addField(field);


        //add initialization code to dummy init method
        Local local = Jimple.v().newLocal("_$UG" + localID++, type);
        dummyInitBody.getLocals().add(local);

        //local = new Clone()
        //local.init();
        //field = local
        addStmt(Jimple.v().newAssignStmt(local, Jimple.v().newNewExpr(clz.getType())));
        addStmt(Jimple.v().newInvokeStmt(Jimple.v().newSpecialInvokeExpr(local, clz.getMethod(noArgConsSubSig).makeRef())));
        addStmt(Jimple.v().newAssignStmt(Jimple.v().newStaticFieldRef(field.makeRef()), local));

        return field;
    }

    public SootClass getLastCloneCreated() {
        return lastClonedClass;
    }

    /**
     * Create an object and a new field to reference that object.  For the object type, find the first 
     * concrete sub class or implementor of type (or type itself if concrete).
     */
    public SootField addRefTypeWithNewField(RefType type, InfoKind unmodeledFlowType, boolean deepClone) {
        SootClass clz = type.getSootClass();
        clz = ClassCloner.getClonedClassFromClone(clz);
        //if an interface, find a direct implementor of and instantiate that...
        if (!clz.isConcrete()) {
            clz = SootUtils.getCloseConcrete(clz);
        }

        if (clz ==  null) {
            //if clz is null, then we have an interface with no known implementors, 
            //so just pass null
            logger.warn("Cannot find any known implementors of {} when adding dummy object", 
                type.getSootClass());
            return null;
        }

        SootField field = new SootField(DUMMY_FIELD_PREFIX + type.toString().replace(".", "_") + localID, 
            type, Modifier.PUBLIC | Modifier.STATIC);
        dummyClass.addField(field);
        
        addRefTypeInternal(field, clz, unmodeledFlowType, deepClone);
        
        return field;
    }
    
    /**
     * Create an object for the given class (of type) and all concrete subclasses / implementors of the type.
     * Use the dummy Object field to reference all objects
     */
    public SootField addRefTypeForConcretes(RefType type, InfoKind unmodeledFlowType, boolean deepClone) {
        SootClass clz = type.getSootClass();
        clz = ClassCloner.getClonedClassFromClone(clz);
        //if an interface, find a direct implementor of and instantiate that...
        List<SootClass> concretes = SootUtils.getAllConcreteSubsImps(clz);

        if (concretes.isEmpty()) {
            //if clz is null, then we have an interface with no known implementors, 
            //so just pass null
            logger.debug("Cannot find any known implementors of {} when adding dummy object", 
                type.getSootClass());
            return null;
        }

        int created = 0;
        for (SootClass concrete : concretes) {
            //don't clone cloned classes
            if (ClassCloner.isClonedClass(concrete))
                continue;
            
            logger.debug("On concrete {} for fallback type {}", type, concrete);
            
            //um, just in case a clone sneaks by ??
            SootClass realConcrete = ClassCloner.getClonedClassFromClone(concrete);
            
            if (!typeToAddedField.containsKey(realConcrete.getType())) {
                logger.debug("Need to add concrete {}", realConcrete);
                addRefTypeInternal(dummyObjectField, realConcrete, unmodeledFlowType, deepClone);
                created++;
            }

            if (created > MAX_SUBS_AND_IMPLEMENTORS_TO_ADD) {
                logger.debug("reached max created dummy concrete objects created: {}", clz);
                break;
            }

        }

        return dummyObjectField;
    }

    /**
     * Add a unmodeled object in the DroidSafeDummies generated class.  Create a cloned type of the 
     * given type and allocate an object of the cloned type.  Tag all methods of the cloned type with 
     * the given source info kind.  Do not check to see if another object of the type has been created, and
     * do not cache this object / field.
     */
    private void addRefTypeInternal(SootField field, SootClass clz, InfoKind unmodeledFlowType, boolean deepClone) {
        //don't clone cloned classes
        if (ClassCloner.isClonedClass(clz))
            return;
        
        clz = ClassCloner.getClonedClassFromClone(clz);
        
        //clone clz
        ClassCloner cloner;
        if (deepClone || (Config.v().reportUnmodeledFlows && 
                ("android.content.Intent".equals(clz.getName()) || "android.nfc.NdefRecord".equals(clz.getName()))))
            cloner = ClassCloner.cloneClassAndInheritedMethods(clz, true);
        else 
            cloner = ClassCloner.cloneClass(clz);

        SootClass clone = cloner.getClonedClass();
        lastClonedClass = clone;

        logger.info("Creating cloned class for fallback modeling: {}", clone);

        classesAdded.add(clone);

        installNoArgConstructor(clone);

        //make all methods of unmodeled type
        for (SootMethod method : clone.getMethods()) {
            API.v().addSourceInfoKind(method, unmodeledFlowType.getName(), unmodeledFlowType.isSensitive());
        }

        //field and add creation of object
        if (!clone.declaresMethod(noArgConsSubSig)) {
            logger.error("Error during fallback modeling. Class {} does not have a no arg constructor.", clone);
        }                 

        //add initialization code to dummy init method
        Local local = Jimple.v().newLocal("_$UG" + localID++, clz.getType());
        dummyInitBody.getLocals().add(local);

        //local = new Clone()
        //local.init();
        //field = local
        addStmt(Jimple.v().newAssignStmt(local, Jimple.v().newNewExpr(clone.getType())));
        addStmt(Jimple.v().newInvokeStmt(Jimple.v().newSpecialInvokeExpr(local, clone.getMethod(noArgConsSubSig).makeRef())));
        addStmt(Jimple.v().newAssignStmt(Jimple.v().newStaticFieldRef(field.makeRef()), local));

        logger.debug("Adding to fallback map: {} -> {}", clz.getType(), field);
        
        typeToAddedField.put(clz.getType(), field);
    }

    /**
     * Create a no arg constructor either by cloning super's no arg constructor (if exists) or creating an empty
     */
    public static void installNoArgConstructor(SootClass clone) {
        boolean cloned = false;

        if (clone.declaresMethod("void <init>()"))
            return;

        //first try to clone the superclass's init method
        if (clone.getSuperclass().declaresMethod("void <init>()")) {
            SootMethod ancestorM = clone.getSuperclass().getMethod("void <init>()");
            if ( !ancestorM.isPhantom() && ancestorM.isConcrete()) {
                SootMethod newMeth = new SootMethod(ancestorM.getName(), ancestorM.getParameterTypes(),
                    ancestorM.getReturnType(), ancestorM.getModifiers(), ancestorM.getExceptions());

                clone.addMethod(newMeth);

                API.v().cloneMethodClassifications(ancestorM, newMeth);

                Body ancestorBody = null;
                try {
                    ancestorBody = ancestorM.retrieveActiveBody();
                }
                catch (Exception ex) {
                    logger.info("Exception retrieving method body {}", ex);
                    return;
                }

                //clone body
                Body newBody = (Body)ancestorBody.clone();
                newMeth.setActiveBody(newBody);

                JSAStrings.v().updateJSAResults(ancestorBody, newBody);
                cloned = true;
            }
        }

        if (!cloned) {
            //if we have not cloned a method, then just create a no arg constructor...
            SootMethod init = new SootMethod("<init>", Collections.<Type>emptyList(), VoidType.v(), 
                Modifier.PUBLIC, Collections.<SootClass>emptyList());

            clone.addMethod(init);

            if (API.v().isSystemClass(clone)) {
                API.v().addSafeMethod(init);
            }

            Body newBody = Jimple.v().newBody(init);
            init.setActiveBody(newBody);

            //get this, just return?
            Local thisL = Jimple.v().newLocal("_$r0", clone.getType());
            newBody.getLocals().add(thisL);
            newBody.getUnits().add(Jimple.v().newIdentityStmt(thisL, Jimple.v().newThisRef(clone.getType())));

            newBody.getUnits().add(Jimple.v().newReturnVoidStmt());
        }
    }

    /**
     * Return the field that has been created to fake object of the given type.  
     * 
     * This method could return null constant if for some reason, no field is created.
     */
    public Value getSootFieldForType(Type type) {
        SootField field = null;

        if (type instanceof PrimType) {
            field = addPrimitive((PrimType)type);
        } else if (SootUtils.isStringOrSimilarType(type)) {
            //handle string separately so we don't keep reusing the same string object
            //and spreading taint all around!
            field = addStringType((RefType)type);
        } else {        
            //if a reference, create dummy object
            if (type instanceof RefType) {
                //class type
                field = addRefTypeForConcretes((RefType)type, 
                    InfoKind.getInfoKind("UNMODELED", Config.v().reportUnmodeledFlows), false);
                logger.debug("Field = {}", field);
                
            } else if (type instanceof ArrayType) {
                //array type
                field = addArrayType((ArrayType)type);
            }                         
        }

        if (field == null)
            return NullConstant.v();

        return Jimple.v().newStaticFieldRef(field.makeRef());
    }

}
