package stub.generation.service;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import droidsafe.transforms.NativeMethodBuilder;
import droidsafe.transforms.UnmodeledGeneratedClasses;
import soot.*;
import soot.jimple.*;
import stub.generation.common.BooleanConstant;
import stub.generation.common.ByteConstant;
import stub.generation.common.CharConstant;
import stub.generation.common.ShortConstant;
import stub.generation.pojo.*;
import stub.generation.utils.*;


public class FlagAnalysis {
	private final static Logger logger = LoggerFactory.getLogger(NativeMethodBuilder.class);
    private static  int LOCAL_ID = 0;
    private static int localID = 0;
    private Map<Local, Value> outputs = new HashMap();

	public void buildBody( SootMethod method, List<Methoder> methoders){
        localID = 0;
        outputs.clear();
        if (methoders == null || methoders.size() == 0) {
            buildBodyCore(method,null);
            return;
        }

        for(Methoder m : methoders){
            if(method.getSignature().equals(m.getSignature())){

                System.out.println("native方法与sootMehthod一致:"+method.getSignature());
                buildBodyCore(method, m);
                return;
            }
        }

		buildBodyCore(method,null);
	}

	/**
	 *方式1：构造赋值语句
     * 方式2 ： 构造taint语句
	 */
	private void buildBodyCore(SootMethod sootMethod, Methoder methoder) {

        Body newBody = Jimple.v().newBody();

        Map<String,Local> map = new HashMap();
        //this local
//        if(! Modifier.isStatic(sootMethod.getModifiers())) {
        	Local ths = Jimple.v().newLocal("_$this_" + sootMethod.getDeclaringClass().getName() + "_" + localID++,
                    sootMethod.getDeclaringClass().getType());
        	map.put("this", ths);
        	newBody.getLocals().add(ths);
        	newBody.getUnits().add(Jimple.v().newIdentityStmt(ths, Jimple.v().newThisRef(sootMethod.getDeclaringClass().getType())));
//        }
        //参数 local声明
        for (int i = 0; i < sootMethod.getParameterCount(); i++) {
            Local p = Jimple.v().newLocal("_$para_" + localID++, sootMethod.getParameterType(i));
            map.put("para_" + i, p);
            newBody.getLocals().add(p);
            newBody.getUnits().add(Jimple.v().newIdentityStmt(p, Jimple.v().newParameterRef(sootMethod.getParameterType(i), i)));
        }
        //return local
        Type rType = sootMethod.getReturnType();
        Local r = Jimple.v().newLocal("_$return_" + localID++, rType);
        map.put("return",r);


        createStmt(newBody, methoder ,map, rType, r);


        //添加return语句
        if (rType instanceof VoidType) {
            newBody.getUnits().add(Jimple.v().newReturnVoidStmt());
        } else {
            newBody.getUnits().add(Jimple.v().newReturnStmt(r));
        }


        logger.info("Implementing app native method: {}", sootMethod);

        //turn off native modifier
        sootMethod.setModifiers(sootMethod.getModifiers() ^ soot.Modifier.NATIVE);
        newBody.setMethod(sootMethod);
        sootMethod.setActiveBody(newBody);
        logger.info("native method: {}", sootMethod.getActiveBody());
    }

