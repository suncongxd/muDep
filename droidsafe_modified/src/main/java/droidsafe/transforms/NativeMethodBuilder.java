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

import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

import droidsafe.analyses.value.ValueAnalysis;
import droidsafe.android.app.Project;
import droidsafe.android.app.resources.Resources;
import droidsafe.main.Config;
import droidsafe.speclang.StringValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import droidsafe.analyses.interapp.SourceFlow;
import droidsafe.android.system.API;
import droidsafe.utils.SootUtils;
import soot.*;
import soot.jimple.*;
import soot.tagkit.AnnotationTag;
import soot.util.Chain;
import stub.generation.pojo.Methoder;
import stub.generation.service.FlagAnalysis;
import stub.generation.utils.FileUtils;

/**
 * For native methods defined in app src code, create a body that just return a new
 * value of the return type.  For primitives just return a local that is created.  For
 * reference types, return a new object of the type.  For arrays, create a one element array
 * and set the element equal to a new value.
 * 
 * 
 * @author mgordon
 *
 */
public class NativeMethodBuilder {
    private final static Logger logger = LoggerFactory.getLogger(NativeMethodBuilder.class);
    
    private static NativeMethodBuilder v;
    private List<SourceFlow> flows;
    public static int localID = 0;

    private Set<SootMethod> wasNative = new HashSet<SootMethod>();

    public Set<SootMethod> getWasAppNative() {
        return wasAppNative;
    }

    private Set<SootMethod> wasAppNative = new HashSet<SootMethod>();
    FlagAnalysis flagAnalysis =  new FlagAnalysis();

    public static NativeMethodBuilder v() {
        if (v == null)
            v = new NativeMethodBuilder();
        
        return v;
    }
    
    private NativeMethodBuilder() {
        // TODO Auto-generated constructor stub
    }
    
