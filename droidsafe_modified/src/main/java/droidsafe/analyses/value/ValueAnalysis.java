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

package droidsafe.analyses.value;

import droidsafe.analyses.pta.PTABridge;
import droidsafe.analyses.pta.PointsToAnalysisPackage;
import droidsafe.analyses.value.primitives.ClassVAModel;
import droidsafe.analyses.value.primitives.StringVAModel;
import droidsafe.android.app.Project;
import droidsafe.android.system.API;
import droidsafe.main.Config;
import droidsafe.speclang.Method;
import droidsafe.transforms.JSAResultInjection;
import droidsafe.utils.AllocNodeEquality;
import droidsafe.utils.ParameterizedLinkedHashMap;
import droidsafe.utils.SootUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import soot.jimple.AssignStmt;
import soot.jimple.ClassConstant;
import soot.jimple.Constant;
import soot.jimple.DoubleConstant;
import soot.jimple.FloatConstant;
import soot.jimple.InstanceFieldRef;
import soot.jimple.IntConstant;
import soot.jimple.LongConstant;
import soot.jimple.StringConstant;
import soot.jimple.Stmt;
import soot.jimple.spark.pag.AllocNode;
import soot.jimple.spark.pag.ObjectSensitiveAllocNode;
import soot.jimple.spark.pag.StringConstantByMethod;
import soot.jimple.spark.pag.StringConstantNode;
import soot.jimple.toolkits.callgraph.Edge;
import soot.jimple.toolkits.pta.IAllocNode;
import soot.jimple.toolkits.pta.IStringConstantNode;
import soot.Context;
import soot.MethodOrMethodContext;
import soot.PrimType;
import soot.RefType;
import soot.Scene;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.Type;
import soot.Value;

/** 
 * Determine soundly and conservatively the possible values of fields of security sensitive  Android objects such as 
 * Intents, Strings, and Uris.
 *
 * This will make it easier for an analyst to determine if a particular security-sensitive API call that one of these
 * objects interacts with is used maliciously by providing the necessary context.
 * 
 * @author dpetters
 */
public class ValueAnalysis  {
    //if true then for string values, only track jsa resolved strings (so hot spot strings)
    public static final boolean ONLY_TRACK_JSA_STRINGS = true;

    public static final boolean USE_PTA_ONLY = true;

    /** Singleton for analysis */
    private static ValueAnalysis am;

    /** 
     * keys are the objects that we can and want to model (they are new expressions) 
     * The value is the Model object which simulates that object. 
     */
    private Map<IAllocNode, RefVAModel> allocNodeToVAModelMap; 

    private Set<Type> vaModeledTypesAndParents;

    private Map<SootClass, Set<SootField>> classesAndFieldsToModel;

    /** va errors file used to help figure out what to model */
    public static FileWriter vaErrorsLog;

    /** va stats file */
    public static FileWriter vaResultsLog;

    /** Generic logger */
    private static final Logger logger = LoggerFactory.getLogger(ValueAnalysis.class);

    /** The package of the value analysis models */
    public static final String MODEL_PACKAGE = "droidsafe.analyses.value.VAResultContainerClasses";

    /** The prefix that is prepended to fully qualified value analysis models */
    public static final String MODEL_PACKAGE_PREFIX = MODEL_PACKAGE + ".";
    /** list of base classes that we will model taken from the annotations in the source code. 
     * Can be queried before va is run or created.
     */
    private static Set<SootClass> baseClassesToModel;

    /** Private constructor to enforce singleton pattern */
    private ValueAnalysis() {
        // Use custom map so the results can be queried after additional pta runs
        this.allocNodeToVAModelMap = new ParameterizedLinkedHashMap<IAllocNode, RefVAModel>(AllocNodeEquality.v()); // new LinkedHashMap<IAllocNode, RefVAModel>();
        this.classesAndFieldsToModel = VAResultContainerClassGenerator.getClassesAndFieldsToModel(true);
        this.vaModeledTypesAndParents = new HashSet<Type>();
    }