	private void createStmt(Body newBody, Methoder methoder, Map<String, Local> map, Type rType, Local returnLocal) {

        if (rType instanceof PrimType ) {
            //if prim, then just add local to the body
            newBody.getLocals().add(returnLocal);//
        }
        else if (rType instanceof RefType) {
            NativeMethodBuilder.v().initializeRef(newBody, (RefType)rType, returnLocal);
//            returnTypeIsRef();
        }
        else if (rType instanceof ArrayType) {
            NativeMethodBuilder.v().initializeArray(newBody, (ArrayType)rType, returnLocal);
        }

        //from_native.apk硬编码,返回值foo.data加污染   foo.data.addTaint(new TaintObj()) 检测不到
//        Local ret = map.get("para_1");
//        SootClass retClx = Scene.v().getSootClass(ret.getType().toString());
//        SootField sf = retClx.getFieldByName("data");
//        Type sootFieldT = sf.getType();
//        Local temp = Jimple.v().newLocal("_$field_"+ sootFieldT+ "_"+ localID++  , sootFieldT);
//        newBody.getLocals().add(temp);
//
//        newBody.getUnits().add(Jimple.v().newAssignStmt( temp,Jimple.v().newInstanceFieldRef(ret, sf.makeRef()) ));
//
//        Local newTaint = newInstance(newBody,"java.lang.DSTaintObject","void <init>()",null);
//        String addTaintSig = "<java.lang.Object: void addTaint(java.lang.DSTaintObject)>";
//        SootMethod addTaintMth =  Scene.v().getMethod(addTaintSig);
//        newBody.getUnits().add(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(temp, addTaintMth.makeRef(),
//                newTaint )));


//        setField
//        Local ret = map.get("return");
//        SootClass retClx = Scene.v().getSootClass(ret.getType().toString());
//        SootField sf = retClx.getFieldByName("data");
//        //初始化data字段
//        SootClass strClx = Scene.v().getSootClass("java.lang.String");
//        Local newStr = Jimple.v().newLocal("_$string_"+ strClx.getType()+ "_"+ localID++  , strClx.getType());
////        newBody.getLocals().add(newStr);
//        NativeMethodBuilder.v().initializeRef(newBody, strClx.getType(), newStr);
////        newBody.getUnits().add(Jimple.v().newAssignStmt( Jimple.v().newInstanceFieldRef(ret, sf.makeRef()), newStr));
//
//
//        Type sootFieldT = sf.getType();
////        Local temp = Jimple.v().newLocal("_$field_"+ sootFieldT+ "_"+ localID++  , sootFieldT);
////        newBody.getLocals().add(temp);
////        newBody.getUnits().add(Jimple.v().newAssignStmt( temp,Jimple.v().newInstanceFieldRef(ret, sf.makeRef()) ));
//
//        Local newTaint = newInstance(newBody,"java.lang.DSTaintObject","void <init>()",null);
//        String addTaintSig = "<java.lang.Object: void addTaint(java.lang.DSTaintObject)>";
//        SootMethod addTaintMth =  Scene.v().getMethod(addTaintSig);
//        newBody.getUnits().add(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(newStr, addTaintMth.makeRef(),
//                newTaint )));
//
//        newBody.getUnits().add(Jimple.v().newAssignStmt( Jimple.v().newInstanceFieldRef(ret, sf.makeRef()), newStr));
//
//        newBody.getUnits().add(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(ret, addTaintMth.makeRef(),
//                newTaint )));

        Map<Local, List<Local>> return2parameter = parseMethoder(newBody, map, methoder);

        for (Local outputL : return2parameter.keySet()) {
            Value outputV = outputs.get(outputL);
            for(Local inputLo: return2parameter.get(outputL)){
                //污染来自于native侧
                if(inputLo == null){
                    //A.addTaint(new TaintObject())
                    if(outputL == null) continue;
                    if(outputL.getType() instanceof RefType){
                        Local taintTemp = newInstance(newBody,"java.lang.DSTaintObject","void <init>()",null);

                        invokeMethod(newBody,outputL,"<java.lang.Object: void addTaint(java.lang.DSTaintObject)>",taintTemp);
                    }

                    else{
                        //Integer i = new Integer(new Random().nextInt());     return i.intValue();
                        none2primitive(newBody,outputL);
                    }
                    continue;
                }
                //参数和返回值都为基本类型
                if(inputLo.getType() instanceof PrimType && outputL.getType() instanceof PrimType) {
                    //类型不匹配,类型转换
                    Local temp = outputL;
                    if(! outputL.getType().equals(inputLo.getType())){
                        castType(newBody,inputLo, outputL);//将输入类型转换为输出类型
                        continue;
                    }
                    //类型相同，只有一个输入，或者输出类型为char，使用直接赋值的方式
                    if( return2parameter.get(outputL).size() == 1 || outputL.getType().toString().equals("char") ){
                        newBody.getUnits().add(Jimple.v().newAssignStmt(outputL, inputLo));
                    }//逻辑或
                    else if(outputL.getType().toString().equals("boolean")){
                        OrExpr orExpr = Jimple.v().newOrExpr(outputL,inputLo);
                        newBody.getUnits().add(Jimple.v().newAssignStmt(outputL, orExpr));
                    }
                    else{//+/-/*除。统一使用加法,累加   int ,
                        AddExpr addExpr = Jimple.v().newAddExpr(outputL,inputLo);
                        newBody.getUnits().add(Jimple.v().newAssignStmt(outputL, addExpr));
                    }

                    continue;
                }

//                //方式1：直接赋值语句
                if(outputL.getType().equals(inputLo.getType())){
                    newBody.getUnits().add(Jimple.v().newAssignStmt(
                            outputV == null ?  outputL: outputV ,
                            inputLo));
                }
                else{
                    //方法2：调用taint方法
                    String getTaintSig = "<java.lang.Object: java.lang.DSTaintObject getTaint()>";
                    String addTaintSig = "<java.lang.Object: void addTaint(java.lang.DSTaintObject)>";
                    //仅参数为基本类型
                    if(inputLo.getType() instanceof PrimType) {
                        String inputFullType = inputLo.getType().toString();
                        logger.info("boolean参数完全类型名：{}", inputFullType);
                        if (inputFullType.equals("boolean") || inputFullType.equals(Boolean.class.getName())) {
                            getTaintSig = "<java.lang.Object: boolean getTaintBoolean()>";
                            addTaintSig = "<java.lang.Object: void addTaint(boolean)>";
                        } else {
                            addTaintSig = "<java.lang.Object: void addTaint(double)>";
                            String upperCase = String.valueOf((char)(inputFullType.charAt(0)-32));
                            getTaintSig = "<java.lang.Object: "+ inputFullType + " getTaint" + upperCase+inputFullType.substring(1) +"()>";
//                        if (fullType.equals("char") || fullType.equals(Character.class.getName()))
//                            getTaintSig = "<java.lang.Object: char getTaintChar()>";
//                        else if (fullType.equals("int") || fullType.equals(Integer.class.getName()))
//                            getTaintSig = "<java.lang.Object: int getTaintInt()>";
//                        else if (fullType.equals("short") || fullType.equals(Short.class.getName()))
//                            getTaintSig = "<java.lang.Object: short getTaintShort()>";
//                        else if (fullType.equals("byte") || fullType.equals(Byte.class.getName()))
//                            getTaintSig = "<java.lang.Object: byte getTaintByte()>";
//                        else if (fullType.equals("long") || fullType.equals(Long.class.getName()))
//                            getTaintSig = "<java.lang.Object: long getTaintLong()>";
//                        else if (fullType.equals("float") || fullType.equals(Float.class.getName()))
//                            getTaintSig = "<java.lang.Object: float getTaintFloat()>";
//                        else if (fullType.equals("double") || fullType.equals(Double.class.getName()))
//                            getTaintSig = "<java.lang.Object: double getTaintDouble()>";
                        }
                        logger.info("基本类型的getTaint方法签名：{}", getTaintSig);
                        logger.info("基本类型的addTaint方法签名：{}", addTaintSig);
                    }
                    //仅返回值为基本类型
                    if(outputL.getType() instanceof PrimType){
                        //A.getTaintInt/boolean/double...
                        String outputFullType = outputL.getType().toString();
                        String upperCase = String.valueOf((char)(outputFullType.charAt(0)-32));
                        getTaintSig = "<java.lang.Object: "+ outputFullType + " getTaint" + upperCase+outputFullType.substring(1) +"()>";
                    }

                    //getTaint
                    SootMethod getTaintMth =  Scene.v().getMethod(getTaintSig);
                    //addTaint(DSTaintObject/boolean/double)
                    SootMethod addTaintMth =  Scene.v().getMethod(addTaintSig);

                    taintValue(newBody,inputLo, outputL,getTaintMth,addTaintMth);
                }
            }
        }
	}

