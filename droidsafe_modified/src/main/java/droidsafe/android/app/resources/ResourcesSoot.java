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

/*
 * This class contains soot objects that will be used in XML replacement 
 */
package droidsafe.android.app.resources;

import com.google.common.collect.HashBiMap;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import soot.Body;
import soot.BooleanType;
import soot.RefType;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.SootMethodRef;
import soot.Type;
import soot.Unit;
import soot.Value;
import soot.VoidType;
import soot.Modifier;
import soot.SootField;
import soot.Local;
import soot.jimple.FieldRef;  
import soot.jimple.Expr;
import soot.jimple.ConditionExpr;
import soot.jimple.ReturnVoidStmt;
import soot.jimple.StringConstant;
import soot.jimple.Jimple;
import soot.jimple.NullConstant;
import soot.jimple.Stmt;
import soot.jimple.JimpleBody;
import soot.util.Chain;
import droidsafe.android.app.Project;
import droidsafe.android.system.API;
import droidsafe.utils.SootUtils;

/**
 * @author Nguyen Nguyen
 * This calss is the holding place for all soot generated code that is injected into the 
 * Soot scene when parsing the XML resources.
 */
public class ResourcesSoot {

    public enum UiType {
        UNKNOWN,
        VIEW,
        FRAGMENT
    }

    /**
     * Inner class holding UISoot object used for lookup.  We will have a table of 
     * id->UISootObject mapping that will allow us to do cross reference between ID and 
     * (methods, object, text)
     */
    public class UISootObject {
        /** numeric object ID found ins gen/R..... */
        public int          numericId;         

        /** id in String representation, seen in XML file */
        public String       stringId;          

        /** the object type this UI object is    */
        public String       type;              

        /** collection of attributes associated with this UI */
        public Map<String, String> attributes;

        /** static field (in soot) associated with this UI object */
        public SootField    sootField;          

        /** exposed lookup method used by findViewBy_Id replacement */
        public SootMethod   lookupMethod;

        public UiType       childOf;

        /**
         * Default Constructor
         */
        public UISootObject() {
            numericId = 0;
            stringId   = "unknown";
            type = "unkownType";
            sootField    = null;
            lookupMethod = null;
            attributes = null;
            childOf    = UiType.UNKNOWN;
        }

