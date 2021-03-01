package nativemethod.fuzzing;

/**
 * Created by myw on 19-2-19.
 */
import android.content.Context;
import android.util.Log;

@Deprecated
public class LoadPluginAPKCLass {
    public static void load(Context context, String apkPackageName, String singleClassName){
        try {
            Context c = context.createPackageContext(apkPackageName, Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);
            //载入这个类
            Log.i("loading the class",apkPackageName + "."+singleClassName);
            Class clazz = c.getClassLoader().loadClass(apkPackageName + "."+singleClassName);
            Object obj = clazz.newInstance();
//            Method method = clazz.getMethod("vulnerableMethod",null);
//            method.invoke(obj,new Object[0]);
            //  android.util.Log.w(TestEnv.TAG, "method: " + clazz.getName());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