    public FlagAnalysis() {
        super();
    }

    private void castType(Body newBody, Local inputLo, Local outputL) {
	    Local outputLocal = null;
	    if (outputL.getType().toString().equals("boolean")){
	        //boolean bo = f1 >0 ? true : false;
            genIfStmt(newBody,inputLo, IntConstant.v(0), BooleanConstant.v(true), outputL);

            genIfStmt(newBody,inputLo,  IntConstant.v(0),BooleanConstant.v(false), outputL);
        }
	    else if(outputL.getType().toString().equals("char")){
	        if(inputLo.getType().toString().equals("boolean")){
	            //out = input ? 'a' : 'b';
                genIfStmt(newBody,inputLo, BooleanConstant.v(true), CharConstant.v('a'), outputL);

                genIfStmt(newBody,inputLo, BooleanConstant.v(false), CharConstant.v('z'), outputL);
            }
	        else{//强制类型转换
                CastExpr castExpr = Jimple.v().newCastExpr(inputLo, outputL.getType());
                Local temp = newLocal(newBody, castExpr);
                newBody.getUnits().add(Jimple.v().newAssignStmt(outputL, temp));
            }
        }
	    else{
            if(inputLo.getType().toString().equals("boolean")){
                //  out =  input ? 1 : 0;
                genIfStmt(newBody,inputLo, BooleanConstant.v(true), IntConstant.v(1), outputL);

                genIfStmt(newBody,inputLo, BooleanConstant.v(false), IntConstant.v(0), outputL);
            }
            else{//强制类型转换
                CastExpr castExpr = Jimple.v().newCastExpr(inputLo, outputL.getType());
                Local temp = newLocal(newBody, castExpr);
                newBody.getUnits().add(Jimple.v().newAssignStmt(outputL, temp));
            }
        }
    }