        /**
         * Constructor to fill in Object values 
         * @param numId
         * @param type
         * @param strId
         * @param attrs
         */
        public UISootObject(int numId, String itsType, String strId, Map<String, String> attrs) {
            this.numericId = numId;
            this.stringId  = strId;
            this.type      = itsType;
            this.attributes = attrs;
            childOf    = UiType.UNKNOWN;
        }

    }

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ResourcesSoot.class);  

    /** singleton instance */
    private static  ResourcesSoot instance = new ResourcesSoot();
    /** singleton access */
    public static ResourcesSoot v() { return instance; }

    /** soot class for holder class */
    private SootClass  mSootClass;       //class that holds all methods

    /** clinit method */
    private SootMethod mClinitMethod;  

    /** clinit body */
    private JimpleBody mClinitBody;

    /** method to hold initLayout_ID method */
    private SootMethod mInitLayoutMethod;

    private Local mArgContext;

    /** body of initLayout_ID */
    private JimpleBody mInitLayoutBody;

    /** member activity, prevent serialization from crashing when empty */
    private SootField  mActivityField;

    private SootField  mConditionalField;

    /** prebuilt reference for ViewClass */
    private SootClass  mViewClass;   

    private List<SootClass> mBaseClassList;  

    /** pre-built View::<init> method */
    private SootMethod mViewInitMethod;

    /** table to hold ID -> object */
    private HashMap<Integer, UISootObject> mUiObjectTable; 

    /** map that holds numeric (generated) -> string (xml) */
    private HashBiMap<Integer, String> mNumberToIDMap;

    /** map that holds xml stringId -> set of string values */
    private Map<String, Set<RString>> mStringToValueSet;

    /** map that maps layout ID -> layout init method */
    private Map<Integer, SootMethod> mLayoutInitMap; 

    private static int UNIQUE_ID = 0;

    /**
     * private constructor
     */
    private ResourcesSoot() {

        mUiObjectTable = new HashMap<Integer, UISootObject>();
        mLayoutInitMap = new HashMap<Integer, SootMethod>();

        mSootClass = new SootClass(Project.DS_GENERATED_CLASSES_PREFIX + "ResourcesSoot", Modifier.PUBLIC);
        mSootClass.setSuperclass(Scene.v().getSootClass("java.lang.Object"));
        Scene.v().addClass(mSootClass);

        mConditionalField = new SootField("randomCond", BooleanType.v(),
            Modifier.PUBLIC | Modifier.STATIC);
        mSootClass.addField(mConditionalField);

        mActivityField = new SootField("currentActivity", RefType.v("android.app.Activity"), 
            Modifier.PUBLIC | Modifier.STATIC);
        mSootClass.addField(mActivityField);

        mSootClass.setApplicationClass();

        API.v().addSystemClass(mSootClass);

        mBaseClassList = new LinkedList<SootClass>();

        String[] baseClasses = new String[] {
                                             "android.view.View", "android.app.Fragment",
                                             "android.app.Activity", "android.support.v4.app.Fragment"
        };

        for (String className: baseClasses) {
            try {
                SootClass sootClass = Scene.v().getSootClass(className);
                if (sootClass != null)
                    mBaseClassList.add(sootClass);
            } 
            catch(Exception ex) {

            }
        }

        mViewClass     = Scene.v().getSootClass("android.view.View");
        mViewInitMethod = Scene.v().getMethod(
                "<android.view.View: void <init>(android.content.Context)>");
    }

    /** 
     * Re-initializes the singleton to avoid conflicts.
     */
    public static void reset(){
        instance = new ResourcesSoot();
    }

    public static boolean isAndroidId(Integer intId) {
        return ((intId.intValue() & 0xF1000000) == 0x01000000);            
    }
    /**
     * get the conditional field
     * @return
     */
    public SootField getConditionField() {
        return mConditionalField;
    }
    /** 
     * get numericID -> string ID mapping 
     */
    public void setNumberToStringMap(HashBiMap<Integer, String> map) {
        mNumberToIDMap = map;
    }

    /**
     * get stringID => list of possible values
     * @param map
     */
    public void setStringToValueSetMap(Map<String, Set<RString>> map) {
        mStringToValueSet = map;    
    }

    /**
     *  add ad new numeric to string ID map.  A unique numeric number will be allocated.
     *  We use this method to add new mapping for unnamed and duplicate entries in xml
     * @param strId
     */
    public void addNewNumberToStringEntry(String strId) {
        if (mNumberToIDMap == null)
            return;

        int baseId = 0x10000000;


        java.util.Random random = new java.util.Random();


        // find the next ID we can use for lookup 
        int id = baseId  | random.nextInt(0x1000);

        Integer intId = Integer.valueOf(id);

        while (mNumberToIDMap.containsKey(intId)) {
            id = baseId  | random.nextInt(0x1000);
            intId = Integer.valueOf(id);
        }

        mNumberToIDMap.put(intId, strId);
    }


    /**
     * renaming string ID from to to, this allows enclosing layout to be part of the ID
     * @param fromStrId
     * @param toStrId
     * 
     * @return true if renaming happened
     *  
     */
    public boolean renameStringIdEntry(String fromStrId, String toStrId) {
        if (mNumberToIDMap == null)
            return false;

        Integer key = mNumberToIDMap.inverse().get(fromStrId);
        if (key != null ) {
            mNumberToIDMap.remove(key);
            mNumberToIDMap.put(key, toStrId);
            return true;
        }
        return false;
    }

    /**
     * addView:
     *   function to add a view info into uiObjectTable and create a static for use by the
     *   IntegrateXMLLayout transformation.
     */
    public boolean addView(String type, String strId, Map<String, String> attrMap) {
        // adding ui object (partial information, no numeric ID, no soot method)  
        // to the list of UI objects 

        // First time initializing, we will add the clinit method.  <clinit> method
        // CANNOT be empty, so we only add it when there is at least a static member
        // if (uiObjectTable.isEmpty()) {
        //  addClinitMethod();
        // }

        if(strId == null || type == null) {
            logger.warn("addView type:{}, id:{}", type, strId);
        } else {
            logger.info("addView type:{}, id:{}", type, strId);
        }

        if (strId.startsWith("@+")) {
            strId = strId.substring(2);
        }

        if (strId.startsWith("@")) {
            strId = strId.substring(1);
        }

        strId = strId.replace("@android:", "");

        //normalize stringname
        if (strId.startsWith("android:")) {
            strId = strId.substring("android:".length());
        }

        Integer id = mNumberToIDMap.inverse().get(strId);

        if (id == null) {
            logger.warn("numeric ID is NULL for {} => {} ", strId, id);
            /*
            for (String myStrId: mNumberToIDMap.inverse().keySet()) {
                logger.warn("checking key {} <=> {} ", strId, myStrId);
            }
             */
            return false;
        }

        if (mUiObjectTable.get(id) == null) {

            String fullTypeName = makeClassName(type); 

            UISootObject obj = new UISootObject(id.intValue(), fullTypeName, strId, attrMap);
            mUiObjectTable.put(id, obj);

            //create a static field 
            if (createUiMember(id) == false) {
                //undo adding if failed
                logger.info("createUIMember failed on {}", id);
                mUiObjectTable.remove(id);
                return false;
            }

            // depending on type: fragment or view, create appropriate addGetView / addGetFragment
            if (obj.childOf == UiType.FRAGMENT) {
                logger.info("Fragment id {} is added ", String.format("%08x", id));
                addGetFragment_ID(id);
                return false;
            }

            if (obj.childOf == UiType.VIEW) {
                // create method getView_XYX()
                return addGetView_ID(id);
            }
        } else {
            logger.info("Already added? {}", mUiObjectTable.get(id));
        }
        return true;
    }


    /**
     * check to see if it is a fragment type
     * @param type
     * @return
     */
    private boolean isFragmentType(String type) {
        if (type.endsWith(".Fragment"))
            return true;

        SootClass sootClass  = null;

        try {
            sootClass = Scene.v().getSootClass(type);
        }
        catch (Exception ex) {
            return false;
        }

        for (String fragment: new String[] {"android.app.Fragment", 
        "android.support.v4.app.Fragment"}) {
            try {
                SootClass fragmentClass = Scene.v().getSootClass(fragment);

                if (SootUtils.checkAncestor(sootClass, fragmentClass))
                    return true;

            } 
            catch (Exception ex) {

            }

        }
        return false;
    }

    /**
     * first time creating a layout init function 
     * @param layout
     */
    public void createInitLayout_ID(String layoutName) {

        logger.info("createInitLayout for {} ", layoutName);

        Integer numericId = mNumberToIDMap.inverse().get(layoutName);
        if (numericId == null) {
            logger.warn("layout {} has no Unique ID", layoutName);
            for (String myStrId: mNumberToIDMap.inverse().keySet()) {
                logger.warn("checking key {} <=> {} ", layoutName, myStrId);
            }
            return;
        }
        String methodName = String.format("initLayout_%08x", numericId);
        if (!mSootClass.declaresMethodByName(methodName)) {
            List<Type> params = new LinkedList<Type>();
            params.add(RefType.v("android.content.Context"));

            mInitLayoutMethod = new SootMethod(methodName, params, VoidType.v(), 
                Modifier.PUBLIC | Modifier.STATIC);

            mSootClass.addMethod(mInitLayoutMethod);
            API.v().addSafeMethod(mInitLayoutMethod);

            mInitLayoutBody = Jimple.v().newBody(mInitLayoutMethod);
            mInitLayoutMethod.setActiveBody(mInitLayoutBody);
            mLayoutInitMap.put(numericId, mInitLayoutMethod);

            // extract parameter
            mArgContext = 
                    //Jimple.v().newLocal("paramContext",  RefType.v("android.app.Activity"));
                    Jimple.v().newLocal("paramContext",  RefType.v("android.content.Context"));

            // android.content.Context paramActivity;
            mInitLayoutBody.getLocals().add(mArgContext);

            // argContext = @paramter0
            Chain<Unit> units = mInitLayoutBody.getUnits();
            units.add(Jimple.v().newIdentityStmt(mArgContext,
                Jimple.v().newParameterRef(RefType.v("android.content.Context"), 0))); 
        } else {
            mInitLayoutMethod = mSootClass.getMethodByName(methodName);
            mInitLayoutBody = (JimpleBody) mInitLayoutMethod.getActiveBody();
            for (Local local : mInitLayoutBody.getLocals()) {
                //find the local we created above before
                if ("paramContext".equals(local.getName()))
                    mArgContext = local;
            }
        }
    }

    /**
     * adding a view allocation invoke to InitLayout_XXXX
     * @param strId
     * @return
     */
    public boolean addUiAllocToInitLayout_ID(String strId) {
        Integer intId = mNumberToIDMap.inverse().get(strId);
        UISootObject uiObj = mUiObjectTable.get(intId);

        if (uiObj == null)
            return false;

        if (uiObj.childOf == UiType.FRAGMENT)
            return addFragmentAllocToInitLayout_ID(strId);

        if (uiObj.childOf == UiType.VIEW)
            return addViewAllocToInitLayout_ID(strId);

        return false;
    }


    /**
     * add allocation of a view or its child to initLayout, no requestFocus
     * @param strId
     * @return
     */
    public boolean addViewAllocToInitLayout_ID(String strId) {
        return addViewAllocToInitLayout_ID(strId, false);
    }

    /**
     * add a view allocation to initLayout_XYX
     * @param strId
     * @param requestFocus: true to call requestFocus
     * @return
     */
    public boolean addViewAllocToInitLayout_ID(String strId, boolean requestFocus) {
        logger.info("addViewAllocForInitLayout view ID {} ", strId);
        Integer intId = mNumberToIDMap.inverse().get(strId);
        if (intId == null) {
            logger.warn("No matching numeric Id for {} ", strId);
            return false;
        }

        SootMethod method = lookupGetUi_ID(intId);

        if (method == null) {
            logger.warn("findViewByID_{} is NULL ", String.format("%08x", intId));
            return false;
        }

        //logger.warn("method {} ", method);
        //logger.warn("mArgContext {}", mArgContext);

        Expr invokeExpr = Jimple.v().newStaticInvokeExpr(method.makeRef(), mArgContext); 
        Stmt stmt = Jimple.v().newInvokeStmt(invokeExpr);

        Chain<Unit> units = mInitLayoutBody.getUnits();
        units.add(stmt);

        if (requestFocus) {
            logger.info("We may want to add requestFocus here");
        }

        return true;
    }

    /**
     * add a fragment allocation to initLayout_ID
     * @param strId
     * @return
     */
    public boolean addFragmentAllocToInitLayout_ID(String strId) {
        logger.info("addFragmentAllocForInitLayout view ID {} ", strId);
        Integer intId = mNumberToIDMap.inverse().get(strId);
        if (intId == null) {
            logger.warn("No matching numeric Id for {} ", strId);
            return false;
        }

        SootMethod method = lookupGetUi_ID(intId);

        if (method == null) {
            logger.warn("findFragmentByID_{} is NULL ", String.format("%08x", intId));
            return false;
        }

        //logger.warn("method {} ", method);
        //logger.warn("mArgContext {}", mArgContext);
        Expr invokeExpr = Jimple.v().newStaticInvokeExpr(method.makeRef(), mArgContext); 
        Stmt stmt = Jimple.v().newInvokeStmt(invokeExpr);

        Chain<Unit> units = mInitLayoutBody.getUnits();
        units.add(stmt);


        return true;
    }

    /**
     * invoking the callback if there is any inside this class
     * @param intId
     * @param onClickSignature
     * @return
     */
    public boolean addCallOnClickToInitLayout_ID(String strId, String onClickSignature) {
        logger.info("addCallOnClickToInitLayout_ID {} ", strId);
        Integer intId = mNumberToIDMap.inverse().get(strId);
        if (intId == null) {
            logger.warn("No matching numeric Id for {} ", strId);
            return false;
        }

        UISootObject uiObj = mUiObjectTable.get(intId);
        if (uiObj == null || uiObj.sootField == null) {
            logger.warn("findViewByID_{} is NULL ", String.format("%08x", intId));
            return false;
        }

        SootMethod method = null;

        if (onClickSignature.contains(":")) {
            String className = onClickSignature.substring(1, onClickSignature.indexOf(":"));
            try {
                SootClass clz = Scene.v().getSootClass(className);
                method = SootUtils.resolveCallbackMethod(clz, onClickSignature);
            }
            catch (Exception ex) {
                logger.warn("onClick handler {} is not available", onClickSignature);
                return false;
            }
        }
        else {
            List<SootMethod> matches = SootUtils.matchApplicationMethodName(onClickSignature);
            if (matches.size() > 0)
                method = matches.get(0);
        }

        if (method == null) {
            logger.warn("Cannot locate method {} ", onClickSignature);
            return false;
        }

        // Two things we need when performing the callback:
        // 1. owning object of the callback (activity) => mArgContext
        // 2. 

        logger.info("method {} OK for onclick", method);

        Chain<Unit> units = mInitLayoutBody.getUnits();


        String viewLocalName = "_$viewLocal" + UNIQUE_ID;
        Local viewLocal = Jimple.v().newLocal(viewLocalName, RefType.v("android.view.View"));
        mInitLayoutBody.getLocals().add(viewLocal);

        //logger.warn("mArgContext {}", mArgContext);
        FieldRef  fieldRef = Jimple.v().newStaticFieldRef(uiObj.sootField.makeRef());

        Stmt stmt = Jimple.v().newAssignStmt(viewLocal, fieldRef);

        // localView =  fieldRef
        units.add(stmt);

        //need cast argument of init layout to type of the receiver of the onclick method...
        //add a local for the cast
        SootClass listenerClass = method.getDeclaringClass();
        Local castLocal = Jimple.v().newLocal("_$castLocal" + UNIQUE_ID++ ,  RefType.v(listenerClass));
        mInitLayoutBody.getLocals().add(castLocal);

        Stmt castStmt = 
                Jimple.v().newAssignStmt(castLocal, Jimple.v().newCastExpr(mArgContext,RefType.v(listenerClass)));
        units.add(castStmt);

        Expr invokeExpr = Jimple.v().newVirtualInvokeExpr(castLocal, method.makeRef(), viewLocal);
        stmt = Jimple.v().newInvokeStmt(invokeExpr);

        units.add(stmt);
        return true;
    }

    /**
     * add return statement to initLayout_XYZ
     * @param strId
     * @return
     */
    public void addReturnToInitLayout_ID() {
        Chain<Unit> units = mInitLayoutBody.getUnits();

        if (!(units.getLast() instanceof ReturnVoidStmt)) {

            //logger.warn("mArgContext {}", mArgContext);
            Stmt stmt = Jimple.v().newReturnVoidStmt();

            // localView =  fieldRef
            units.add(stmt);
        }
    }

    /**
     * get a mthod that initalizes the Layout
     * @param intId
     * @return
     */
    public SootMethod lookupInitLayout_ID(Integer intId) {
        logger.info("calling lookupInitLayout_ID{}) ", 
            String.format("%08x", intId));
        return mLayoutInitMap.get(intId); 
    }

    /**
     * createViewMember:
     *   method to add static Button button_xxyyyy to the ResourcesSoot class
     */
    private boolean createUiMember(Integer intId) {
        logger.info("calling createViewMember {}:{}) ", 
            intId.toString(), String.format("%08x", intId));

        UISootObject obj = mUiObjectTable.get(intId);    
        if (obj == null || obj.type == null) {
            logger.warn("Object for id {} info is not available", 
                String.format("%08x", intId));
            return false; 
        }

        String   idName    = makeIdName(obj.type, obj.numericId); 
        String   className = makeClassName(obj.type);

        if (className == null) {
            logger.warn("Cannot resolve class {} ", obj.type);
            return false;
        }

        RefType  classType = RefType.v(className);
        RefType  fragmentType = RefType.v("android.app.Fragment");
        RefType  supportFragmentType = RefType.v("android.support.v4.app.Fragment");

        if (SootUtils.isSubTypeOfIncluding(classType, fragmentType) ||
                SootUtils.isSubTypeOfIncluding(classType, supportFragmentType)) {
            obj.childOf = UiType.FRAGMENT;
            logger.info("soot class {} is a Fragment ", classType);
        }

        //if (SootUtils.checkAncestor(sootClass, mViewClass)) {
        RefType viewType = RefType.v("android.view.View");
        if (SootUtils.isSubTypeOfIncluding(classType, viewType)) {
            obj.childOf = UiType.VIEW;
            logger.info("soot class {} is a view ", classType);
        }

        // step 1: create sootfield for member variable
        SootField sf = new SootField(idName, classType, Modifier.PUBLIC | Modifier.STATIC);
        mSootClass.addField(sf);
        obj.sootField = sf;
        return true;
    }

    /**
     * lookupGetView_ID:
     *   Method to look up the getView_2222()
     */
    public SootMethod lookupGetUi_ID(Integer intId) {
        logger.info("calling lookupGetView_ID {}) ", 
            String.format("%08x", intId));
        UISootObject obj = mUiObjectTable.get(intId);    
        if (obj == null) {
            logger.warn("Object for id {} info is not available", 
                String.format("%x", intId));
            return null; 
        }
        return obj.lookupMethod; 
    }

    /**
     * Method to add getView_XYX(content) to the ResourcesSoot 
     * @param intId : numeric Id seen in gen/R file
     */
    private boolean addGetView_ID(Integer intId) {
        // units.add(Jimple.v().newAssignStmt(fieldRef, arg));

        UISootObject obj = mUiObjectTable.get(intId);    
        logger.info("addGetView_ID({}:{}) ", intId.toString(), String.format("%x", intId));

        if (obj == null) {
            logger.warn("Object for id {} does not exist ", String.format("%x", intId));
            return false;
        }
        if (obj.sootField == null)  {
            logger.warn("No sootfield previously created ");
            return false;
        }

        List<Type> params = new LinkedList<Type>();
        params.add(RefType.v("android.content.Context"));

        RefType returnType = (RefType) obj.sootField.getType(); 

        SootMethod viewInitMethod = 
                Scene.v().getActiveHierarchy().resolveConcreteDispatch(
                    returnType.getSootClass(), mViewInitMethod);

        if (viewInitMethod == null) {
            logger.warn("Cannot locate proper constructor for {})", returnType);
            return false;
        }

        String funcName = "getView_" + String.format("%x", intId);
        //instantiate a method
        SootMethod method = new SootMethod(funcName, params, returnType, 
            Modifier.PUBLIC | Modifier.STATIC);

        obj.lookupMethod = method;

        // add the method to the class
        mSootClass.addMethod(method);
        API.v().addSafeMethod(method);

        // create active body, and set the body active
        JimpleBody body = Jimple.v().newBody(method);
        method.setActiveBody(body);

        Chain<Unit> units = body.getUnits();

        // extract parameter
        Local argContext = 
                Jimple.v().newLocal("_$paramContext",  RefType.v("android.content.Context"));

        // android.content.Context paramActivity;
        body.getLocals().add(argContext);

        // local Argument for view
        Local localView = Jimple.v().newLocal("_$localView",  returnType);
        body.getLocals().add(localView);

        // paramActivity = @paramter0
        units.add(Jimple.v().newIdentityStmt(argContext,
            Jimple.v().newParameterRef(RefType.v("android.content.Context"), 0)));

        FieldRef  fieldRef = Jimple.v().newStaticFieldRef(obj.sootField.makeRef());

        // localView =  fieldRef
        units.add(Jimple.v().newAssignStmt(localView, fieldRef));

        // beforeIF block
        Stmt beforeIf = (Stmt) units.getLast();

        // IF block: adding more code for if block
        Expr newExpr = Jimple.v().newNewExpr((RefType)returnType);

        units.add(Jimple.v().newAssignStmt(localView, newExpr));

        //view.init<>
        //constructor call, use invoke special
        units.add(Jimple.v().newInvokeStmt(
            Jimple.v().newSpecialInvokeExpr(localView, viewInitMethod.makeRef(), 
                argContext))); 

        for (String attrName: obj.attributes.keySet()) {
            SootMethod setter = AttributeSetterMap.v().resolveSetter(
                attrName, returnType.getSootClass());

            // if there is no setter match, skip the attribute
            if (setter == null) {
                // logger.debug("attr {}, class {} CANNOT resolve ", 
                //             attrName, returnType.getSootClass());
                continue;
            } 

            // at this point, we have a setter, need to call the setter with values
            logger.debug("attr {} => setter {} ", attrName, setter);


            Set<String> textSet = new HashSet<String>();
            String attrValue = obj.attributes.get(attrName);

            //TODO: need to normalize the name/params
            if (attrValue.contains("@")) {
                // the name automatically gets assigned during xml parsing

                String stringName = attrValue;
                stringName = stringName.replace("@android:string/",  "android.R.string.");
                stringName = stringName.replace("@string/", "string.");

                /*
                int ind = attrValue.indexOf("@");
                String stringName = attrValue.substring(ind+1);
                stringName = stringName.replace("/", ".");
                 */

                if (mStringToValueSet.containsKey(stringName)) {
                    logger.debug("{} can be expanded ", stringName);    
                    Set<RString> rstringList = mStringToValueSet.get(stringName);

                    /* set will keep it unique, remove duplicate entries */
                    for (RString rstring: rstringList) {
                        if (rstring == null || rstring.value == null)
                            continue;
                        textSet.add(rstring.value);
                    }
                } 
                else {
                    logger.warn("attr {} => {} not in mStringToValueSet ", attrValue, stringName ); 
                }
            } 
            else {
                textSet.add(attrValue);
            }

            for (String text: textSet) {
                Expr settingExpr = Jimple.v().newVirtualInvokeExpr(
                    localView, 
                    setter.makeRef(), 
                    StringConstant.v(text)); 

                Stmt settingStmt = Jimple.v().newInvokeStmt(settingExpr);
                logger.debug("text <{}> ", text);
                logger.debug("settingText expr {} ", settingExpr);
                units.add(settingStmt); 
            }
        }

        units.add(Jimple.v().newAssignStmt(fieldRef, localView));

        // afterIF: return localView
        Stmt afterIf = Jimple.v().newReturnStmt(localView);
        units.add(afterIf);

        // condition expression and statement (not equal expr)
        ConditionExpr condExpr = Jimple.v().newNeExpr(localView, NullConstant.v());

        // condition statement
        Stmt condStmt =  Jimple.v().newIfStmt(condExpr, afterIf);

        logger.debug("condStmt {} ", condStmt);
        units.insertAfter(condStmt, beforeIf);

        return true;
    }



    /**
     * method called to addFragment_ID
     * @param intId
     * @return
     */
    private boolean addGetFragment_ID(Integer intId) {

        /* we want to do this:
         * getFragment_XYZ(context) {
         * 	if (fragment == null)
    			fragment_XYZ =  new DerivedFragment();
    		}

         */

        UISootObject obj = mUiObjectTable.get(intId);    
        logger.info("addGetFragment_ID({}:{}) ", intId.toString(), String.format("%x", intId));

        if (obj == null) {
            logger.warn("Object for id {} does not exist ", intId);
            return false;
        }
        if (obj.sootField == null)  {
            logger.warn("No sootfield previously created ");
            return false;
        }

        List<Type> params = new LinkedList<Type>();
        params.add(RefType.v("android.content.Context"));

        RefType returnType = (RefType) obj.sootField.getType(); 

        SootMethod initMethod = null;

        for (String fragmentClass: new String[] {"android.app.Fragment", 
        "android.support.v4.app.Fragment"}) {
            String methodSig = String.format("<%s: void <init>()>", fragmentClass);
            SootMethod fragmentInit = Scene.v().getMethod(methodSig);

            try {
                initMethod = Scene.v().getActiveHierarchy().resolveConcreteDispatch(
                    returnType.getSootClass(), fragmentInit); 
            } 
            catch (Exception ex) {

            }

            if (initMethod != null)
                break;

        }

        if (initMethod == null) {
            logger.warn("Cannot resolve Fragment init method for {} ", returnType);
            return false;
        }



        String funcName = "getFragment_" + String.format("%x", intId);
        //instantiate a method
        SootMethod method = new SootMethod(funcName, params, returnType, 
            Modifier.PUBLIC | Modifier.STATIC);

        obj.lookupMethod = method;

        // add the method to the class
        mSootClass.addMethod(method);
        API.v().addSafeMethod(method);

        // create active body, and set the body active
        JimpleBody body = Jimple.v().newBody(method);
        method.setActiveBody(body);

        Chain<Unit> units = body.getUnits();

    	// extract parameter
    	Local argContext = 
    			Jimple.v().newLocal("_$paramContext",  RefType.v("android.content.Context"));

        // android.content.Context paramActivity;
        body.getLocals().add(argContext);

    	// local Argument for view
    	Local localFragment = Jimple.v().newLocal("_$localFragment",  returnType);
    	body.getLocals().add(localFragment);

        // paramActivity = @paramter0
        units.add(Jimple.v().newIdentityStmt(argContext,
            Jimple.v().newParameterRef(RefType.v("android.content.Context"), 0)));

        FieldRef  fieldRef = Jimple.v().newStaticFieldRef(obj.sootField.makeRef());

        // localFragment =  fieldRef
        units.add(Jimple.v().newAssignStmt(localFragment, fieldRef));

        // beforeIF block
        Stmt beforeIf = (Stmt) units.getLast();

        // IF block: adding more code for if block
        Expr newExpr = Jimple.v().newNewExpr((RefType)returnType);

        units.add(Jimple.v().newAssignStmt(localFragment, newExpr));

        units.add(Jimple.v().newInvokeStmt(
            Jimple.v().newSpecialInvokeExpr(localFragment, initMethod.makeRef())));

        units.add(Jimple.v().newAssignStmt(fieldRef, localFragment));

        //add call to droidsafeSetContext to set the parent of the fragment
        List<Type> setContextMethodArgTypes = new LinkedList<Type>();
        setContextMethodArgTypes.add(Scene.v().getSootClass("android.content.Context").getType());
        SootMethodRef setContextMethod = Scene.v().makeMethodRef(returnType.getSootClass(), "droidsafeSetContext", 
            setContextMethodArgTypes, VoidType.v(), false);        
        Stmt setContextCall = Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(localFragment, setContextMethod,
            argContext));
        
        units.add(setContextCall);
        
        // afterIF: return localFragment
        Stmt afterIf = Jimple.v().newReturnStmt(localFragment);
        units.add(afterIf);

        // condition expression and statement (not equal expr)
        ConditionExpr condExpr = Jimple.v().newNeExpr(localFragment, NullConstant.v());

        // condition statement
        Stmt condStmt =  Jimple.v().newIfStmt(condExpr, afterIf);

        //logger.debug("condStmt {} ", condStmt);
        units.insertAfter(condStmt, beforeIf);

        logger.info("addView(): done ");
        logger.info(" ");
        return true;
    }

    /**
     * create field public static String String_XYZ;
     * @param intId
     * @return
     */
    private SootField resolveStringMember(Integer intId) {
        String   idName    = makeIdName("String", intId); 
        String   className = makeClassName("java.lang.String");

        if (mNumberToIDMap == null) {
            logger.warn("mNumberToIDMap not set");
        	return null;
        }
        if (!mNumberToIDMap.containsKey(intId)) {
            logger.warn("ID {} is not in the resource info ", String.format("%x", intId));
            return null;
        }

        SootField sf = null;

        try {
            sf = mSootClass.getFieldByName(idName);
        }
        catch (Exception ex ) {
            RefType  classType = RefType.v(className);         
            // step 1: create sootfield for member variable
            sf = new SootField(idName, classType, Modifier.PUBLIC | Modifier.STATIC);
            mSootClass.addField(sf);
            logger.info("added field: {}", sf);
        }

        logger.info("Field name {} resolved", idName);

        return sf;
    }

    /**
     * retrieve the getCharSequence_ID method, create one if does not exist
     * @param intId
     * @return
     */
    public SootMethod retrieveGetCharSequence_ID(Integer intId) {
        String methodName = "getCharSequence_" + String.format("%x", intId);

        SootMethod method = null;

        try {
            method = mSootClass.getMethodByName(methodName);
            return method;
        } 
        catch (Exception ex){
            return addGetCharSequence_ID(intId);
        }
    }

    /**
     * add getStringOrText_ID()
     * @param intId
     * @return
     */
    public SootMethod addGetCharSequence_ID(Integer intId) {
    	if (intId == null) {
            logger.warn("addGetCharSequence_ID null ");
            return null;
    	}
        SootField sootField = resolveStringMember(intId); 
        if (sootField == null)  {
            logger.warn("Cannot create String field for {} ", String.format("%x", intId));
            return null;
        }		    

        String stringName = mNumberToIDMap.get(intId);

        Set<RString> rvalueSet = mStringToValueSet.get(stringName);

        if (rvalueSet == null || rvalueSet.size() == 0) {
            logger.warn("String {} has no values ", stringName);
            return null;
        }

        List<Type> params = new LinkedList<Type>();

        RefType returnType = (RefType) sootField.getType(); 

        String methodName = "getCharSequence_" + String.format("%x", intId);

        SootMethod method = null;

        try {
            method = mSootClass.getMethodByName(methodName);
        } 
        catch(Exception ex) {

        }

        if (method != null)
            return method;

        //instantiate a method
        method = new SootMethod(methodName, params, returnType, 
            Modifier.PUBLIC | Modifier.STATIC);

        // add the method to the class
        mSootClass.addMethod(method);
        API.v().addSafeMethod(method);

        // create active body, and set the body active
        JimpleBody body = Jimple.v().newBody(method);
        method.setActiveBody(body);

        Chain<Unit> units = body.getUnits();

    	// local Argument for view
    	Local localString = Jimple.v().newLocal("_$localString",  returnType);
    	body.getLocals().add(localString);
    
        FieldRef  fieldRef = Jimple.v().newStaticFieldRef(sootField.makeRef());

        // localString =  fieldRef
        units.add(Jimple.v().newAssignStmt(localString, fieldRef));

        // beforeIF block
        Stmt beforeIf = (Stmt) units.getLast();

        // IF block: adding more code for if block
        if (!SootUtils.isStringType(returnType)) {
            Expr newExpr = Jimple.v().newNewExpr((RefType)returnType);
            units.add(Jimple.v().newAssignStmt(localString, newExpr));
        }

        for (RString rstring: rvalueSet) {
            units.add(Jimple.v().newAssignStmt(localString, StringConstant.v(rstring.value)));
            units.add(Jimple.v().newAssignStmt(fieldRef, localString));
        }

        units.add(Jimple.v().newAssignStmt(localString, fieldRef));

        //generate code for goto (if localString != null)

        // afterIF: return localView
        Stmt afterIf = Jimple.v().newReturnStmt(localString);
        units.add(afterIf);

        // condition expression and statement (not equal expr)
        ConditionExpr condExpr = Jimple.v().newNeExpr(localString, NullConstant.v());

        // condition statement
        Stmt condStmt =  Jimple.v().newIfStmt(condExpr, afterIf);

        // put in if conditionalblock right after beforeIf
        logger.debug("condStmt {} ", condStmt);
        units.insertAfter(condStmt, beforeIf);

        return method;
    }


    /**
     * get all possible values of string given by a strId
     * @param strId
     * @return
     */
    public Set<String> getStringValues(Integer intId) {
        String stringName = mNumberToIDMap.get(intId);
        Set<RString> rvalueSet = mStringToValueSet.get(stringName);
        Set<String> valueSet = new HashSet<String>();

        if (rvalueSet == null || rvalueSet.size() == 0) {
            logger.warn("String {} has no values ", stringName);
            return valueSet;
        }

        for (RString rstring: rvalueSet) {
            valueSet.add(rstring.value);
        }
        return valueSet;
    }

    /*****************************************************************************
     *                               Utility/helper Functions 
     ******************************************************************************/
    /**
     * Function to form a Id based on type and Id
     * @param type
     * @param numId
     * @return
     */
    private String makeIdName(String type, int numId) {
        String[] tokens = type.split("[.]", 8);

        String shortType = type;
        if (tokens.length > 1)
            shortType = tokens[tokens.length - 1];

        StringBuilder builder = new StringBuilder(shortType);
        builder.append("_").append(String.format("%x", numId));
        return builder.toString();
    }

    /**
     * function to form a valid class name used for soot lookup
     * @param className
     * @return
     */
    private String makeClassName(String className) {
        String[] tokens = className.split("[.]", 8);
        /* if the name already contains full qualified path, keep it */

        if (tokens.length > 1)
            return className;
        if (className.equals("View")) {
            return "android.view.View";
        }

        className = String.format("%s%s", className.substring(0, 1).toUpperCase(), 
            className.substring(1));

        String[] basePackages = new String[] {
                                              "android.widget",
                                              "android.app",
                                              "android.view",
                                              "android.support.v4.app"
        };

        for (String packageName: basePackages) {
            StringBuilder builder = new StringBuilder(packageName);
            builder.append(".");

            builder.append(className.charAt(0));

            if (className.length() > 1)
                builder.append(className.substring(1));

            String fullName = builder.toString();

            logger.info("Trying to locatate {} class ", fullName);

            if (Scene.v().containsClass(fullName))
            {
                logger.info("Found class {} ", fullName);
                return fullName;
            }

            logger.info("class {} NOT Found ", fullName);
            logger.info("Trying to match class {} NOT Found ", className);

            // Now we are trying to match
            List<SootClass> classes = SootUtils.matchShortName(className);

            for (SootClass sootClass: classes) {
                logger.info("matching {} ", sootClass);
                for (SootClass baseClass: mBaseClassList) { 
                    try {
                        if (SootUtils.checkAncestor(sootClass, baseClass)) {
                            logger.info("soot class {} is a UI component", sootClass);
                            return sootClass.toString();
                        }
                    }
                    catch (Exception ex) {
                        // logger.debug("soot class {} is not available ", baseClass);
                        // logger.debug("ex: {} ", ex);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Write to the file
     */
    public void writeFile(String dir)  {

        String filePath = dir + File.separator + mSootClass.toString();
        PrintWriter writer;

        try {
            writer = new PrintWriter(filePath + "_steps.jimple");
        }
        catch (Exception ex) {
            logger.warn("Cannot open file {} ", filePath);
            return;
        }

        writer.printf("class %s { \n", mSootClass.toString());
        writer.println("");

        logger.info("fields ");
        for (SootField field : mSootClass.getFields()) {
            logger.info("field {} ", field);    
            writer.printf("\t%s %s \n", Modifier.toString(field.getModifiers()), field);
        }

        logger.info("methods ");
        writer.println("");
        for (SootMethod method: mSootClass.getMethods()) {
            Body body = method.getActiveBody();

            logger.info("====== method {} ======== ", method);    
            writer.printf("\t%s %s { \n", 
                Modifier.toString(method.getModifiers()), method);

            for (Local local: body.getLocals()) {
                logger.info("{}", local);
                writer.printf("\t\t%s %s \n", local.getType(), local);
            }

            writer.println("");
            Chain<Unit> units = body.getUnits();
            for (Unit unit: units) {
                String unitString;
                try {
                    unitString = unit.toString();
                } catch(Exception ex) {
                    unitString = "***Invalid Statement ";
                }
                writer.printf("\t\t%s \n", unitString);
                logger.info("{} ", unitString);
            }

            writer.printf("\t} \n");
            logger.info("");
        }
        writer.printf("} \n");
        writer.flush();
    }

    /**
     * add runAllClicks into holder class
     */



    /****************************************************************************
     *                           To delete
     ****************************************************************************/

    /**
     * addClinitMethod:
     *   add the static constructor (class constructor)
     */
    private void addClinitMethod() {
        mClinitMethod = new SootMethod("<clinit>", new LinkedList(), VoidType.v(), Modifier.STATIC);
        mSootClass.addMethod(mClinitMethod);

        /*
         * adding clinit method for the class
         */
        mClinitBody = Jimple.v().newBody(mClinitMethod);

        /* The active body to work on is mClinitBody */
        mClinitMethod.setActiveBody(mClinitBody);
    }
}