    public void run() {
        Map<String, Set<String>> ssMap = Project.v().getSSNativeMap();
        String flagPath = Project.v().getFlagPath();
        String flagContent = FileUtils.readToString(flagPath);
        List<Methoder> methoders =  FileUtils.getMethod(flagContent, Methoder.class);

        for (SootClass clz : Scene.v().getClasses()) {
            if (!API.v().isSystemClass(clz)) {
                for (SootMethod method : clz.getMethods()) {
                    if(Pattern.matches("addTaintFor.*Fields", method.getName())){//java方法,正则表达式匹配，void addTaintForXXXFields()。用于对source的返回值和参数的字段加污点
                        Body body = method.getActiveBody();
                        body.getUnits().removeLast();//remove return stmt

                        Local para = body.getParameterLocal(0);

                        //new para的字段 string = new String() 字段类型为调用source的返回值类型。由于特殊处理set_field_from_native以及native_heap_modified，调用的source的返回值都是String,这里硬编码返回值类型为java.lang.String
                        SootClass strClx = Scene.v().getSootClass("java.lang.String");
                        Local newStr = Jimple.v().newLocal("_$string_"+ strClx.getType()+ "_"+ localID++  , strClx.getType());
                        initializeRef(body, strClx.getType(), newStr);

                        // Local taint = para.getTaint()
                        SootClass dsTaintObjClx = Scene.v().getSootClass("java.lang.DSTaintObject");
                        Local taint = Jimple.v().newLocal("_$taint_"+ "java.lang.DSTaintObject"+ "_"+ localID++  , dsTaintObjClx.getType());
                        body.getLocals().add(taint);
                        SootMethod getTaintMth  = Scene.v().getMethod("<java.lang.Object: java.lang.DSTaintObject getTaint()>");
                        body.getUnits().add(Jimple.v().newAssignStmt( taint , Jimple.v().newVirtualInvokeExpr(para, getTaintMth.makeRef() )));

                        //string.addTaint(taint)
                        String addTaintSig = "<java.lang.Object: void addTaint(java.lang.DSTaintObject)>";
                        SootMethod addTaintMth =  Scene.v().getMethod(addTaintSig);
                        body.getUnits().add(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(newStr, addTaintMth.makeRef(),
                                taint )));

                        //para.field = string  para的String类型字段
                        List<Value> matchFields = new ArrayList<>();
                        recursive(para, "java.lang.String", body, matchFields);
                        for (Value v: matchFields)
                            body.getUnits().add(Jimple.v().newAssignStmt( v, newStr));


                        Type rType = method.getReturnType();
                        Local r = Jimple.v().newLocal("_$return_" + localID++, rType);
                        //添加return语句
                        if (rType instanceof VoidType) {
                            body.getUnits().add(Jimple.v().newReturnVoidStmt());
                        } else {
                            body.getUnits().add(Jimple.v().newReturnStmt(r));
                        }

                        method.setActiveBody(body);
                        System.out.println(method.getActiveBody());
                    }
//                    if(method.getName().equals("leakImei")){
//                        final Chain<Unit> units = method.getActiveBody().getUnits();
//                        Unit firstUnit = units.getFirst();
//                        Unit secondUnit = units.getSuccOf(firstUnit);
//                        Unit thirdUnit = units.getSuccOf(secondUnit);
//                        Unit forthUnit = units.getSuccOf(thirdUnit);
//                        final Unit fifthUnit = units.getSuccOf(forthUnit);
//
//                        AbstractStmtSwitch stmtSwitch = new AbstractStmtSwitch() {
//                            @Override
//                            public void caseAssignStmt(AssignStmt stmt) {
//                                //if statment
//                                Value r2 = stmt.getLeftOp();
//                                ConditionExpr eqCondition = Jimple.v().newEqExpr(r2, NullConstant.v());
////                        Local a = newLocal(newBody, newValue);
//                                SootMethod sm = Scene.v().getMethod("<android.util.Log: int d(java.lang.String,java.lang.String)>");
//                                List args = new ArrayList<>();
//                                Stmt afterIf = Jimple.v().newInvokeStmt(Jimple.v().newStaticInvokeExpr(sm.makeRef(), StringConstant.v("if print") , r2));
//                                units.insertBefore(afterIf, fifthUnit );
//                                units.insertBefore((Jimple.v().newIfStmt(eqCondition,afterIf)), fifthUnit);
//                            }
//                        };
//                        forthUnit.apply(stmtSwitch);
//                        System.out.println(method.getActiveBody());
//                    }

                    addSourceTagOrSinkTag(method, ssMap);

                    if (method.isNative() ) {

                        Date startTime = new Date();

                        flagAnalysis.buildBody( method, methoders);

                        double elapsedTime = (new Date().getTime()-startTime.getTime()) / 1000;
                        FileUtils.append(Project.v().getOutputDir() + File.separator + "mutdep_runtime.txt",
                                "stub_gen_time: "  + String.format("%.9f",elapsedTime));
                        //write stub file
                        FileUtils.append(Project.v().getOutputDir() + File.separator + "native_stub.txt",
                                method.getActiveBody().toString());
                    }
                }
            }
        }
        // write .so scan time
        if(ssMap == null || ssMap.size() == 0) return;
        double sumTime = 0;
        for(String time: ssMap.get("runtime")){
            sumTime += Double.parseDouble(time);
        }
        FileUtils.append(Project.v().getOutputDir() + File.separator + "mutdep_runtime.txt",
                "so_scan_time: "+ String.format("%.9f",sumTime));
    }

    private void recursive(Value base, String str, Body newBody, List<Value> mathches){
        SootClass baseClass = Scene.v().getSootClass(base.getType().toString());
        for(SootField sf : baseClass.getFields()){
            if(sf.getType().toString().equals(str)){
                mathches.add(Jimple.v().newInstanceFieldRef(base, sf.makeRef()));
            }
            else{
                if(sf.getType() instanceof RefType){
                    //new Field
                    SootClass fieldClx = Scene.v().getSootClass(sf.getType().toString());
                    Local field = Jimple.v().newLocal("_$new_field_"+ sf.getType()+ "_"+ localID++  , sf.getType());
                    initializeRef(newBody, (RefType)sf.getType(), field);

//                    Local ref = Jimple.v().newLocal("_$instanceField_"+sf.getType() + "_"+ localID++  , sf.getType());
//                    newBody.getLocals().add(ref);
                    newBody.getUnits().add(Jimple.v().newAssignStmt(Jimple.v().newInstanceFieldRef(base, sf.makeRef()), field));
                    recursive(field, str,newBody, mathches);
                }
            }
        }
    }


    private void addSourceTagOrSinkTag(SootMethod method, Map<String, Set<String>> ssMap) {
        if(ssMap == null || ssMap.size() == 0) return;

        Set<String> sources = ssMap.get("source");
        Set<String> sinks = ssMap.get("sink");
        Set<String> logs = ssMap.get("log");
        //需要映射本地方法名对应的本地函数名。两种方式，先使用第一种方式
        //默认转换规则
        String JNIFuncName = getJNIFunctionName(method.getDeclaringClass().getName(), method.getName());//方法的全称路径
//        System.out.println("JNIFuncName: " + JNIFuncName);

        //得到infokind
        for(String s: sources){
            String soMethod = s;
            if(s.contains("dynamic_register#")){//动态注册
                soMethod  = s.split("#")[1];//方法名 + 二进制参数名 + @kind
//                logger.info("JNIFuncSig: {}",str);
                JNIFuncName =  method.getName() + "("+ method.getBytecodeParms() + ")";
//                logger.info("dynamic register JNIFuncName(source): {}", JNIFuncName);
            }

            if(soMethod.contains(JNIFuncName)){
                logger.info("adding source: {} ",JNIFuncName);
                String infoKind = s.split("@")[1];
                addSource(method, infoKind);
            }
        }
        for(String s: sinks){
            String soMethod = s;
            if(s.contains("dynamic_register#")){//动态注册
                soMethod  = s.split("#")[1];//方法名 + 二进制参数名
//                logger.info("JNIFuncSig: {}",str);
                JNIFuncName =  method.getName() + "("+ method.getBytecodeParms() + ")";
//                logger.info("dynamic JNIFuncName(sink): {}", JNIFuncName);
            }

            if(soMethod.contains(JNIFuncName)){//默认转换规则
                logger.info("adding sink :{}", JNIFuncName);
                String infoKind = s.split("@")[1];
                addSink(method, infoKind);
            }
        }

        for(String s: logs){
            String soMethod = s;
            if(s.contains("dynamic_register#")){//动态注册
                soMethod  = s.split("#")[1];//方法名 + 二进制参数名
//                logger.info("JNIFuncSig: {}",str);
                JNIFuncName =  method.getName() + "("+ method.getBytecodeParms() + ")";
//                logger.info("dynamic JNIFuncName(log): {}", JNIFuncName);
            }

            if(soMethod.contains(JNIFuncName)){//默认转换规则
                logger.info("adding log sink: {}", JNIFuncName);
                addSink(method, "NATIVE_LOG");
            }
        }
    }


    public static String getJNIFunctionName(String ClassName,String methodName){
        String classNamePart =ClassName.replaceAll("_", "_1").replace('.', '_');
        String methodNamePart = methodName.replaceAll("_", "_1");
        return "Java_" + classNamePart + "_" +  methodNamePart;

    }

    public void addSink(SootMethod method, String infoKind) {
        API.v().addSinkInfoKind(method, infoKind,true);//add to sinklist    @DSSink({DSSinkKind.LOG})
        API.v().getAllSysMethods().addMethod(method);
        System.out.println("将本地方法添加到sink中:" + method);
        FileUtils.append(Project.v().getOutputDir() + File.separator + "add_java_sourcesinks.txt",
                "add sink: " + method.getSignature());
    }

    public void addSource(SootMethod method, String infoKind) {
        wasAppNative.add(method);
        API.v().addSrcInfoKind(method, infoKind,true);//add to sinklist    @DSSink({DSSinkKind.LOG})
        API.v().getAllSysMethods().addMethod(method);
        System.out.println("将本地方法添加到source中:" + method);
        FileUtils.append(Project.v().getOutputDir() + File.separator + "add_java_sourcesinks.txt",
                "add source: " + method.getSignature());
    }

    public boolean wasNativeAppMethod(SootMethod method) {
        return wasNative.contains(method);
    }

    public boolean isNativeAppMethod(SootMethod method) {
        return wasAppNative.contains(method);
    }

    private void buildBody(SootMethod method) {
        //create new body that just returns a new string
        Body newBody = Jimple.v().newBody();
        
        //create param identity statements
        for (int i = 0; i < method.getParameterCount(); i++) {
            Local p = Jimple.v().newLocal("_$native_builder_" + localID++, method.getParameterType(i));
            newBody.getLocals().add(p);
            newBody.getUnits().add(Jimple.v().newIdentityStmt(p, Jimple.v().newParameterRef(method.getParameterType(i), i)));            
        }
        
        //return new value
        Type rType = method.getReturnType();
        Local r = Jimple.v().newLocal("_$native_builder_" + localID++, rType);
        
        initializeValue(newBody, rType, r);
        
        if (rType instanceof VoidType) {
            newBody.getUnits().add(Jimple.v().newReturnVoidStmt());
        } else {
            newBody.getUnits().add(Jimple.v().newReturnStmt(r));
        }
              
        logger.info("Implementing app native method: {}", method);
        //turn off native modifier
        method.setModifiers(method.getModifiers() ^ soot.Modifier.NATIVE);
        newBody.setMethod(method);
        method.setActiveBody(newBody);
        wasNative.add(method);

    }
    
    public void initializeValue(Body newBody, Type rType, Local r) {
        if (rType instanceof PrimType) {
            //if prim, then just add local to the body
            newBody.getLocals().add(r);           
        } else if (rType instanceof RefType) {
            initializeRef(newBody, (RefType)rType, r);            
        } else if (rType instanceof ArrayType) {
            initializeArray(newBody, (ArrayType)rType, r);            
        } else {
            //nothing to do
            return;
        }
    }
    
    public void initializeRef(Body newBody, RefType type, Local r) {
        newBody.getLocals().add(r);
        
        SootClass clz = type.getSootClass();
        //if an interface, find a direct implementor of and instantiate that...
        if (!clz.isConcrete()) {
            clz = SootUtils.getCloseConcrete(clz);
        }

        if (clz ==  null) {
            //if clz is null, then we have an interface with no known implementors, 
            //so just pass null
            logger.warn("Cannot find any known implementors of {} when adding dummy object", 
                type.getSootClass());
            return;
        }
        
        UnmodeledGeneratedClasses.installNoArgConstructor(clz);
        
        //field and add creation of object
        if (!clz.declaresMethod(UnmodeledGeneratedClasses.noArgConsSubSig)) {
            logger.warn("Error during native simulation. Class {} does not have a no arg constructor.", clz);
            return;
        }

        //local = new Clone()
        //local.init();
        //field = local
        newBody.getUnits().add(Jimple.v().newAssignStmt(r, Jimple.v().newNewExpr(clz.getType())));
        newBody.getUnits().add(Jimple.v().newInvokeStmt(Jimple.v().newSpecialInvokeExpr(r, 
            clz.getMethod(UnmodeledGeneratedClasses.noArgConsSubSig).makeRef())));                
    }

    public void initializeArray(Body newBody, ArrayType type, Local arrayLocal) {
        Type baseType = type.getArrayElementType();
        Local baseValue = Jimple.v().newLocal("_$native_builder_" + localID++, baseType);
        initializeValue(newBody, baseType, baseValue);
        
        //add local for array
        newBody.getLocals().add(arrayLocal);

        if (type.numDimensions > 1) {
            //multiple dimensions, have to do some crap...
            List<Value> ones = new LinkedList<Value>();
            for (int i = 0; i < type.numDimensions; i++)
                ones.add(IntConstant.v(1));

            newBody.getUnits().add(Jimple.v().newAssignStmt(arrayLocal,
                Jimple.v().newNewMultiArrayExpr(type, ones)));
        } else {
            //single dimension, add new expression
            newBody.getUnits().add(Jimple.v().newAssignStmt(arrayLocal, 
                Jimple.v().newNewArrayExpr(baseType, IntConstant.v(1))));
        }

        //get down to an element through the dimensions
        Local elementPtr = arrayLocal;
        while (((ArrayType)elementPtr.getType()).getElementType() instanceof ArrayType) {
            Local currentLocal = Jimple.v().newLocal("_$MULTIARRAY" + localID++, ((ArrayType)elementPtr).getElementType());
            newBody.getUnits().add(Jimple.v().newAssignStmt(
                currentLocal, 
                Jimple.v().newArrayRef(elementPtr, IntConstant.v(0))));
            elementPtr = currentLocal;
        }        

        //assign the new local to the array access
        newBody.getUnits().add(Jimple.v().newAssignStmt(
            Jimple.v().newArrayRef(elementPtr, IntConstant.v(0)), 
            baseValue)); 
    }
}