    private void genIfStmt(Body newBody, Local conditionLeft, Value conditionright,Value newValue , Local outputL) {
        ConditionExpr eqCondition = Jimple.v().newEqExpr(conditionLeft, conditionright);
        Local a = newLocal(newBody, newValue);
        Stmt afterIf = Jimple.v().newAssignStmt(outputL, a);
        newBody.getUnits().add(afterIf );
        newBody.getUnits().add(Jimple.v().newIfStmt(eqCondition,afterIf));
    }


    private Map<Local, List<Local>> parseMethoder(Body newBody, Map<String, Local> map, Methoder methoder) {
        Map<Local, List<Local>> return2parameter = new HashMap<>();

        if (methoder == null) return return2parameter;

        for(Dependency dependency : methoder.getDependencies()){
            Element paraEle = dependency.getInput();
            Local finalInput = null;
            if(! paraEle.getFullType().equals("none")){
                Local paraLocal = localFromMap(paraEle.getFullType(), map);
                finalInput = paraLocal;
                if(paraEle.getChangeField()!=null){
                    //连接data.str
//                    Map<Local, Value> inputMap= concat(paraEle, paraLocal, newBody , false);
//                    for(Local l : inputMap.keySet()){
//                        finalInput = l;
//                    }
                    finalInput = concat(paraEle, paraLocal, newBody , false);
//                    paraLocal = newLocal(newBody,finalInput);
//                    newBody.getUnits().add(Jimple.v().newAssignStmt(paraLocal, finalInput));
                }

            }

            for(Element rntEle : dependency.getOutputs()){
                Local rLocal = localFromMap(rntEle.getFullType(), map);
                Local finalOutput = rLocal;
                if(rntEle.getChangeField() != null){
//                    Map<Local, Value> outputMap = concat(rntEle, rLocal, newBody , true);
//                    for(Local l : outputMap.keySet()){
//                        finalOutput = l;
//                    }
                    finalOutput = concat(rntEle, rLocal, newBody , true);
//                    rLocal = newLocal(newBody,finalOutput);
//                    newBody.getUnits().add(Jimple.v().newAssignStmt(rLocal, finalOutput));
                }

                if(!return2parameter.containsKey(finalOutput)){
                    return2parameter.put(finalOutput, new ArrayList<Local>());
                }
                return2parameter.get(finalOutput).add(finalInput);
            }
        }
        return return2parameter;
    }
    /**
     * output: data.str
     * */
    private Local concat(Element ele, Local paraLocal, Body newBody, boolean leftOp){
        Map<Local, Value> map = new HashMap();
        Local  tmpRef = paraLocal;
        Value leftV = paraLocal;
        while(ele != null){
            logger.info("full type: {}",ele.getFullType());
            String type = ele.getFullType();
            if(type.contains("@")){
                type = type.split("@")[0];
            }

            if(ele.getChangeField() != null){
                SootClass clx = Scene.v().getSootClass(type);
//                Type sootFieldT = Scene.v().getSootClass(ele.getChangeField().getFullType()).getType();
                // findFieldSootType(changeField.getFullType());
                SootField sf =  clx.getFieldByName(ele.getChangeField().getName());
                Type sootFieldT = sf.getType();
                logger.info("field---name:{},type:{}",ele.getChangeField().getName(), sootFieldT);

                if(sf.isStatic()){
                    Local ref = Jimple.v().newLocal("_$staticField_"+ sootFieldT+ "_"+ localID++  , sootFieldT);
                    newBody.getLocals().add(ref);
                    leftV = Jimple.v().newStaticFieldRef(sf.makeRef());
                    newBody.getUnits().add(Jimple.v().newAssignStmt(ref, leftV));
                    tmpRef = ref;; //data.str

                }
                else{
                    Local ref = Jimple.v().newLocal("_$instanceField_"+sootFieldT + "_"+ localID++  , sootFieldT);
                    newBody.getLocals().add(ref);
//                    logger.info("field base:{}",Jimple.v().newInstanceFieldRef(tmpRef, sf.makeRef()).getBase());
//                    logger.info("field base box:{}",Jimple.v().newInstanceFieldRef(tmpRef, sf.makeRef()).getBaseBox());
                    leftV = Jimple.v().newInstanceFieldRef(tmpRef, sf.makeRef());
                    newBody.getUnits().add(Jimple.v().newAssignStmt(ref, leftV));
                    tmpRef = ref;
                }
            }
//            else{
//                return tmpRef;
//            }

            ele = ele.getChangeField();//只有一个变化的字段
        }
        if(leftOp){
            outputs.put(tmpRef, leftV);
//            map.put(tmpRef, leftV);
        }
        else{
//            map.put(tmpRef, tmpRef);
        }
        return tmpRef;
    }

