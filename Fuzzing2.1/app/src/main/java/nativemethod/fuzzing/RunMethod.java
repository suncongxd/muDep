package nativemethod.fuzzing;

import android.util.Log;

import com.cedarsoftware.util.DeepEquals;
import com.rits.cloning.Cloner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nativemethod.fuzzing.Utils.CommonUtil;
import pojo.Dependency;
import pojo.Element;
import pojo.Methoder;

public class RunMethod{
    private static final Logger logger = LoggerFactory.getLogger(RunMethod.class);
    private static Integer invokeFailedNUm=0;
    private Flag flg = null;
    private String mthSignature;
    private Cloner cloner;


    public RunMethod(String mthsig,String apkName, Cloner cloner){
        this.flg = new Flag( mthsig,apkName);
        this.mthSignature = mthsig;
        this.cloner = cloner;
//        cloner.setDumpClonedClasses(true);
    }
    /**
     * 算法程序开始
     * */
    public Methoder run()  {
        Methoder methoder = new Methoder(mthSignature);
        Set<Dependency> dependencies = new HashSet<>();
        //记录分析时间
        long startTime = System.currentTimeMillis();

        flg.initial();
        logger.info("方法类型:{}",flg.isMthIsStatic());



        int M =flg.getM(), N = flg.getN();
        //如果flag矩阵的m为0，直接返回不分析
        if(M == 0){
            logger.error("方法返回值的个数为0");
            return methoder;
        }
        //显式参数个数为0,返回值不为0
        if(N == 0 && M != 0){//静态无参方法执行两次
            runNonParaMethod(flg, methoder);
            return methoder;
        }


        int BOUND = flg.getTestNum();
        //include this
        for(int index=0; index < N; index++) { // i = 0...n
            if(!flg.isMthIsStatic() && index == N-1){
                Log.v("研究this",String.valueOf(index+1));
            }
            else{
                Log.v("第几个参数",String.valueOf(index+1));
            }

            for(int count = 0; count < BOUND; count++){//测试次数
                Log.v("循环次数",String.valueOf(count+1));

                Set<Element> outputSet = new HashSet<>();
                Element inputEle ;
                /**对显式参数+this进行随机生成.*/
                Object[] firstRealParas = flg.inicialLoadAndGenerate(flg.getExplictParaT()).toArray();
                Object thisObj = flg.genThisObj();

                /**克隆显示参数*/
//                Object[] secondRealParas = cloneRealParas(firstRealParas);
                Object[] secondRealParas = new Object[firstRealParas.length];
                for(int i=0; i<firstRealParas.length; i++){
                    Object para = firstRealParas[i];
                    secondRealParas[i] = cloner.deepClone(para);
//                    logger.info("cloned parameter class: {}",secondRealParas[i].getClass().getName());
                }

                /**深度复制this, 判断是要克隆this还是使用序列化*/
                Object copyThis = cloner.deepClone(thisObj);//MainActivity copy费时
                if(copyThis == null && !flg.isMthIsStatic() ) logger.error("clone this is null");
                else{logger.info("this克隆后得到{}",copyThis);}
//                if(thisObj !=null){
//                    if(flg.isClassImpleSerializable()){
//                        //Log.v("this has can be serializable",String.valueOf(true));
//                        copyThis = CommonUtil.getObjBySerializable(thisObj);
//                    }
//                    else if(thisObj instanceof Cloneable){
//                        //Log.v("this has clone method",String.valueOf(true));
//                        copyThis = cloneObject(thisObj);
//                    }
//                    if(copyThis == null && !flg.isMthIsStatic() ) logger.error("clone this is null");
//                    logger.info("this克隆后得到{}",copyThis);
//                }

                Object mututePara, beforeMututePara;
                String parameterName ;
                /**翻转 第index个 显式参数*/
                if(!flg.isMthIsStatic() && index == N-1){
                    parameterName = getParaFullName(flg.getThisClass(), index);
                    beforeMututePara = thisObj;
                    copyThis = flg. mutate ( copyThis, 0 );
                    mututePara = copyThis;
                    logger.info("this翻转ok!", index+1);
//                    logger.info("比较this{}和翻转this{},比较结果: {}", thisObj.toString(), copyThis.toString(),
//                            DeepEquals.deepEquals(thisObj, copyThis));
//                            thisObj.equals(copyThis));
                }
                else{
//                    for(Object first: firstRealParas) logger.info("第一次所有参数:{}", first.toString());
//                    for(Object second: secondRealParas) logger.info("第二次所有参数(克隆后){}",second.toString()  );
//                    logger.info("翻转第{}个参数{}", index+1, secondRealParas[index].toString());
                    parameterName = getParaFullName(flg.getExplictParaClasses()[index], index);
                    beforeMututePara = firstRealParas[index];
                    secondRealParas[index] = flg. mutate ( secondRealParas[index], 0 );
                    mututePara = secondRealParas[index];
                    logger.info("第{}个参数翻转ok!", index+1);
//                    logger.info("比较{}和翻转对象{},比较结果: {}", firstRealParas[index].toString(), secondRealParas[index].toString(),
//                            firstRealParas[index].equals(secondRealParas[index]));
//                            DeepEquals.deepEquals(firstRealParas[index], secondRealParas[index]));
//                    for(Object copy: secondRealParas) logger.info("翻转后,第二次所有参数:{}", copy.toString());


                }

                if( DeepEquals.deepEquals(beforeMututePara, mututePara) ){
                    continue;
                }
                inputEle = new Element( parameterName);
                recursiveCompareField(beforeMututePara, mututePara, inputEle, 0);
                /**改变的参数输出到依赖关系*/
                //变异可能没有变异成功

//                if(flg.isMutableRef(firstRealParas[index])){
//                    parameterName = clx.getSuperclass().getName();
//                }else
//                    parameterName = clx.getName();//lt:java.lang.String
//                parameterName += "@" + String.valueOf(index);
//                if(mututePara.getClass().isArray()){
//                    inputEle = new Element( parameterName);
//                    //
//                }
//                else if(flg.isPrimitive(mututePara.getClass())){
//                    inputEle = new Element( parameterName);
//                }
//                else{

//                }




                Class[] paraClasses = flg.getExplictParaClasses();
                /**方法执行第一次*/
                Object[] firstReturn = excuteMth(M,thisObj,paraClasses, firstRealParas);
                /**方法第一次执行完毕得到firstReturn[0]，将引用类型对象放入到firstReturn*/
                 getReturns(firstReturn, firstRealParas, thisObj);




                /**第二次执行方法*/
                Object[] secondReturn = excuteMth(M,copyThis,paraClasses, secondRealParas);
                /**方法第二次执行完毕得到secondReturn[0]，将引用类型对象放入到secondReturn*/
                Map<Integer,Integer> map = getReturns(secondReturn,secondRealParas, copyThis);



                /**比较结果:第一次的引用结果firstReturn和第二次的引用结果secondReturn*/
                logger.info("开始比较结果....");
                for( int i=0; i < M ; i++){
                    Element returnEle ;
                    //变异的参数不比较
                    if(map.get(index) != null && i == map.get(index))
                        continue;


                    //比较null
                    if(firstReturn[i] == null && secondReturn[i] == null )
                        continue;
                    else if(firstReturn[i] == null || secondReturn[i] == null ){

                        String returnName = firstReturn[i] == null ?
                                getReturnFullName(secondReturn, i, map) : getReturnFullName(firstReturn, i, map);
                        outputSet.add(new Element( returnName ));//去重
                        flg.setFlag(i,index,1);
                        Log.v("值改变,flag为1", returnName);
                        continue;
                    }
                    logger.info("比较{}---{}",firstReturn[i], secondReturn[i]);

                    String returnName = getReturnFullName(firstReturn, i, map);//input和outputs的名称转换

                    if(!DeepEquals.deepEquals(firstReturn[i], secondReturn[i])){
                        returnEle = new Element(returnName );
                        recursiveCompareField(firstReturn[i],secondReturn[i],returnEle ,0);

                        outputSet.add(returnEle);//去重
                        flg.setFlag(i,index,1);
                        logger.info("{} compare different", firstReturn[i].getClass().getName());
                    }
                }
                Log.v("比较结果","finish");
                if( outputSet.size() > 0){
                    dependencies.add(new Dependency( inputEle, outputSet));
                }

            }

            /**输出flag*/
            int[][] flag = flg.getFlags();
            for(int i=0; i < M; i++){
                if(N !=0){
                    for (int j=0; j < N; j++){
                        Log.v("flag matrix",String.valueOf(flag[i][j]));
                    }
                }
                else{//一维
                    Log.v("一维flags",String.valueOf(flag[i][0]));
                }
            }
            Log.v("split","------------------------------------------------------------------------------------");
        }
        if(dependencies.size() > 0)
            methoder.setDependencies(dependencies);

        Log.v("runtime",String.valueOf((double)(System.currentTimeMillis()-startTime)/1000)+"s");
        logger.info("方法执行失败次数：{}", invokeFailedNUm);
        if( invokeFailedNUm / 2 >= BOUND * 0.95 ) Log.e("95%调用失败","参数设置问题");
        return methoder;
    }

