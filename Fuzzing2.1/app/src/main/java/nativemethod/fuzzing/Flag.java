package nativemethod.fuzzing;

/**
 * Created by myw on 18-5-14.
 */

import android.content.Context;
import android.os.Environment;
import android.os.Parcel;

import com.rits.cloning.Cloner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dalvik.system.DexClassLoader;

import nativemethod.fuzzing.Utils.ClassLoaderUtil;


import static nativemethod.fuzzing.Configuration.configMap;
import static nativemethod.fuzzing.Configuration.getRandomChar;
import static nativemethod.fuzzing.Configuration.getRandomString;

/***/
public class Flag
{
    private  int M;/**返回值个数*/
    private int N;/**参数个数*/
    private int[][] flags ;
    private ArrayList allParas = new ArrayList();/**所有显式参数+this*/
    public Object thisObj ;//this
    //private ArrayList mutableArr;/**可变参数+this*/

    /**我自己生成的子类的类名，如果是final和static方法，则是原始类名*/
    private String fullClassName;/** 代理类的类名初始化为原始类类名*/
    private String singleClassName;
    public ImmutableType immutable ;/**读取immutabla文件中的final类型的类的名字集合.immutable的定义为是final类，字段全为final*/
    private Configuration random;



    /**读取configure文件的内容并根据参数类型，赋予随机值*/
    private Class thisClass;
    private int lenOfEachDim;

    private boolean classIsfinal = false;
    private boolean mthIsfinal = false;
    private boolean mthIsStatic = false;
    private boolean mthHasReturn = false;
    private boolean mthIsNative = false;

    private String returnT ;
    private String[] explictParaT;

    public Class[] getExplictParaClasses() {
        return explictParaClasses;
    }
    public Class getThisClass() {
        return thisClass;
    }
    private Class[] explictParaClasses;


    private boolean classhasCloneMth = false;
    private boolean classImpleSerializable = false;
    private boolean classCanCompare = false;

    private DexClassLoader loader;
    private MethodToBeAnalysed methodToBeAnalysed;

    private static final Logger logger = LoggerFactory.getLogger(Flag.class);