    /**
     * Before VA is run, this method will all base classes that will be tracked (have at least one field 
     * tracked by VA).
     */
    public static Set<SootClass> baseClassesToModel() {
        if (baseClassesToModel == null) {
            baseClassesToModel = 
                    VAResultContainerClassGenerator.getClassesAndFieldsToModel(false).keySet();
        }

        return baseClassesToModel;
    }

    /**
     * Getter for analysis result.  AllocNode -> VAModel.
     */
    public Map<IAllocNode, RefVAModel> getResults() {
        return this.allocNodeToVAModelMap;
    }

    /**
     * Getter for analysis result
     */
    public Map<SootClass, Set<SootField>> getClassesAndFieldsToModel() {
        return this.classesAndFieldsToModel;
    }

    /**
     * Return true if this alloc node has an analysis result (and is not invalidated)
     */
    public boolean hasResult(IAllocNode node) {
        return this.allocNodeToVAModelMap.containsKey(node) &&
                !this.allocNodeToVAModelMap.get(node).invalidated();
    }

    /**
     * Return the ModeledObject result for a given alloc node.
     */
    public VAModel getResult(IAllocNode node) {
        return this.allocNodeToVAModelMap.get(node);
    }

    /** access to the singleton instance */
    public static ValueAnalysis v() {
        if (am == null)
            am = new ValueAnalysis();

        return am;
    }
    
    /**
     * For a string field of a modeled alloc node, get resolved string values.
     * Store valuse in arg <resolvedValues>. 
     * Return false if field is invalidated or otherwise could not be resolved.
     *   
     */
    public boolean getStringFieldValues(IAllocNode node, SootField field, Set<String> resolvedValues) {
        VAModel model = getResult(node);
        if (model instanceof RefVAModel) {
            RefVAModel refVAModel = (RefVAModel)model;
            
            Set<VAModel> fldVAModels = refVAModel.getFieldVAModels(field);
            for(VAModel fldVAModel : fldVAModels) {
                                              
                if (fldVAModel.invalidated() || (!(fldVAModel instanceof StringVAModel))){
                    return false;
                } 
                
                //if we get here, it is a resolved string va model value
                for (String str : ((StringVAModel)fldVAModel).getValues()) {
                    resolvedValues.add(str);
                }
            }
            return true;
        }
        
        return false;
    }

    /** run the analysis to fixed point */
    public static void run() {
        if (PTABridge.v() == null) {
            logger.error("The GeoPTA pass has not been run. Value analysis requires it.");
            droidsafe.main.Main.exit(1);
        }      

        //create singleton
        v();

        try {
            vaErrorsLog = new FileWriter(Project.v().getOutputDir() + File.separator 
                + "va-errors.log");
        } catch (Exception e) {
            logger.warn("Unable to open va-errors.log:", e);
        }

        try {
            vaResultsLog = new FileWriter(Project.v().getOutputDir() + File.separator 
                + "va-results.log");
        } catch (Exception e) {
            logger.warn("Unable to open va-results.log: ", e);
        }

        am.createObjectModels();

        if (USE_PTA_ONLY)
            am.collectPTAResults();
        else 
            am.visitMethodContexts();

        if (Config.v().debug)
            am.logResults();

        try {
            vaErrorsLog.close();
        } catch (IOException e){
            logger.warn("Unable to close the va-errors.log file.", e);
        }

        try {
            vaResultsLog.close();
        } catch (IOException e){
            logger.warn("Unable to close the va-results.log file.", e);
        }
    }

    public void createObjectModels() {
        for(IAllocNode allocNode : PTABridge.v().getAllAllocNodes()) {
            if (allocNode instanceof ObjectSensitiveAllocNode) {
                createObjectModel(allocNode);    
            }
        }
    }