    /**找到不相等的字段,继续递归
     * @param depth 避免死循环，不停的比较
     * */
    private boolean recursiveCompareField(Object o1, Object o2, Element returnEle, int depth) {
        if(depth >= 10 ) return false;
        if(o1 == null && o2 == null){
            depth --;
            return true;
        }

        else if(o1 == null || o2 == null){
            depth--;
            return false;
        }

//        logger.info("记录比较{}和{}",o1.toString(), o2.toString());
        if(o1.getClass() != o2.getClass()){
//            Log.e("比较对象类型不一致",o1.getClass() +"---"+ o2.getClass());
            depth--;
            return false;
        }

        if(flg.isPrimitive(o1.getClass()) && ! o1.equals(o2)){
            depth--;
            return false;
        }
        else if(o1.getClass() == String.class && !o1.equals(o2)){
            depth--;
            return false;
        }
        else{
            //数组
            if( ! DeepEquals.deepEquals(o1,o2)){// !val1.equals(val2)
                if(o1.getClass().isArray()){//
                    //todo 没有比较索引，以及组成数组元素
                    depth--;
                    return false;
                }
                //比较对象字段
                else{
                    Field[] fields;
                    Class theOtherClass;

                    fields =o1.getClass().getDeclaredFields();
                    theOtherClass = o2.getClass();


                    for(Field f1 : fields){
                        f1.setAccessible(true);
                        try {
                            Field f2 = theOtherClass.getDeclaredField(f1.getName());
                            f2.setAccessible(true);
                            Object val1 = f1.get(o1), val2 = f2.get(o2);
                            if(!DeepEquals.deepEquals(val1,val2) ){
                                Element ele = new Element(f1.getName(),f1.getType().getName());
                                returnEle.setChangeField(ele );
                                recursiveCompareField(val1,val2, ele, depth + 1 );
//                                logger.info("深度记录不等字段{}和{}",f1.getType()+" "+f1.getType().getName()+" "+val1,
//                                        f2.getType()+" "+f2.getType().getName()+" "+val2);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return true;
    }
    /*key:方法显式参数索引
     * value:方法返回值（return,可变参数,this）索引*/
    public int getKey(Map<Integer,Integer> map, int value){
        for(Integer key: map.keySet()){
            if(map.get(key).equals(value)){
                return key;
            }
        }
        throw new IllegalArgumentException("没有key对应的值");
    }

    private Object[] excuteMth(int M,Object thisObj, Class[] parasType, Object[] firstRealParas) {
        Object[] firstReturn = new Object[M];
        Object  firstReturnObj;
        if(flg.isMthIsStatic()){//是静态方法
            Class clx = flg.loadClass(flg.getFullClassName());
            firstReturnObj = invokeStaticMthByReflect(clx,flg.getMethodName(),parasType,firstRealParas,firstReturn);
        }
        else{//非静态方法
            firstReturnObj = invokeMthByReflect(thisObj,flg.getMethodName(),parasType,firstRealParas,firstReturn);
        }
        //返回值
        firstReturn[0] = firstReturnObj;
        logger.info("方法执行返回值: {}",firstReturnObj);
        return firstReturn;
    }

    /**
     * e.g output: com.example.interact_nativecode.Data@0
     * */
    private String getParaFullName(Class clx, int index) {

        String returnName= clx.getName() ;
//        if(flg.isMutableRef(obj)){
//            returnName = clx.getSuperclass().getName();
//        }else
//            returnName = clx.getName();//lt:java.lang.String
        if(index >= flg.getExplictParaClasses().length)
            returnName += "@this";
        else
            returnName += "@" + String.valueOf(index);

        return returnName;
    }

    private String getReturnFullName(Object[] returns, int index, Map<Integer,Integer> map) {
        String returnName ;
        Class clx = returns[index].getClass();

        returnName = clx.getName();//lt:java.lang.String

        if(flg.isMthHasReturn() && index == 0){//return
            returnName += "@return";
        }
        else if( !flg.isMthIsStatic() && index >= returns.length-1){//this
            returnName += "@this";
        }
        else if(map != null){
            returnName += "@" + String.valueOf(getKey(map,index));//
        }

        return returnName;
    }

    private Object[] cloneRealParas(Object[] firstRealParas) {
        Object[] secondRealParas = new Object[firstRealParas.length];
        for (int k = 0; k < firstRealParas.length; k++) {
            Class clz = firstRealParas[k].getClass();
//                    if(k == index){
//                        //mutate T k (arg k , ξ(arg k ))
//                    }
            //基本类型,不可变类型,直接赋值
            if(flg.isPrimitive(clz) || flg.immutable.isImmutable(clz.getName())){
                secondRealParas[k] = firstRealParas[k];
            }//如果参数是数组类型,深度克隆数组
            else if(clz.isArray()){
                Object cloneArr = Array.newInstance(clz.getComponentType(),
                        Array.getLength(firstRealParas[k]));
                try{
                    cloneArr(firstRealParas[k], cloneArr);
                }catch (Exception e){
                    logger.error("克隆数组{}失败",clz.getName());
                    e.printStackTrace();
                }
                secondRealParas[k] = cloneArr;
            }
            else{//可变对象
                secondRealParas[k] = cloneObject(firstRealParas[k]);
            }

//            if((secondRealParas[k] == null)) {
//                logger.error("拷贝的参数{}为null", clz.getName());
//                return methoder;
//            }
        }
        return secondRealParas;
    }





    /**对象方法，有this,通过object得到指定方法
     *
     * */
    public Object invokeMthByReflect(Object target,String mthName,Class[] parasType,Object[] realPara,Object[] Return){
        if(target == null)
            return null;

        String returnName ="";
        Object returnObj;
        try {
            Method mth= target.getClass().getDeclaredMethod(mthName, parasType);

            logger.info("target: {}", target);

            mth.setAccessible(true);

            returnObj  = mth.invoke(target, realPara);

            returnName = mth.getReturnType().getName();
            logger.info("执行对象方法{}成功", mth.toString());

            if(returnObj != null){logger.info("方法执行结果: {}", returnObj);}
            if(returnName.equals("void")){return null;}

            return returnObj;
        } catch (NoSuchMethodException e) {
            invokeFailedNUm++;
            Log.e("没有该方法",mthName);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            invokeFailedNUm++;
            e.printStackTrace();
        } catch (InvocationTargetException e) {//返回值不是void,调用失败，则将返回值置为空，防止后面程序失败
            if(!returnName.equals("void")){
                Return[0] = null;
                e.printStackTrace();
            }
            invokeFailedNUm++;
        }
        return null;
    }
    /**静态方法，没有this,通过class调用指定方法
     * */
    public Object invokeStaticMthByReflect(Class target,String mthName,Class[] parasType,Object[] realPara,Object[] Return){
        String returnName ="";
        Object returnObj;
        try {
            Method mth = target.getDeclaredMethod(mthName, parasType);

            Log.v("target",target.getName());

            mth.setAccessible(true);

            returnObj  = mth.invoke(null, realPara);

            returnName = mth.getReturnType().getName();

            if(returnObj != null){Log.v("方法执行返回值结果",returnObj.toString());}

            Log.v("执行静态方法成功","success");
            return returnObj;
        } catch (NoSuchMethodException e) {
            Log.e("没有该方法",mthName);
            invokeFailedNUm++;
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            invokeFailedNUm++;
            e.printStackTrace();
        } catch (InvocationTargetException e) {//返回值不是void,调用失败，则将返回值置为空，防止后面程序失败
            if(!returnName.equals("void")){
                Return[0] = null;
//                e.printStackTrace();
            }
            invokeFailedNUm++;
        }
        return null;
    }

    public Object cloneObject(Object originalObj){

        if (originalObj instanceof Cloneable) {
            logger.info("{} has clone method",originalObj.getClass().getName());
            Object copyObj ;
            //copyObjList[i] = Flag.invokeMthNoParameterByReflect(firstReferencePara[i],"clone");
            try {
                Method clone = originalObj.getClass().getMethod("clone");
                copyObj = clone.invoke(originalObj);
                return copyObj;
            } catch (NoSuchMethodException e) {
                logger.error("{}没有clone方法",originalObj.getClass().getName());
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                logger.error("{}的clone方法调用失败",originalObj.getClass().getName());
                e.printStackTrace();
            }
        }
        return null;
    }



    /**对数组中的每个元素依次克隆
     *
     * 多维数组深层克隆，如果数组类型是实现了Cloneable接口的某个类，
     * 则会调用每个元素的clone方法实现深度克隆
     *
     * 虽然数组有clone方法，但我们不能使用反射来克隆数组，因为不能使用
     * 反射来获取数组的clone方法，这个方法只能通过数组对象本身来调用，
     * 所以这里使用了动态数组创建方法来实现。
     *
     * @param objArr
     * @param cloneArr 克隆得到的数组
     * @throws Exception
     */
     private void cloneArr(Object objArr, Object cloneArr) throws Exception {
         Object objTmp;
         Object val = null;
         for (int i = 0; i < Array.getLength(objArr); i++) {
             //注，如果是非数组的基本类型，则返回的是包装类型
             objTmp = Array.get(objArr, i);

             if (objTmp == null) {val = null;}
             else if (objTmp.getClass().isArray()) {//如果是数组
                 val = Array.newInstance(objTmp.getClass().getComponentType(), Array.getLength(objTmp));
                 //如果元素是数组，则递归调用
                 cloneArr(objTmp, val);
             }
             else {//否则非数组
	                /*
	                 * 如果为基本类型或者是非Cloneable类型的引用类型，则直接对拷值 或
	                 * 者是对象的地址。没有实现Cloneable的引用类型会实行浅复制， 这对
	                 * 于像String不可变类来说是没有关系的，因为它们可以多实例或 多线程
	                 * 共享，但如果即没有实现Cloneable，又是可变以的类，浅复制 则会带来
	                 * 危险，因为这些类实例不能共享 ，一个实例里的改变会影响到 另一个实
	                 * 例。所以在使用克隆方案的时候一定要考虑可变对象的可克隆性，即需要
	                 * 实现Cloneable。
	                 *
	                 * 注，这里不能使用 objTmp.getClass.isPrimitive()来判断是元素是
	                 * 否是基本类型，因为objTmp是通过Array.get获得的，而Array.get返
	                 * 回的是Object 类型，也就是说如果是基本类型会自动转换成对应的包
	                 * 装类型后返回，所以 我们只能采用原始的类型来判断才行。
	                 */
                 if (objTmp.getClass().isPrimitive() || !(objTmp instanceof Cloneable)) {//基本类型或非Cloneable引用类型
                     val = objTmp;
                 }
                 else if (objTmp instanceof Cloneable) {//引用类型，并实现了Cloneable
	                    /*
	                     *  用反射查找clone方法，注，先使用getDeclaredMethod获取自
	                     *  己类 中所定义的方法（包括该类所声明的公共、保护、默认访问
	                     *  及私有的 方法），如果没有的话，再使用getMethod，getMethod
	                     *  只能获取公有的方法，但还包括了从父类继承过来的公有方法
	                     */
                     Method cloneMethod;
                     try {
                         //先获取自己定义的clone方法
                         cloneMethod = objTmp.getClass().getDeclaredMethod("clone", new Class[] {});
                     } catch (NoSuchMethodException e) {
                         //如果自身未定义clone方法，则从父类中找，但父类的clone一定要是public
                         cloneMethod = objTmp.getClass().getMethod("clone", new Class[] {});
                     }
                     cloneMethod.setAccessible(true);
                     val = cloneMethod.invoke(objTmp, new Object[0]);

                 }
             }
             // 设置克隆数组元素值
             Array.set(cloneArr, i, val);
         }
    }
    /**比较数组中每个元素的内容是否相等，全部相等返回true，否则返回false
     * 1.比较数组的长度
     * 2.对数组的元素一个一个比较,如果比较相等的个数等于全部的元素个数，返回true，否则返回false
     * */
    public static boolean compareArr(Object array1,Object array2){
        if(! (Array.getLength(array1) == Array.getLength(array2)) ){return false;}
        Pair pair = computeEqualNum(array1,array2,0,0);
        if(pair.getEqualNum() == pair.getAllNum()){return true;}
        return false;
    }
    /**计算两个数组中对应元素相等的个数*/
    public static Pair<Integer> computeEqualNum(Object array1, Object array2,int equalNum, int allNumbers){
        Object objTmp1,objTmp2;
        Object val1 = null, val2 = null;

        for (int i = 0; i < Array.getLength(array1); i++) {
            //注，如果是非数组的基本类型，则返回的是包装类型
            objTmp1 = Array.get(array1, i);
            objTmp2 = Array.get(array2, i);

            if (objTmp1 == null) {val1 = null;}
            else if(objTmp2 == null){val2=null;}
            else if (objTmp1.getClass().isArray() && objTmp2.getClass().isArray()) {//如果是数组
                //如果元素是数组，则递归调用
                //allNumbers += Array.getLength(objTmp1);
                Pair pair= computeEqualNum(objTmp1, objTmp2,equalNum,allNumbers);
                equalNum = (int)pair.getEqualNum();
                allNumbers = (int)pair.getAllNum();
            }
            else {//否则非数组
	                /*比较基本类型及对象*/
                allNumbers += 1;
                if (objTmp1.getClass().isPrimitive() && objTmp1.getClass().isPrimitive()) {
                    if(objTmp1 == objTmp2){ equalNum+=1; }
                }
                else {//引用类型
	                    /*用反射查找equals方法 */
                    try {
                        //先获取自己定义的clone方法
                        Method  equalsMethod = objTmp1.getClass().getDeclaredMethod("equals", Object.class);
                        Object result = equalsMethod.invoke(objTmp1, objTmp2);
                        if(result.equals(true)){ equalNum+=1;}
                    } catch (NoSuchMethodException e) {
                        //如果自身未定义clone方法，则从父类中找，但父类的clone一定要是public
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return new Pair(equalNum,allNumbers);
    }

    /**N=0的情况(无显式参数)：
     * 1.返回值不为空 或 2.存在this*/
    public void runNonParaMethod(Flag flg, Methoder methoder){
        int M = flg.getM();
        Object copyThisObj , thisObj ;
        Class target;
        Object[] firstreturnObj=new Object[M], secondreturnObj=new Object[M];
        //静态方法没有this，非静态方法克隆this
        if(!flg.isMthIsStatic()){//对象方法,有this，实际不用考虑该情况

            thisObj = flg.genThisObj();
            //clone this
            copyThisObj = cloner.deepClone(thisObj);

            //方法执行第一次
            int tep=0;
            firstreturnObj[0] = invokeMthByReflect(thisObj,flg.getMethodName(),null,null,firstreturnObj);
//            Log.v("first return",firstreturnObj.getClass().getName()+":"+firstreturnObj[0].toString());
            firstreturnObj[++tep] = thisObj;//将this放入firstreturnObj中

            //方法执行第二次
            int ans = 0;
            secondreturnObj[0] = invokeMthByReflect(copyThisObj,flg.getMethodName(),null,null,secondreturnObj);
//            Log.v("second return",secondreturnObj.getClass().getName()+":"+secondreturnObj.toString());
            secondreturnObj[++ans] = copyThisObj;//将this放入secondreturnObj中
        }
        else{//静态方法

            target = flg.loadClass(flg.getFullClassName());

            //方法执行第一次
            firstreturnObj[0] = invokeStaticMthByReflect(target,flg.getMethodName(),null,null,firstreturnObj);
            if(firstreturnObj[0] != null) {
                Log.v("static first return", firstreturnObj.getClass().getName() + ":" + firstreturnObj[0].toString());
            }

            //方法执行第二次
            secondreturnObj[0] = invokeStaticMthByReflect(target,flg.getMethodName(),null,null,secondreturnObj);
            if(secondreturnObj[0] != null){
                Log.v("static second return",secondreturnObj.getClass().getName()+":"+secondreturnObj.toString());
            }
        }

        //比较返回结果和this
        Set<Element> outputSet = new HashSet<>();
        Set<Dependency> dependencies = new HashSet<>();
        Log.v("返回比较结果","start");
        for(int i=0; i < M ; i++){
            logger.info("比较{}---{}",firstreturnObj[i], secondreturnObj[i]);

            outputSet = new HashSet<>();
            if(firstreturnObj[i] == null && secondreturnObj[i] == null )
                continue;
            else if(firstreturnObj[i] == null || secondreturnObj[i] == null ){
                String returnName = firstreturnObj[i] == null ?
                        getReturnFullName(secondreturnObj, i, null) : getReturnFullName(firstreturnObj, i, null);
                outputSet.add(new Element( returnName ));//去重
                flg.setFlag(i,0,1);
                Log.v("值改变,flag为1", returnName);
                continue;
            }

            String returnName = getReturnFullName(firstreturnObj, i, null);//input和outputs的名称转换
            if(!DeepEquals.deepEquals(firstreturnObj[i], secondreturnObj[i])){
                logger.info("值改变,flag为1: {}",firstreturnObj[i].getClass().getName());
                Element returnEle = new Element(returnName );
                recursiveCompareField(firstreturnObj[i],secondreturnObj[i],returnEle, 0);

                outputSet.add(returnEle);//去重
                flg.setFlag(i,0,1);
                Log.v("Flag",String.valueOf(flg.getFlags()[i]));
            }
        }
        Log.v("比较返回结果","finish");
        if( outputSet.size() > 0){
            dependencies.add(new Dependency(  new Element("none" ), outputSet));
            methoder.setDependencies(dependencies);
        }
    }

    /**
     * key:方法显式参数索引
     * value:方法返回值（return,可变参数,this）索引
     * */
    public Map<Integer, Integer> getReturns(Object[] returns, Object[] realParas, Object thisObj) {
        int k = 0;
        Map<Integer, Integer> map = new HashMap<>();
        if(flg.isMthHasReturn())
            k=1;//无论方法是否调用成功，都有返回值，即使调用方法失败返回值为null
        int i=0;
        for(; i < realParas.length; i++){
            if(flg.isMutableRef(flg.getExplictParaClasses()[i])){
                returns[k] = realParas[i];
                map.put(i, k++);
            }
        }
        if(!flg.isMthIsStatic()){//非静态方法
            returns[k] = thisObj;
            map.put(i,k);
        }
        return map;
    }

}