    private void taintValue(Body newBody, Local paraLocal, Local returnLocal, SootMethod getTaintMth, SootMethod addTaintMth) {
        if(paraLocal.getType() instanceof PrimType){
//            if(returnLocal.getType() instanceof PrimType){
//                //Integer i = new Integer(int1);      i = int1 + int2
//                //int t = i.intValue();
//                SootClass dsTaintObjClx = Scene.v().getSootClass("java.lang.Integer");
//            }
//            else{
                //returnpara.addTaint(primitive_para);
                newBody.getUnits().add(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(returnLocal, addTaintMth.makeRef(),
                        Arrays.asList(paraLocal) )));
//            }

        }
        else {
            VirtualInvokeExpr virtualInvokeExpr = Jimple.v().newVirtualInvokeExpr(paraLocal, getTaintMth.makeRef() );

            if(returnLocal.getType() instanceof PrimType){
                //return getTaintInt/boolean
                newBody.getUnits().add(Jimple.v().newAssignStmt(returnLocal, virtualInvokeExpr));
            }
            else{
                // _$temp1 = finalPara.getTaint()
                Local tempTaint = Jimple.v().newLocal("_$temp_"+virtualInvokeExpr.getType() +"_" + localID++ , virtualInvokeExpr.getType());
                newBody.getLocals().add(tempTaint);
                logger.info("getTaint() return temp = {} ",virtualInvokeExpr.getType() );
                newBody.getUnits().add(Jimple.v().newAssignStmt(tempTaint, virtualInvokeExpr));
                //if _$temp1 !=null : returnpara.addTaint(_$temp1);
                ConditionExpr condExpr = Jimple.v().newNeExpr(tempTaint, NullConstant.v());
                InvokeStmt afterIf = Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(returnLocal, addTaintMth.makeRef(),
                        Arrays.asList(tempTaint) ));
                newBody.getUnits().add(afterIf );
                newBody.getUnits().add(Jimple.v().newIfStmt(condExpr,afterIf));

            }
        }
	}


    private Local newLocal(Body newBody, Value paraValue) {
        Local parameterLocal = Jimple.v().newLocal("_$local_"+ paraValue.getType() +"_"+localID++ , paraValue.getType());
        newBody.getLocals().add(parameterLocal);
        return parameterLocal;
    }

    private Local localFromMap(String typeStr, Map<String, Local> map) {
        Local returnLocal;
        String[] temp = typeStr.split("@");
        if(temp[1].equals("this"))
            returnLocal = map.get("this");
        else if(temp[1].equals("return"))
            returnLocal = map.get("return");
        else
            returnLocal = map.get("para_"+temp[1]);//显式参数索引
        return returnLocal;
    }



    private void none2primitive(Body newBody,Local returnLc) {
        //new Random
        Local random = newInstance(newBody,"java.util.Random","void <init>()",null);
        logger.info("Random对象：{}",random.getName());
        Value randomValue;
        Local primValue;
        String fullType = returnLc.getType().toString();
        if(fullType.equals("char") ){
            //(char)random.nextInt();
            randomValue = invokeExpression(newBody,"<java.util.Random: int nextInt(int)>", random,
                    IntConstant.v((int)Character.MAX_VALUE));
            newBody.getUnits().add(Jimple.v().newAssignStmt(randomValue,Jimple.v().newCastExpr(random,CharType.v())));
            //new Character()
            primValue = newInstance(newBody,"java.lang.Character","<java.lang.Character: void <init>(char)>",randomValue);
            randomValue = invokeExpression(newBody,"<java.lang.Character: char charValue()>",primValue,null);

        }
        else if(fullType.equals("int")){
            randomValue = invokeExpression(newBody,"<java.util.Random: int nextInt()>", random, null);
//            void <init>()
            primValue = newInstance(newBody,"java.lang.Integer", "void <init>(int)",randomValue);
            randomValue = invokeExpression(newBody,"<java.lang.Integer: int intValue()>",primValue,null);
        }
        else if(fullType.equals("short") ){
            randomValue = invokeExpression(newBody,"<java.util.Random: int nextInt(int)>", random,
                    ShortConstant.v(Short.MAX_VALUE));

            primValue = newInstance(newBody,"java.lang.Short","<java.lang.Short: void <init>(short)>",randomValue);
            randomValue = invokeExpression(newBody,"<java.lang.Short: short shortValue()>",primValue,null);
        }
        else if(fullType.equals("byte")){

            randomValue = invokeExpression(newBody,"<java.util.Random: int nextInt(int)>", random,
                    ByteConstant.v(Byte.MAX_VALUE));

            primValue = newInstance(newBody,"java.lang.Byte","<java.lang.Byte: void <init>(byte)>",randomValue);
            randomValue = invokeExpression(newBody,"<java.lang.Byte: byte byteValue()>",primValue,null);
        }
        else if(fullType.equals("long")){
            randomValue = invokeExpression(newBody, "<java.util.Random: long nextLong()>", random, null);

            primValue = newInstance(newBody,"java.lang.Long","<java.lang.Long: void <init>(long)>",randomValue);
            randomValue = invokeExpression(newBody,"<java.lang.Long: long longValue()>",primValue,null);
        }
        else if(fullType.equals("float")){
            randomValue = invokeExpression(newBody, "<java.util.Random: float nextFloat()>", random, null);

            primValue = newInstance(newBody,"java.lang.Float","<java.lang.Float: void <init>(float)>",randomValue);
            randomValue = invokeExpression(newBody,"<java.lang.Float: float floatValue()>",primValue,null);
        }
        else if(fullType.equals("double")){
            randomValue = invokeExpression(newBody, "<java.util.Random: double nextDouble()>", random, null);

            primValue = newInstance(newBody,"java.lang.Double","<java.lang.Double: void <init>(double)>",randomValue);
            randomValue = invokeExpression(newBody,"<java.lang.Double: double doubleValue()>",primValue,null);
        }
        else{
            randomValue = invokeExpression(newBody, "<java.util.Random: boolean nextBoolean()>", random, null);

            primValue = newInstance(newBody,"java.lang.Boolean","<java.lang.Boolean: void <init>(boolean)>",randomValue);
            randomValue = invokeExpression(newBody,"<java.lang.Boolean: boolean booleanValue()>",primValue,null);
        }
        newBody.getUnits().add(Jimple.v().newAssignStmt(returnLc,randomValue));
    }

    private Local invokeExpression(Body newBody, String MethodSignature, Local returnLc, Value value) {
        SootMethod sootMethod =  Scene.v().getMethod(MethodSignature);
        VirtualInvokeExpr virtualInvokeExpr;
        if(value == null){
            virtualInvokeExpr = Jimple.v().newVirtualInvokeExpr(returnLc, sootMethod.makeRef());
//            newBody.getUnits().add(Jimple.v().newInvokeStmt(virtualInvokeExpr));
        }
        else{
            virtualInvokeExpr = Jimple.v().newVirtualInvokeExpr(returnLc, sootMethod.makeRef(), Arrays.asList(value) );
//            newBody.getUnits().add(Jimple.v().newInvokeStmt(virtualInvokeExpr));
        }
        Local local = newLocal(newBody,virtualInvokeExpr);
        newBody.getUnits().add(Jimple.v().newAssignStmt(local, virtualInvokeExpr));
        return local;
    }

    private void invokeMethod(Body newBody, Local returnLc, String MethodSignature,  Value value) {
        SootMethod sootMethod =  Scene.v().getMethod(MethodSignature);
        if(value == null){
            newBody.getUnits().add(Jimple.v().newInvokeStmt( Jimple.v().newVirtualInvokeExpr(returnLc, sootMethod.makeRef())));
        }
        else{
            newBody.getUnits().add(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(returnLc, sootMethod.makeRef(),
                    Arrays.asList(value) )));
        }
    }

    private Local newInstance(Body newBody,String className,String initSig, Value para) {
        //_$temp local
        SootClass dsTaintObjClx = Scene.v().getSootClass(className);
        Local local = Jimple.v().newLocal("_$temp_"+ dsTaintObjClx.getName() + "_" + localID++  , dsTaintObjClx.getType());
        newBody.getLocals().add(local);
        //_$temp = new DSTaintObject();
        newBody.getUnits().add(Jimple.v().newAssignStmt(local, Jimple.v().newNewExpr( dsTaintObjClx.getType())));
        //_$temp.init
        if(para == null)
            newBody.getUnits().add(Jimple.v().newInvokeStmt(Jimple.v().newSpecialInvokeExpr(local,
                    dsTaintObjClx.getMethod(initSig).makeRef())));
        else
            newBody.getUnits().add(Jimple.v().newInvokeStmt(Jimple.v().newSpecialInvokeExpr(local,
                    dsTaintObjClx.getMethod(initSig).makeRef(), para)));
        return local;
    }



    /**返回值为基本类型*/
    private void returnTypeIsPrim(Body newBody, Local returnLocal, Local paraLocal) {
        PatchingChain  units =  newBody.getUnits();
	    Type rtnType = returnLocal.getType();
        Type paraType = paraLocal.getType();
        //返回值为基本类型, 参数也是基本类型
        if(paraType instanceof PrimType){
            if(rtnType.equals(paraType)){
                units.add(Jimple.v().newAssignStmt(returnLocal, paraLocal));// 需要确定参数位置是否正确
                return;
            }

            if(rtnType.equals( BooleanType.v() )){
//            if(a > 0)  b = true;
//            if(a <= 0) b = false;
                units.add(Jimple.v().newIfStmt(
                        Jimple.v().newGtExpr( paraLocal ,IntConstant.v(0)), Jimple.v().newAssignStmt(returnLocal, BooleanConstant.v(true))));

                units.add(Jimple.v().newIfStmt(
                        Jimple.v().newLeExpr( paraLocal ,IntConstant.v(0)), Jimple.v().newAssignStmt(returnLocal, BooleanConstant.v(false))));

            }
            else if(paraType.equals( BooleanType.v())){
//            int a  = b ? >0 : <0; 看返回值类型
//            if(a) b = random; b>0
//            if(a) b = random;  b<0
                Constant gt0Constant=null ;
                Constant lt0Constant=null ;
                if(rtnType instanceof      IntType)
                    gt0Constant = IntConstant.v(Math.abs(new Random().nextInt()));
                else if(rtnType instanceof LongType)
                    gt0Constant = LongConstant.v(Math.abs(new Random().nextLong()));
                else if(rtnType instanceof FloatType)
                    gt0Constant = FloatConstant.v(Math.abs(new Random().nextFloat()));
                else if(rtnType instanceof DoubleType)
                    gt0Constant = DoubleConstant.v(Math.abs(new Random().nextDouble()));
                else if(rtnType instanceof ByteType)
                    gt0Constant = ByteConstant.v( (byte) Math.abs( new Random().nextInt()));
                else if(rtnType instanceof ShortType)
                    gt0Constant = ShortConstant.v( (short) Math.abs(new Random().nextInt()));

                if(rtnType instanceof      IntType)
                    lt0Constant = IntConstant.v(-Math.abs(new Random().nextInt()));
                else if(rtnType instanceof LongType)
                    lt0Constant = LongConstant.v(-Math.abs(new Random().nextLong()));
                else if(rtnType instanceof FloatType)
                    lt0Constant = FloatConstant.v(-Math.abs(new Random().nextFloat()));
                else if(rtnType instanceof DoubleType)
                    lt0Constant = DoubleConstant.v(-Math.abs(new Random().nextDouble()));
                else if(rtnType instanceof ByteType)
                    lt0Constant = ByteConstant.v( (byte) -Math.abs( new Random().nextInt()));
                else if(rtnType instanceof ShortType)
                    lt0Constant = ShortConstant.v( (short) -Math.abs(new Random().nextInt()));

                if(rtnType instanceof CharType)
                    lt0Constant = gt0Constant = CharConstant.v( (char) Math.abs( new Random().nextInt()));


                units.add(Jimple.v().newIfStmt(
                        Jimple.v().newEqExpr( paraLocal ,BooleanConstant.v(true)), Jimple.v().newAssignStmt(returnLocal, gt0Constant)));
                units.add(Jimple.v().newIfStmt(
                        Jimple.v().newNeExpr( paraLocal ,BooleanConstant.v(false)), Jimple.v().newAssignStmt(returnLocal, lt0Constant)));
            }
            else{
//            看返回值类型,除去强制类型转换
//            int a = (int) b;  b=long
                CastExpr castExpr = null;
                if(rtnType instanceof      IntType)
                    castExpr = Jimple.v().newCastExpr(paraLocal,IntType.v());
                else if(rtnType instanceof LongType)
                    castExpr = Jimple.v().newCastExpr(paraLocal,LongType.v());
                else if(rtnType instanceof FloatType)
                    castExpr = Jimple.v().newCastExpr(paraLocal,FloatType.v());
                else if(rtnType instanceof DoubleType)
                    castExpr = Jimple.v().newCastExpr(paraLocal,DoubleType.v());
                else if(rtnType instanceof ByteType)
                    castExpr = Jimple.v().newCastExpr(paraLocal,ByteType.v());
                else if(rtnType instanceof ShortType)
                    castExpr = Jimple.v().newCastExpr(paraLocal,ShortType.v());
                else if(rtnType instanceof CharType)
                    castExpr = Jimple.v().newCastExpr(paraLocal,ShortType.v());

                units.add(Jimple.v().newAssignStmt(returnLocal, castExpr));

            }
        }
//        else if(paraType instanceof RefType){
//
//        }
//        else if(paraType instanceof ArrayType){}
    }

	public static void main(String[] args){
		String path = "/media/myw/Study/DroidSafe/droidsafe-src/android-apps/examples/interact_nativecode/flag.json";
		String content = FileUtils.readToString(path);
		System.out.println(content);
		List<Methoder> methoders = FileUtils.jsonToArrayList(content,Methoder.class);
		for(Methoder m: methoders){
            System.out.println(m.getSignature());
			for(Dependency d : m.getDependencies()){
                Element ele = d.getInput();
			    while(ele.getChangeField() != null){
                    System.out.println(ele.getFullType());
                    ele = ele.getChangeField();
                }
			    for(Element rntEle : d.getOutputs()){
                    while(rntEle.getChangeField() != null){
                        System.out.println(rntEle.getFullType());
                        rntEle = rntEle.getChangeField();
                    }
                }
                //System.out.println(d.getPara().getName());
                //System.out.println(d.getPara().getChangeField().getName());
			}
		}

	}
}
