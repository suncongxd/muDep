package nativemethod.fuzzing;


/**
 * Created by myw on 19-2-20.
 */

public class MethodToBeAnalysed {
    String packageName;
    String singleClassName;
    String[] parasTypes;
    String returnType;
    String methodName;

    //根据方法签名得到：方法所在类名，方法名，方法参数类型列表，返回类型。
    //(example.interact_nativemethod.Data,example.interact_nativemethod.Eavesdropper,boolean)>
    public MethodToBeAnalysed(String mthSig){
        String FullclassName = mthSig.split(": ")[0].substring(1);;
        int lastComma = FullclassName.lastIndexOf(".");
        packageName = FullclassName.substring(0,lastComma);
        singleClassName = FullclassName.substring(lastComma+1);

        String[] sTemp = mthSig.split(": ")[1].split(" ");
        returnType = sTemp[0];
        methodName = sTemp[1].substring(0,sTemp[1].indexOf("("));
        String paraStr = sTemp[1].substring(sTemp[1].indexOf("(")+1, sTemp[1].indexOf(")"));
        if(paraStr.length() > 0 ){
            parasTypes = paraStr.split(",");
        }
        else{
            parasTypes = new String[0];
        }

    }
}