    public Flag( String mthSig,String apkName){
        M=0;  N=0;
        immutable =  MyApplication.getImmutableType();
        random =  MyApplication.getConfiguration();
        lenOfEachDim = random.getLenOfEachDim();
        methodToBeAnalysed = new MethodToBeAnalysed(mthSig);

        singleClassName = methodToBeAnalysed.singleClassName;
        fullClassName = methodToBeAnalysed.packageName + "." + singleClassName;
        returnT = methodToBeAnalysed.returnType;
        String methodName = methodToBeAnalysed.methodName;
        explictParaT = methodToBeAnalysed.parasTypes;
        mthHasReturn  = !returnT.equals("void");

        logger.info("package name: {}", methodToBeAnalysed.packageName);
        loader = ClassLoaderUtil.getClassLoader(apkName);

        //根据包名，加载Plugin apk中的类
//        try{
//            Context thisContext = MyApplication.getContext();
//            logger.info("package name: {}", methodToBeAnalysed.packageName);
//
//            PathClassLoader pathClassLoader = (PathClassLoader) thisContext.getClassLoader();
//            File optimizedDirectoryFile = thisContext.getDir("dex", Context.MODE_PRIVATE);//在应用安装目录下创建一个名为app_dex文件夹目录,如果已经存在则不创建
//
//            String devicePath = Environment.getExternalStorageDirectory().getAbsolutePath();
//            loader = new DexClassLoader( devicePath+ File.separator + apkName,
//                    optimizedDirectoryFile.getAbsolutePath(), null, pathClassLoader);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

    /**
     * 加载apk获得内部资源
     * @param apkDir apk目录
     * @param apkName apk名字,带.apk
     * @throws Exception
     */

    /**
     * 设置M,N的值,初始化flag
     * N:显式参数+this个数
     * M:return+显式参数的可变对象+this个数
     * **/

    public void initial(){
        //子类的类名初始化为原始类类名
        N = explictParaT.length;
        logger.info("方法显式参数长度: {}", N);

        getNativeMthProperty();

        if( isMthHasReturn())
            M++;


        if(!isMthIsStatic()){
            M++;
            N++;
        }

        logger.info("矩阵长度: {},宽度: {}", M , N);
        //initial flags array
        if(N == 0)
            flags =new int[M][1];
        else
            flags =new int[M][N];

    }

    private void getNativeMthProperty() {
        //判断方法参数列表的class，用于使用java反射的getMethod方法
        explictParaClasses = new Class[explictParaT.length];
        int i=0;
        for(String type: explictParaT) {
            if (type.equals("int")) {explictParaClasses[i] = int.class;}
            else if (type.equals("short")) {explictParaClasses[i] = short.class;}
            else if (type.equals("long")) {explictParaClasses[i] = long.class;}
            else if (type.equals("byte")) {explictParaClasses[i] = byte.class;}
            else if (type.equals("double")) {explictParaClasses[i] = double.class;}
            else if (type.equals("float")) {explictParaClasses[i] = float.class;}
            else if (type.equals("char")) {explictParaClasses[i] = char.class;}
            else if (type.equals("boolean")) {explictParaClasses[i] = boolean.class;}
            else if (immutable.isImmutable(type)) {explictParaClasses[i] = loadClass(type);}
            //数组
            else if (type.contains("[]")) {
                M++;
                int dim = numOfDimension(type,"[]");
                StringBuilder sig = new StringBuilder();
                for(int in=0; in < dim; in++)
                    sig.append("[");

                //去掉[][][],剩余基本元素
                String baseType = type.substring(0,type.indexOf("["));
                //基本类型
                if(baseType.equals("int")){ sig.append("I");}
                else if(baseType.equals("short") ){sig.append("S");}
                else if(baseType.equals("long")){sig.append("J");}
                else if(baseType.equals("byte")){sig.append("B");}
                else if(baseType.equals("float")){sig.append("F");}
                else if(baseType.equals("double")){sig.append("D");}
                else if(baseType.equals("char")){sig.append("C");}
                else if(baseType.equals("boolean")){ sig.append("Z");}//初始值为false
                //java对象类型
                else{ sig.append("L" + baseType + ";");}
                explictParaClasses[i] = getArrayClass(sig.toString());
            }
            //不可变和可变都一样
            else {
                explictParaClasses[i] = loadClass(type);
                M++;
            }
            i++;
        }

        //设置 classIsfinal mthIsfinal  mthIsStatic  mthIsAccessible  mthIsNative classhasCloneMth  classCanCompare
        if(immutable.isImmutable(fullClassName)){
            setClassIsfinal(true);
            setMthIsfinal(true);//final类的方法都为final
        }

        thisClass = loadClass(fullClassName);
        if(thisClass == null) return;
        try {
            Method mth = thisClass.getDeclaredMethod(getMethodName(),explictParaClasses);//Class.getMethod方法reflects the specified public member method
            mth.setAccessible(true);//private/default/protect/public方法都可以运行

            int mthModifiers = mth.getModifiers();
            //if((Modifier.isPublic(mthModifiers) || Modifier.isProtected(mthModifiers)) ){
            if(Modifier.isFinal(thisClass.getModifiers())){setMthIsfinal(true);}
            if(Modifier.isStatic(mthModifiers)) {setMthIsStatic(true);}
            if(Modifier.isNative(mthModifiers)){setMthIsNative(true);}
            if(Modifier.isFinal(mthModifiers)) {setMthIsfinal(true);}

            //设置classhasCloneMth  classCanCompare, classImpleSerializable
            for(Method m:thisClass.getDeclaredMethods()){
                if(m.getName().equals("equals")){
                    setClassCanCompare(true);
                    break;
                }
            }

            for(Class interf : thisClass.getInterfaces()) {
                if (interf == Cloneable.class) {
                    setClasshasCloneMth(true);
                }
                if(interf == Serializable.class){
                    setClassImpleSerializable(true);
                }
            }

        } catch (NoSuchMethodException e) {
        e.printStackTrace();
        }
    }

    /**在运行时使用反射可以修改成员的访问限制，根据类名和方法名得到声明的方法，设置方法的访问权限为可访问的。
     *         忽略：所要测试的方法必须为public，因为Class.getMethod方法reflects the specified public member method
     * 如果方法是静态的，没有this对象，直接返回原始类名。因为静态方法属于类方法，无需在子类中使用静态方法
     * 如果方法不是静态的，执行以下步骤：
     * 1.如果this是不可变对象（final），原始类不需要比较方法，只需要克隆方法,返回原始类的对象,或者原始类实现了序列化方法，否则返回null
     * 2.如果this是可变对象，方法为final方法，执行原始类的方法，原始类需要实现克隆方法和比较方法，返回原始类的对象，否则返回null
     * 3.如果方法的访问权限为private和protected,默认，只能使用Class.getDeclaredMethod来得到。
     * 只能使用原始类对象类调用方法。原始类对象this需要有克隆方法和比较方法来进行克隆和比较。
     * 4.返回子类的对象
     *
     * @return 值为null:无法生成this; 值为String :表明方法为静态方法，值为Object:返回正常的this对象
     * */
    public Object genThisObj(){
        //先判断this类名是否是final修饰，如果是则不可继承，那要判断是否实现了cloneable接口，以及equals,hashcode方法，对于非静态方法而言

        if(isMthIsStatic()){
            return null;
        }

        if(isClassIsfinal() ){
            logger.info("原始类{}被初始化",fullClassName);
            return simplestConstructorInit(fullClassName ,true);//实例化原始类
        }
        else{
            Class tempCLz = loadClass(fullClassName);
            if(tempCLz == null) return null;
            //接口或者抽象类
            if (tempCLz.isInterface() || Modifier.isAbstract(tempCLz.getModifiers())) {
                if( ! Configuration.implementors.containsKey(fullClassName))
                    return null;

                String subClass = Configuration.implementors.get(fullClassName);
                fullClassName = subClass;
//                fullClassName="overidedSubclass.Ext" + subClass.substring(subClass.lastIndexOf(".")+1);
                return simplestConstructorInit(subClass, false);

                //遍历overidedSubclass包下的代理类，如果实现了该接口/继承了抽象类，load该代理类
//                List<Class<?>> overidedSubclasses =ClassUtil.getClassesFromPackage("overidedSubclass");
//                for(Class c : overidedSubclasses){
//                    if(tempCLz.isAssignableFrom( c )){
//                        fullClassName = c.getName();
//                        logger.info("代理类{}被初始化",fullClassName);
//                        return simplestConstructorInit( fullClassName, false);
//                    }
//                }
            }
            else{
//                fullClassName = "overidedSubclass.Ext"+ singleClassName ;
                logger.info("代理类{}被初始化",fullClassName);
                return simplestConstructorInit(fullClassName, true);
            }
        }
//        if(isClassIsfinal() ){
//            if(isClasshasCloneMth()){
//                logger.info("原始类{}被初始化",myClassName);
//                return simplestConstructorInit(myClassName ,true);//实例化原始类
//            }
//            else if(isClassImpleSerializable()){
//                logger.info("原始类{}被初始化",myClassName);
//                return simplestConstructorInit(myClassName, true);//实例化原始类;
//            }
//        }
//        else if(isMthIsfinal() && isClasshasCloneMth() && isClassCanCompare()){
//            logger.info("原始类{}被初始化",myClassName);
//            return simplestConstructorInit(myClassName ,true);//实例化原始类
//        }
//        else if(Modifier.isPublic(mthModifiers)){//使用代理类
//            myClassName = "overidedSubclass.Ext"+myClassName.substring(myClassName.lastIndexOf(".")+1);
//            logger.info("代理类{}被初始化",myClassName);
//            return simplestConstructorInit(myClassName ,true);
//        }
//        else{
//            if(isClassCanCompare()){
//                if(isClassImpleSerializable()){
//                    logger.info("原始类{}被初始化",myClassName);
//                    return simplestConstructorInit(myClassName, true);//实例化原始类;
//                }
//                else if(isClasshasCloneMth()){
//                    logger.info("原始类{}被初始化",myClassName);
//                    return simplestConstructorInit(myClassName, true);//实例化原始类;
//                }//
//            }
//        }
    }

    /**生成参数
     * 规则：基本类型，随机生成; 对象类型，用最简单的构造函数构造，可变类型使用子类，不可变类型使用原始类。
     * 注意：数组是可变对象
     * */
    public ArrayList inicialLoadAndGenerate(String[] paraType){
        ArrayList res = new ArrayList();
        if(paraType.length <= 0 ) return new ArrayList();

        Class clx = null;
        for (String paraT : paraType){
            logger.info("显式参数{}",paraT);
            //参数为数组类型
            if(paraT.contains("[]")){//数组还未初始化
                M += 1;//数组为可变类型
                int dim = numOfDimension(paraT,"[]");
                StringBuilder sig = new StringBuilder();
                for(int in=0; in < dim; in++)
                    sig.append("[");

                //去掉[][][],剩余基本元素
                String baseParaT = paraT.substring(0,paraT.indexOf("["));
                //基本类型
                if(baseParaT.equals("int")){ sig.append("I");}
                else if(baseParaT.equals("short") ){sig.append("S");}
                else if(baseParaT.equals("long")){sig.append("J");}
                else if(baseParaT.equals("byte")){sig.append("B");}
                else if(baseParaT.equals("float")){sig.append("F");}
                else if(baseParaT.equals("double")){sig.append("D");}
                else if(baseParaT.equals("char")){sig.append("C");}
                else if(baseParaT.equals("boolean")){ sig.append("Z");}//初始值为false
                //java对象类型
                else{ sig.append("L" + baseParaT + ";");}
                clx = getArrayClass(sig.toString());//@test something wrong
            }
            //基本类型
            else if(paraT.equals("int")){ clx = int.class;}
            else if(paraT.equals("short") ){clx = short.class;}
            else if(paraT.equals("long")){clx = long.class;}
            else if(paraT.equals("byte")){clx = byte.class;}
            else if(paraT.equals("float")){clx = float.class;}
            else if(paraT.equals("double")){clx = double.class;}
            else if(paraT.equals("char")){clx = char.class;}
            else if(paraT.equals("boolean")){ clx = boolean.class;}
            //不可变类型
            else if(immutable.isImmutable(paraT)){clx = loadClass(paraT);}
            else{
                clx = loadClass(paraT);
            }
            //生成对象
            Object o = generateCore(clx);
            res.add(o);
        }
        return res;
    }

    private Class getArrayClass(String str) {
        try{
            Class<?> arrClass = Class.forName(str);
            return arrClass;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**参数的生成，对于各个类型：基本类型，数组，可变与不可变对象
     * 基本类型：随机生成
     * 数组：根据数组的维度生成指定类型的数组
     * 可变对象：接口，抽象类。使用子类生成
     * 不可变对象：使用原始类生成
     * */
    public Object generateCore(Class clx){
        if(clx == null) return null;
        if(clx.isArray()){
            int dim = numOfDimension(clx.getName(),"[");
            int[] dims = new int[dim];
            for(int i=0;i<dim;i++)
                dims[i] = lenOfEachDim;

            Object allArr = Array.newInstance(clx.getComponentType(), dims);/// new T[len][len];
            generateArr(allArr);
            return allArr;
        }
        //参数为基本类型
        else if(isPrimitive(clx)){
            if(clx == int.class){return random.randomInt();}
            else if(clx == double.class){return random.randomDouble();}
            else if(clx == short.class){return random.randomShort();}
            else if(clx == byte.class){return random.randombyte();}
            else if(clx == long.class){return random.randomLong();}
            else if(clx == float.class){return random.randomFloat();}
            else if(clx == char.class){return getRandomChar();}
            else {return random.randomBoolean();}
        }
        //参数为不可变类型,使用原始类构造
        else if(immutable.isImmutable(clx.getName())){
            if(clx == String.class) return getRandomString();
            else
                return randomConstructorInit(clx, true);
        }
        else {//可变引用类型
            String className = clx.getName();
            Class tempCLz = loadClass(className);
            if(tempCLz == null) return null;
            //接口或者抽象类
            if (tempCLz.isInterface() || Modifier.isAbstract(tempCLz.getModifiers())) {
                if( ! Configuration.implementors.containsKey(className)){
                    return null;
                }
                String subClass = Configuration.implementors.get(className);
//                String subClzName = "overidedSubclass.Ext"+subClass.substring(subClass.lastIndexOf(".")+1);
                return randomConstructorInit(loadClass(subClass), true);
            }
            else{
//                String subClzName = "overidedSubclass.Ext"+className.substring(className.lastIndexOf(".")+1);
                return randomConstructorInit(tempCLz, true);
            }
        }
    }

    /**数组的生成，可生成多维数组
     * 多维数组使用递归进行
     * */
    public void generateArr(Object arrObj){
        if(arrObj == null)
            return;

        Class component;
        Object val;

        for (int i = 0; i < Array.getLength(arrObj); i++) {
            component = arrObj.getClass().getComponentType();
            val = Array.get(arrObj, i);
            if(component.isArray()){
//                val = Array.newInstance(component.getComponentType(),lenOfEachDim);
                //如果元素是数组，则递归调用
                generateArr(val);
            }
            else{//非数组
                val = generateCore(component);
            }
            Array.set(arrObj, i, val);
        }
    }

    //todo test
    /**
     * 对于数组, 随机指定一个元素, 该元素类型为T
     * 若T为数组类型: mutateArr
     * 否则是非数组, 对该元素mutate
     * */
    public void mutateArr(Object arrObj){
        if(arrObj == null)
            return;

        Object element;

        int arrLen = Array.getLength(arrObj);
        logger.info("变异数组的长度: {}", arrLen);
        if(arrLen <= 0)
            return;

        int randomIndex = new Random().nextInt(arrLen);

        element = Array.get(arrObj, randomIndex);
        if(element == null)
            return;
        //如果元素是数组，则递归调用
        else if(element.getClass().isArray())
            mutateArr(element);
        else //非数组
            element = mutate(element, 0);

        Array.set(arrObj, randomIndex, element);
    }

     /**对象只有一个构造函数时，修改对象的得到public字段（不包括final）
 * 1.如果对象的字段有基本类型，那就修改基本类型的值
 * 2.如果没有基本类型的字段，对于对象字段，修改对象字段的基本字段; 对于数组，翻转数组
 * */
public Object onlyOneConstructorObjFilp(Object obj) throws IllegalAccessException{
    Class curClass = obj.getClass();
    int notFinalNum=0;
    int primitiveNum = 0;
    int all = curClass.getFields().length;
    //Log.v("num of Fields",curClass.getName()+"--"+String.valueOf(all));

    for(Field f : curClass.getFields()){//得到public 字段
        if(!Modifier.isFinal(f.getModifiers()) ){
            notFinalNum += 1;
            if(f.getType().isPrimitive()){//字段是基本类型
                primitiveNum++;
                if     (f.getType() == int.class){f.set(obj,random.randomInt());}
                else if(f.getType() == short.class){f.set(obj,random.randomShort());}
                else if(f.getType() == long.class){f.set(obj,random.randomLong());}
                else if(f.getType() == byte.class){f.set(obj,random.randombyte());}
                else if(f.getType() == double.class){f.set(obj,random.randomDouble());}
                else if(f.getType() == float.class){f.set(obj,random.randomFloat());}
                else if(f.getType() == char.class){f.set(obj,getRandomChar());}
                else if(f.getType() == boolean.class){f.set(obj,random.randomBoolean());}
            }
        }
    }
    if(primitiveNum != 0 ) return obj;

    //无public字段 || 全部为public final 重新生成对象
    if(all == 0 || (all-notFinalNum)  ==0) {
        return randomConstructorInit(obj.getClass(), true);
    }

    for(Field f : curClass.getFields()){
        Object fieldObj = f.get(obj);
        if(Modifier.isFinal(f.getModifiers()) || isPrimitive(f.getClass()))
            continue;
        //数组字段
        if(f.getType().isArray())
            mutateArr(fieldObj);
        else if(fieldObj != null)
            f.set(obj,randomConstructorInit(obj.getClass(), true)); ;
    }
    return obj;
}

    /**(原来)翻转参数时，考虑各种情况：
     * 1.基本类型（这里为包裹类）：重新随机生成
     * 2.数组(支持多维数组)：重新随机生成数组
     * 3.对象（可变及不可变对象）。不可变对象除了string,都是尽量新建一个对象，使用随机的构造函数构造新的对象
     * */
    // depth 控制深度 = 5
    public Object mutate(Object para, int depth){
        if(para == null) return null;

        if(depth >= Configuration.getMutatuDepth()){
            return para;
        }
        Object res ;
        Class clx = para.getClass();
        //数组,包含多维数组,随机选择一个元素变异
        if(clx.isArray()){
            int arrLen = Array.getLength(para);
            if(arrLen == 0){
                Object arr = Array.newInstance(clx.getComponentType(), lenOfEachDim);/// new T[len];
                generateArr(arr);
                return arr;
            }

            mutateArr(para);
            return para;
        }
        //对象为基本类型
        else if(isPrimitive(para.getClass())){
            if(para instanceof Integer){res = random.randomInt();  return res;}
            else if(para instanceof Short){res = random.randomShort();  return res;}
            else if(para instanceof Byte){res = random.randombyte();  return res;}
            else if(para instanceof Long){res = random.randomLong(); return res;}
            else if(para instanceof Double){res = random.randomDouble();  return res;}
            else if(para instanceof Float){res = random.randomFloat();  return res;}
            else if(para instanceof Character){res = getRandomChar();  return res;}
            else {res = !(boolean)para;  return res;}
        }
        //String类型
        else if(clx==String.class){res =getRandomString();  return res;}
        //不可变对象类型,重新用随机构造函数新建对象
        else if(immutable.isImmutable(clx.getName())){
            if(oneConstructorWithNonPara(clx))//只有一个无参构造函数
                return para;
            res = randomConstructorInit(clx, true);
            return res;
        }//随机选择一个对象内部声明的字段执行mutate
        else{
            if(depth >= Configuration.getMutatuDepth() - 1){//变异深度超过指定深度
                return  randomConstructorInit(clx,true);
            }
            Field[] fieldList = clx.getDeclaredFields();//去掉final字段
            List<Field> fields = new ArrayList();
            for(Field f : fieldList) fields.add(f);

            for(Field f : fieldList){
                if( Modifier.isFinal(f.getModifiers()) && ( isPrimitive(f.getType()) || immutable.isImmutable(f.getType().getName()))){
                    fields.remove(f);
                }
                if(clx == ArrayList.class && f.getName().equals("size")){//不能变异arrayList的size字段
                    fields.remove(f);
                }
            }


            int fieldLen = fields.size();
            if(fieldLen == 0){//无声明的字段,用随机构造函数新建对象
                res = randomConstructorInit(clx, true);
                return res;
            }
            //对字段值进行改变
            int randomIndex = new Random().nextInt(fieldLen);
            Field mutateField = fields.get(randomIndex);
            logger.info("变异{}的字段{}", para.getClass().getName(), mutateField.getType().getName());
            mutateField.setAccessible(true);

            try{
                Object mutateFieldVal = mutateField.get(para);
                Object newObj ;
                if(mutateFieldVal == null){
                    logger.info("字段{}为空,重新初始化",mutateField.getType().getName());
                    newObj = randomConstructorInit(mutateField.getType(),true);
                }
                else{
                    newObj = mutate( mutateFieldVal , depth+1);
                }
                mutateField.set(para, newObj);
            }catch (Exception e){
                e.printStackTrace();
            }
            return para;
        }
    }

    private boolean oneConstructorWithNonPara(Class clx) {
        Constructor[] constructors  = clx.getDeclaredConstructors();
        if(constructors.length == 0)
            return true;
        else if( constructors.length == 1){
            Constructor constructor = constructors[0];
            if(constructor.getParameterTypes().length == 0){
                return true;
            }
        }
        return false;
    }


    /**使用最简单的构造方法构造对象。  不分 可变和不可变类型
     * 最简单的构造方法规则：参数个数最少（无参）或参数全部为基本类型
     * 构造函数的参数如果有数组类型，这里只考虑了最多两维。
     *
     * @param className full name of reference type, such as android.graphics.Paint.
     * @return  object whose constructor has none para or all paras are primitive or has least paras
     * */
    public Object simplestConstructorInit(String className, boolean classIsOriginal){
        if(className == null || className.length() ==0) {
            logger.error("参数不合法");
            return null;
        }

        Class tempCLz = loadClass(className);
        if(tempCLz == null) return null;
        //Log.v("classLoader",tempCLz.getClassLoader().toString());
        Constructor[] constructors;
        if(classIsOriginal)
            constructors = tempCLz.getDeclaredConstructors();
        else
            constructors = tempCLz.getConstructors();


        if(constructors == null || constructors.length==0) {
            logger.error("{}类没有public构造函数",className);
            return null;
        }
        //无参
        try{
            Constructor newIns = tempCLz.getConstructor(new Class[] {});
            newIns.setAccessible(true);
            Object res = newIns.newInstance(new Object[] {});
            logger.info("{}被无参实例化得到{}",className,res);
            return res;
        }catch(Exception e){
            int minParaLen = Integer.MAX_VALUE;//初始化构造函数参数个数为最大值
            Constructor primitiveParaCon = null,leastParaCon = null;
            for(Constructor cl: constructors) {
                Class[] paratype = cl.getParameterTypes();
                if (paratype.length < minParaLen) {
                    minParaLen = paratype.length;
                    leastParaCon = cl;
                }
                int primitionNum = 0;
                for(Class pa : paratype){
                    if (pa.isPrimitive()){ primitionNum += 1;}
                }
                if(primitionNum == paratype.length ){primitiveParaCon = cl;}
            }
            //参数最简单的,最好全为基本类型
            if(primitiveParaCon != null)
                return newInstanceCon(tempCLz, primitiveParaCon);
            //或参数个数最少
            else if(leastParaCon !=null)
                return newInstanceCon(tempCLz, leastParaCon);
        }
        logger.error("{}无法被实例化",className);
        return null;
    }

    public Object newInstanceCon(Class thisClz, Constructor constructor){
        Class[] paratype = constructor.getParameterTypes();
        Object[] initargs = new Object[paratype.length];

        for(int i=0; i<paratype.length; i++){
            if(paratype[i].isAssignableFrom(thisClz) || thisClz.isAssignableFrom(paratype[i])) initargs[i] = null;
            else initargs[i] = generateCore(paratype[i]);
        }
        try {
            Object object = constructor.newInstance(initargs);
            logger.info("参数全为基本类型或参数最少的构造函数实例化的构造函数为{}",printConstructor(constructor));
            return object;
        } catch (Exception d) {d.printStackTrace();
        }
        return null;
    }

    /**随机选取一个构造函数来构造对象，包括可变和不可变类型
     *
     * */
    public Object randomConstructorInit(Class clz, boolean classIsOriginal){
        if(clz == null)
            return null;

        Object res = null;
        //String类型
        if(clz == String.class) {return getRandomString();}

        //随机选取一个构造函数
        Constructor[] constructors;
        if(classIsOriginal) constructors = clz.getConstructors();
        else constructors = clz.getConstructors();

        if(constructors.length<=0) return null;

        Constructor randomConstructor = constructors[new Random().nextInt(constructors.length)];

        randomConstructor.setAccessible(true);

        Class[] paratype = randomConstructor.getParameterTypes();
        //初始化参数值
        Object[] initargs = new Object[paratype.length];
        for(int ind = 0; ind < paratype.length; ind++){
            if(paratype[ind].isAssignableFrom(clz) /*|| clz.isAssignableFrom(paratype[ind])*/)
                initargs[ind] = null;
            else{
                if(clz == Parcel.class){
                    initargs[ind] = 0;//new Parcel(0)
                    continue;
                }
                initargs[ind] = generateCore(paratype[ind]);
            }
//            Log.v("initargs[ind]",String.valueOf(initargs[ind]));
        }

        try {
            Object object = randomConstructor.newInstance(initargs);
            logger.info("{}被随机构造函数{}实例化", clz, printConstructor(randomConstructor));
            res = object;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

//        //只有一个构造参数,修改基本类型字段，如果没有基本类型字段，则修改引用类型的基本类型字段
//        try{
//            res = onlyOneConstructorObjFilp(obj);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
        return res;
    }



    public static String printFields(Class cl)
    {
        Field[] fields = cl.getFields();
        StringBuilder sb = new StringBuilder();
        for (Field f : fields)
        {
            Class type = f.getType();
            String name = f.getName();
            sb.append("   ");
            String modifiers = Modifier.toString(f.getModifiers());
            if (modifiers.length() > 0) sb.append(modifiers + " ");
            sb.append(type.getName() + " " + name + ";\n");
        }
        return sb.toString();
    }
    /**@param c a given constructor
     * @return String,such as  public java.lang.String([B, java.nio.charset.Charset);
     * */
    public static String printConstructor(Constructor c)
    {
        StringBuilder str = new StringBuilder();
        String name = c.getName();
        str.append("   ");
        String modifiers = Modifier.toString(c.getModifiers());
        if (modifiers.length() > 0) str.append(modifiers + " ");
        str.append(name + "(");

        // print parameter types
        Class[] paramTypes = c.getParameterTypes();
        for (int j = 0; j < paramTypes.length; j++)
        {
            if (j > 0) str.append(", ");
            str.append(paramTypes[j].getName());
        }
        str.append(");");
        return str.toString();
    }
    public static void printMethods(Class cl)
    {
        Method[] methods = cl.getDeclaredMethods();

        for (Method m : methods)
        {
            Class retType = m.getReturnType();
            String name = m.getName();

            System.out.print("   ");
            // print modifiers, return type and method name
            String modifiers = Modifier.toString(m.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(retType.getName() + " " + name + "(");

            // print parameter types
            Class[] paramTypes = m.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++)
            {
                if (j > 0) System.out.print(", ");
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");
        }
    }
    /**Given a class, find all its constructors and print.
     * @param cl given a class
     * */
    public static void printConstructors(Class cl)
    {
        Constructor[] constructors = cl.getConstructors();

        for (Constructor c : constructors)
        {
            String name = c.getName();
            System.out.print("   ");
            String modifiers = Modifier.toString(c.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(name + "(");

            // print parameter types
            Class[] paramTypes = c.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++)
            {
                if (j > 0) System.out.print(", ");
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");
        }
    }

    public boolean isMutableRef(Class clx){


        if(isPrimitive(clx) )
            return false;
        if(immutable.isImmutable(clx.getName()) )
            return false;
        return true;
    }
    /**得到参数对象中的可变引用对象
     * 参数为数组，数组是可变引用对象
     * */
    public ArrayList getRef(ArrayList paras){
        ArrayList copyList = new ArrayList();
        for(Object para: paras){
            //判断数组类型    Determines if this Class object represents an array class
            if(para.getClass().isArray()){
                copyList.add(para);
                /*
                int dim = numOfDimension(paraName,"[");//判断维数
                if(dim == 1){
                    Class element = para.getClass().getComponentType();
                    if(!(isPrimitive(element) || immutable.isImmutable(element.getName()))){
                        copyList.add(para);
                    }
                }
                else if(dim == 2){
                    Class element = para.getClass().getComponentType().getComponentType();
                    if(!(isPrimitive(element) || immutable.isImmutable(element.getName()))){
                        copyList.add(para);
                    }
                }
                */
            }
            //判断不是基本类型和不可变类型
            else if(!isPrimitive(para.getClass()) && !immutable.isImmutable(para.getClass().getName()) ){
                    copyList.add(para);
            }
        }
        return copyList;
    }
    /**根据class判断是否为基本类型
     * @Para clx
     *
     * */

    public boolean isPrimitive(Class clx){
        if(clx == Integer.class || clx == int.class)   return true;
        else if(clx == Long.class || clx == long.class) return true;
        else if(clx == Short.class || clx == short.class) return true;
        else if(clx == Byte.class || clx == byte.class) return true;
        else if(clx == Float.class || clx == float.class) return true;
        else if(clx == Double.class || clx == double.class) return true;
        else if(clx == Character.class || clx == char.class) return true;
        else if(clx == Boolean.class || clx == boolean.class) return true;

        return false;
    }

    /*public static boolean isPrimitive(String type){
        if(type.equals("int")) return true;
        else if(type.equals("short")) return true;
        else if(type.equals("long")) return true;
        else if(type.equals("byte")) return true;
        else if(type.equals("float")) return true;
        else if(type.equals("double")) return true;
        else if(type.equals("char")) return true;
        else if(type.equals("boolean")) return true;
        else return false;
    }*/


    /**返回数组维度*/
    public static int numOfDimension(String mainStr, String subString){
        int count = 0;
        int index = 0;
        int fromindex = 0;
        while((index = mainStr.indexOf(subString,fromindex))>=0){
            count += 1;
            fromindex = index + subString.length();
        }
        return count;
    }

    public Class loadClass(String fullClassName){
        try{
            logger.info("loading the class name: {}",fullClassName);
            return  loader.loadClass(fullClassName);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
//            return forName(fullClassName);
//            logger.error("can not load the class: {}", fullClassName);
            return null;
        }
    }

    public static Class forName(String s){
        try {
            return Class.forName(s);
        } catch (ClassNotFoundException e) {
            logger.error("forname方法失败:{}",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    public String getFullClassName() {return fullClassName;}

    public String getReturnT() {return returnT;}

    public boolean isClassIsfinal() {return classIsfinal;}

    public void setClassIsfinal(boolean classIsfinal) {
        this.classIsfinal = classIsfinal;
    }

    public boolean isMthIsfinal() {
        return mthIsfinal;
    }

    public void setMthIsfinal(boolean mthIsfinal) {
        this.mthIsfinal = mthIsfinal;
    }

    public boolean isMthIsStatic() {
        return mthIsStatic;
    }

    public void setMthIsStatic(boolean mthIsStatic) {
        this.mthIsStatic = mthIsStatic;
    }

    public boolean isClasshasCloneMth() {
        return classhasCloneMth;
    }

    public void setClasshasCloneMth(boolean classhasCloneMth) {this.classhasCloneMth = classhasCloneMth;}

    public int getM(){return M;}

    public int getN(){return N;}

    public String getMethodName(){return methodToBeAnalysed.methodName;}

    public boolean isClassCanCompare() {
        return classCanCompare;
    }

    public void setClassCanCompare(boolean classCanCompare) {this.classCanCompare = classCanCompare;}

    public boolean isMthHasReturn() {
        return mthHasReturn;
    }

    public void setClassImpleSerializable(boolean classImpleSerializable) {
        this.classImpleSerializable = classImpleSerializable;
    }

    public boolean isClassImpleSerializable() {
        return classImpleSerializable;
    }

    public boolean isMthIsNative() {
        return mthIsNative;
    }

    public void setMthIsNative(boolean mthIsNative) {
        this.mthIsNative = mthIsNative;
    }



    public int getTestNum(){ return random.getTestNum(); }

    public int[][] getFlags(){
        return flags;
    }

    public void setFlag(int i,int j,int value){
        this.flags[i][j] = value;
    }

    public int getFlag(int i,int j){
        return this.flags[i][j];
    }

    public ArrayList getAllParas(){
        return allParas;
    }
    public String[] getExplictParaT() {
        return explictParaT;
    }
}