    public void createObjectModel(IAllocNode allocNode) {
        if(!(allocNode.getType() instanceof RefType)) {
            this.logError(allocNode.toString());
            return;
        }
        RefType refType = (RefType)allocNode.getType();

        String errorLogEntry = "Couldn't model an instance of the " + refType.getSootClass().getName() + " ";

        SootClass sootClass = refType.getSootClass();

        Class<?> cls = null;
        while(sootClass.hasSuperclass() && cls == null) {
            try {
                cls = VAUtils.getDroidsafeClass(sootClass);
            } catch(ClassNotFoundException e) {
                errorLogEntry += e.toString();
                this.logError(errorLogEntry);
                sootClass = sootClass.getSuperclass();
            }
        }

        if (cls == null) {
            return;
        }

        RefVAModel model = null;
        Constructor<?> ctor;
        try {
            ctor = cls.getConstructor(IAllocNode.class);
        } catch(NoSuchMethodException e) {
            errorLogEntry += "Available constructors are:\n";
            for (Constructor<?> constructor : cls.getConstructors()){
                errorLogEntry += constructor + "\n";
            }
            errorLogEntry += e.toString();
            logError(errorLogEntry);
            logger.info(errorLogEntry);
            return;
        } catch(SecurityException e) {
            errorLogEntry += e.toString();
            logError(errorLogEntry);
            return;
        }

        try {
            model = (RefVAModel)ctor.newInstance(allocNode);
        } catch(Exception e){
            errorLogEntry += e.toString();
            logError(errorLogEntry);
            logger.info(errorLogEntry);
            return;
        }
        if(model != null) {
            this.allocNodeToVAModelMap.put(allocNode, model);
            RefType refT = (RefType)allocNode.getType();
            this.vaModeledTypesAndParents.add(refT);
            SootClass allocType = refT.getSootClass();
            for (SootClass parent : SootUtils.getParents(allocType)) {
                this.vaModeledTypesAndParents.add(RefType.v(parent));
            }
        }
    }

    private void collectPTAResults() {
        //loop through all alloc nodes in map
        for (IAllocNode an : allocNodeToVAModelMap.keySet()) {
            RefVAModel baseModel = (RefVAModel)allocNodeToVAModelMap.get(an);
            
            if (baseModel == null)
                continue;
            
            //System.out.println(an);
            
            boolean debug = false;//(an.getType().equals(RefType.v("android.widget.Button")));                    
            
            if (debug) System.out.printf("Looking at: %s\n", an);
            
            Class<?> baseVAModelClass = baseModel.getClass();
            
            //for each field with a type of string or classconstant
            for (SootField sootField : baseModel.getFields()) {
                //add values from pta to the model
                String fieldName = sootField.getName();
                
                try {
                    Field field = baseVAModelClass.getField(fieldName);
                    Object fieldObject = field.get(baseModel);
                    
                    VAModel fieldVAModel = (VAModel)fieldObject;
                    
                    Set<? extends IAllocNode> fieldPTSet = PTABridge.v().getPTSet(an, sootField);
                    
                    if (fieldVAModel instanceof StringVAModel) {
                        if (debug) System.out.printf("Found tracked string field: %s\n", fieldName);
                        
                        handleStringFieldValue((StringVAModel)fieldVAModel, fieldPTSet, debug);
                    } else if (fieldVAModel instanceof ClassVAModel) {
                        //if (debug) System.out.printf("Found tracked field: %s\n", fieldName);
                        handleClassFieldValue((ClassVAModel)fieldVAModel, fieldPTSet);
                    }
                } catch (Exception e) {
                    //used to ignore fields that are not tracked by va
                } 
            }
            //if (debug) System.out.println();
        }
    }

