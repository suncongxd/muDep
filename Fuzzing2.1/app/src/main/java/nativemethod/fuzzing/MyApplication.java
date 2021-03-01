package nativemethod.fuzzing;
import android.app.Application;
import android.content.Context;

/**
 * Created by myw on 18-5-23.
 */

/**
 * 继承Application类来实现应用程序级的全局变量
 * */
public class MyApplication extends Application{
    private static Context context;
    private static Configuration random ;//初始化随机数类
    private static ImmutableType immutable;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        random = new Configuration();
        immutable = new ImmutableType();
    }
    public static Context getContext() {
        return context;
    }

    public static Configuration getConfiguration(){
        return random;
    }

    public static ImmutableType getImmutableType(){
        return immutable;
    }


//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }
}
