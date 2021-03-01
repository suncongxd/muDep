package nativemethod.fuzzing;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

/**
 * Created by myw on 18-10-31.
 */

public class DeepClone {

    public static void cloneArr(Object objArr, Object cloneArr) throws Exception {
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
    public static boolean compareArr(Object array1,Object array2){
        if(! (Array.getLength(array1) ==Array.getLength(array2)) ){return false;}
        try{
            Pair pair = computeEqualNum(array1,array2,0,0);
            if(pair.getEqualNum() == pair.getAllNum()){return true;}
            else return false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    /**计算两个数组中对应元素相等的个数*/
    public static Pair<Integer> computeEqualNum(Object array1, Object array2, int equalNum, int allNumbers) throws Exception{
        Object objTmp1,objTmp2;
        Object val1=null,val2 = null;

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
                else {//引用类型/*用反射查找equals方法 */
                    Method  equalsMethod;
                    try {
                        //先获取自己定义的clone方法
                        equalsMethod = objTmp1.getClass().getDeclaredMethod("equals", Object.class);

                    } catch (Exception e) {
                        //如果自身未定义clone方法，则从父类中找，但父类的clone一定要是public
                        equalsMethod = objTmp1.getClass().getMethod("equals", Object.class);
                    }
                    Object result = equalsMethod.invoke(objTmp1, objTmp2);
                    if(result.equals(true)){ equalNum+=1;}
                }
            }
        }
        return new Pair(equalNum,allNumbers);
    }

}