    public void visitMethodContexts() {
        for (MethodOrMethodContext momc : PTABridge.v().getReachableMethodContexts()) {
            SootMethod sootMethod = momc.method();
            Context context = momc.context();

            if(!sootMethod.isConcrete())
                continue;

            if(!sootMethod.hasActiveBody()) {
            	try {
            		sootMethod.retrieveActiveBody();
            	}
            	catch (Exception ex) {
            		logger.info("Exception retrieving method body {}", ex);
            		continue;
            	}
            }

            for(Iterator stmts = sootMethod.getActiveBody().getUnits().iterator(); stmts.hasNext();) {
                Stmt stmt = (Stmt) stmts.next();
                if(stmt instanceof AssignStmt) {
                    AssignStmt assignStmt = (AssignStmt)stmt;
                    Value leftOp = assignStmt.getLeftOp();
                    Value rightOp = assignStmt.getRightOp();
                    if(leftOp instanceof InstanceFieldRef) {
                        InstanceFieldRef instanceFieldRef = (InstanceFieldRef)leftOp;
                        Value baseValue = instanceFieldRef.getBase();
                        //use a quick type search to see if we need to look at the pta for this value
                        //if its types is not possibly one that could be tracked, then continue;
                        if (!vaModeledTypesAndParents.contains(baseValue.getType())) {
                            continue;
                        }

                        //this call here is expensive!!
                        Set<? extends IAllocNode> baseAllocNodes = PTABridge.v().getPTSet(baseValue,context);
                        for(IAllocNode allocNode : baseAllocNodes) {
                            boolean debug = allocNode.getType().equals(RefType.v("android.widget.Button"));
                            
                            if (debug)
                                logger.debug("Looking at node: {} in stmt {} in {}", allocNode, stmt, sootMethod);
                            
                            VAModel vaModel = this.allocNodeToVAModelMap.get(allocNode);
                            if(vaModel != null) {
                                Class<?> c = vaModel.getClass();
                                String fieldName = instanceFieldRef.getField().getName();
                                if (debug)
                                    logger.debug("Looking at field: {}", fieldName);
                                
                                try {
                                    Field field = c.getField(fieldName);
                                    try {
                                        Object fieldObject = field.get(vaModel);
                                        VAModel fieldObjectVAModel = (VAModel)fieldObject;
                                        if(fieldObjectVAModel instanceof PrimVAModel) {
                                            PrimVAModel fieldPrimVAModel = (PrimVAModel)fieldObjectVAModel;
                                            if (fieldPrimVAModel instanceof StringVAModel || 
                                                    fieldPrimVAModel instanceof ClassVAModel) {
                                                handleStringOrClass(momc, assignStmt, fieldPrimVAModel, context, debug);
                                            } else  {
                                                //primitive, but not string primitive
                                                if(rightOp instanceof Constant) {
                                                    Constant rightOpConstant = (Constant)rightOp;
                                                    if(rightOpConstant instanceof IntConstant) {
                                                        IntConstant intConstant = (IntConstant)rightOpConstant;
                                                        fieldPrimVAModel.addValue(intConstant.value);
                                                    } else if(rightOpConstant instanceof LongConstant) {
                                                        LongConstant longConstant = (LongConstant)rightOpConstant;
                                                        fieldPrimVAModel.addValue(longConstant.value);
                                                    } else if(rightOpConstant instanceof DoubleConstant) {
                                                        DoubleConstant doubleConstant = (DoubleConstant)rightOpConstant;
                                                        fieldPrimVAModel.addValue(doubleConstant.value);
                                                    } else if(rightOpConstant instanceof FloatConstant) {
                                                        FloatConstant floatConstant = (FloatConstant)rightOpConstant;
                                                        fieldPrimVAModel.addValue(floatConstant.value);
                                                    } else {
                                                        System.out.println("Unhandled constant case: " + 
                                                                rightOpConstant.getClass());
                                                        System.exit(1);
                                                    }
                                                } else { //not a constant or string
                                                    fieldPrimVAModel.invalidate();
                                                }
                                            }
                                        }
                                    } catch(IllegalAccessException e) {
                                    }
                                } catch(NoSuchFieldException e) {
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * Handle case where type for assignment is a string
     */
    private void handleStringOrClass(MethodOrMethodContext momc, AssignStmt assignStmt, 
                                     PrimVAModel fieldPrimVAModel, Context context, boolean debug) {
        //Found string
        Set<? extends IAllocNode> rhsNodes;

        //get the string nodes the rhs expression could possibly point to
        if (assignStmt.getRightOp() instanceof Constant) {
            //if a direct string constant, then get the string constant node from pta
            Set<IAllocNode> nodes = new HashSet<IAllocNode>();
            IAllocNode node = PTABridge.v().getAllocNode(assignStmt.getRightOp(), context); 
            nodes.add(node);            
            rhsNodes = nodes;
        } else {
            //if not a string constant, then query pta
            rhsNodes = PTABridge.v().getPTSet(assignStmt.getRightOp(), context);
        }

        if (fieldPrimVAModel instanceof ClassVAModel)
            handleClassFieldValue((ClassVAModel)fieldPrimVAModel, rhsNodes);
        else if (fieldPrimVAModel instanceof StringVAModel)
            handleStringFieldValue((StringVAModel)fieldPrimVAModel, rhsNodes, debug);
    }
    
    private void handleClassFieldValue(ClassVAModel fieldPrimVAModel, Set<? extends IAllocNode> rhsNodes) {
        for(IAllocNode rhsNode : rhsNodes) {
            boolean knownValue = false;
            
           if (rhsNode.getNewExpr() instanceof ClassConstant) {
                fieldPrimVAModel.addValue(SootUtils.getSootClass((ClassConstant)rhsNode.getNewExpr()));
                //System.out.printf("Found CC: %s\n", SootUtils.getSootClass((ClassConstant)rhsNode.getNewExpr()));
                knownValue = true;
            }
                        
            if (!knownValue) {
                //if (debug) System.out.println("Not known: " + rhsNode);
                // all strings weren't constants, write unknown value
                ValueAnalysis.logError(fieldPrimVAModel.toString() + 
                    " the value it is assigned, " + 
                    rhsNode + " is not a constant. Contained values beforehand: " + 
                    fieldPrimVAModel.getValues());
                fieldPrimVAModel.invalidate();
            }
        }   
    }
    
    private void handleStringFieldValue(StringVAModel fieldPrimVAModel, Set<? extends IAllocNode> rhsNodes, boolean debug) {
        for(IAllocNode rhsNode : rhsNodes) {
            if (debug) System.out.println("rhs value: " + rhsNode); 
            boolean knownValue = false;
            
            if (PTABridge.isStringConstant(rhsNode)) {
                //System.out.println(rhsNode);
                //logger.info("handleString: {}", rhsNode.getMethod());
                StringConstant sc = ((StringConstantByMethod)rhsNode.getNewExpr()).getStringConstant();
                //are we tracking all strings, or just the strings injected by jsa for api calls in user code
                if (!ONLY_TRACK_JSA_STRINGS || JSAResultInjection.trackedStringConstants.contains(sc) || rhsNodes.size() <= 5) {
                    String value = sc.value;
                    //if (debug) System.out.println("Found sc: " + value);
                    value = value.replaceAll("(\\r|\\n)", "");
                    value = value.replace("\"", "");
                    value = value.replace("\\uxxxx", "");
                    fieldPrimVAModel.addValue(value);
                    knownValue = true;
                    if (debug) System.out.println("Known value");
                }
            } 
                        
            if (!knownValue) {
                //if (debug) System.out.println("Not known: " + rhsNode);
                // all strings weren't constants, write unknown value
                ValueAnalysis.logError(fieldPrimVAModel.toString() + 
                    " the value it is assigned, " + 
                    rhsNode + " is not a constant. Contained values beforehand: " + 
                    fieldPrimVAModel.getValues());
                fieldPrimVAModel.invalidate();
            }
        }   
    }

    /**
     * Helper method to write to the file where we log all errors we encounter during value analysis
     */
    public static void logError(String logEntry) {
        /*
        try {
            vaErrorsLog.write(logEntry + "\n");
        } catch (IOException ioe) {
            logger.warn("Unable to write to the va-errors.log file.", ioe);
        }
         */
    }

    /**
     * Helper method to write to the file where we log all the value analysis statistics
     */
    public static void logResult(String logEntry) {
        try {
            vaResultsLog.write(logEntry + "\n");
        } catch (IOException ioe) {
            logger.warn("Unable to write to the va-results.log file.", ioe);
        }
    }

    /**
     * Log the results of the modeling
     */
    private void logResults() {
        logResult("VA Results: \n\n");
        for(Map.Entry<IAllocNode, RefVAModel> entry : allocNodeToVAModelMap.entrySet()) {
            logResult("AllocNode: " + entry.getKey());
            logResult("Model: " + entry.getValue().toStringPretty());
        }
    }
    
    /**
     * Return true if VA handles the type as a primitive, so true for primitives, strings, and java.lang.Class.
     */
    public static boolean handleAsPrimType(Type type) {
        if (type instanceof RefType) {
            return SootUtils.isStringOrSimilarType(type) || type.equals(RefType.v("java.lang.Class"));
        } else if (type instanceof PrimType) {
            return true;
        } else 
            return false;
        
        
    }
}
