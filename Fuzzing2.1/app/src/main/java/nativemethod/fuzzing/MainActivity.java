package nativemethod.fuzzing;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.rits.cloning.Cloner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;
import nativemethod.fuzzing.Utils.ClassLoaderUtil;
import nativemethod.fuzzing.Utils.FileUtil;
import pojo.Methoder;
import test.A;
import test.B;
import test.Interface1;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    private static final Logger logger = LoggerFactory.getLogger(MainActivity.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String FLAG_JSON = "flag.json";
        final String FINISHED = "finished.txt";

        List<String> apks = apkNameFromSdcard();
        String apkName = apks.get(0);//分析sdcard的第一个APK
        logger.info("apk名称:{}",apkName);
        //先删除设备上的flag.json文件
        FileUtil.delete(FLAG_JSON);
        FileUtil.delete(FINISHED);

        //启动后,读native.txt(一个apk所有的native方法签名),逐个对每个native方法分析
        Set<String> signatures = Configuration.nativeMthSig;
        List<Methoder> methoders = new ArrayList();

        Cloner cloner = new Cloner();
        ClassLoader classLoader = ClassLoaderUtil.getClassLoader(apkName);
        registerImutableToCloner(cloner, classLoader);

        for(String signature : signatures){
            logger.info("analysing method: {}", signature);
            Methoder methoder = new RunMethod(signature, apkName, cloner).run();
//            methoders.add( );


            Log.v("方法分割线{}","------------------------------------------------------------");
            //写文件 运行完一个方法，有依赖关系就写文件，防止不可知chowing导致整个apk都无依赖关系
            FileUtil.write(FLAG_JSON, methoder);
        }

        FileUtil.appendContent(FINISHED, "all native method have been analysed successfullly");







//        List<Integer> lsit = new ArrayList<>();
//        for(int i=0;i<10;i++) lsit.add(i);
//        B b = new B(lsit);
//        A a = A.genA("hello", b);
//        Cloner cloner = new Cloner();
//        A a2  = cloner.deepClone(a);
//
//        b.set(0,100);
//
//        a.getB().print();
//        a2.getB().print();

//        List<String> apks = apkNameFromSdcard();
//        String apkName = apks.get(0);//分析sdcard的第一个APK
//        logger.info("apk名称:{}",apkName);
//
//        Log.v("print test log", "-------");
//        Log.v("mainacitivity class ", MainActivity.class.getCanonicalName());
//        List<ClassLoader> classLoadersList = new Li   nkedList<ClassLoader>();
//        ClassLoader pathClassLoader =  this.getClassLoader();
//
//        classLoadersList.add(ClassLoaderUtil.getClassLoader(apkName));
//        classLoadersList.add(ClasspathHelper.contextClassLoader());
//        classLoadersList.add(ClasspathHelper.staticClassLoader());
//
//        Reflections reflections =
//                new Reflections(new ConfigurationBuilder()
//                .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
//                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
//                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("nativemethod.fuzzing"))));
////        new Reflections(new ConfigurationBuilder()
////                .setUrls(ClasspathHelper.forPackage("nativemethod.fuzzing"))
////                .setScanners(new SubTypesScanner()));
////        Reflections reflections = new Reflections("test");
//        Set<Class<? extends  android.app.Application>> classes = reflections.getSubTypesOf(android.app.Application.class);
//        for (Class c : classes){
//            Log.v("Application",c.getCanonicalName());
//        }
//
//        Set<Class <? extends  Enumeration>> sd = reflections.getSubTypesOf(Enumeration.class);
//        for (Class c : sd){
//            Log.v("Enumeration",c.getCanonicalName());
//        }

//        Log.v("haha",getUninstallApkInfo(this,
//                Environment.getExternalStorageDirectory().getAbsolutePath()+File.pathSeparator+"interact_nativecode.apk")[0]);
//        getPluginResources("interact_nativecode.apk", Environment.getExternalStorageDirectory().toString());


//        load the 3rh App class and .so file
//        try {/*Environme-nt.getExternalStorageDirectory().getAbsolutePath()*/
//            dynamicLoadApkUsingDexClassloader(this,Environment.getExternalStorageDirectory().getAbsolutePath() ,
//                    "interact_nativecode.apk","com.example.interact_nativecode");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        logger.info("MEDIA_MOUNTED:{}",Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()));;//true
//        logger.info("MEDIA_MOUNTED:{}",);;

//        File[] files = this.getExternalMediaDirs();
//        for(File f : files)
//            logger.info("getExternalMediaDirs: {}",f.getAbsolutePath());
//        File[] files = getExternalFilesDirs(Environment.MEDIA_MOUNTED);
//        for(File file:files){
//            logger.info("getExternalFilesDirs: {}",file.getAbsolutePath());
//        }
    }

    private void registerImutableToCloner(Cloner cloner ,ClassLoader classLoader) {
        ImmutableType immutableType = MyApplication.getImmutableType();
        List<String> immuType = immutableType.getImmutableTypeList();
        int failed  = 0 ;
        for(String immu : immuType) {

            try {
//                System.out.println(immu);
                Class clx = Class.forName(immu);
                cloner.registerImmutable(clx);
            } catch (Exception e) {
                failed++;
            }
        }
        logger.error("加载失败的不可变类数量：{}", failed);//273
    }

    public List<String> apkNameFromSdcard(){//Environment.getExternalStoragePublicDirectory()

        Log.v("external device path",Environment.getExternalStorageDirectory().getAbsolutePath());
        List<String> res = new ArrayList<>();
        File[] files = Environment.getExternalStorageDirectory().listFiles();
        if(files == null)
//            throw new IllegalArgumentException("sdcard不可用,读不到apk文件");
            logger.error("sdcard不可用,读不到apk文件");
        for(File f: files){
            if(f.isFile() && f.getName().endsWith(".apk")){
                logger.info("file on external device: {}",f.getName());
                res.add(f.getName());
            }
        }
        return res;
    }

    private void dynamicLoadApkUsingDexClassloader(Context appContext, String devicePath, String apkName, String apkPackageName) throws Exception {
        File optimizedDirectoryFile = appContext.getDir("dex", Context.MODE_PRIVATE);//在应用安装目录下创建一个名为app_dex文件夹目录,如果已经存在则不创建
        File libPath=  appContext.getDir("lib",Context.MODE_PRIVATE);
        String lib = "";
        String libpath=devicePath + File.separator + apkPackageName + File.separator +
                "lib" + File.separator /*/+ "arm64-v8a" + File.separator */ + "libnative-lib.so";
        Log.v("自己拼接的path",libpath);// /data/data/nativemethod.nativemethod.fuzzing/app_dex
        //复制lib
        Log.v("so文件名称",new File(libpath).getName());
        Log.v("是文件目录", String.valueOf( new File(libpath).isDirectory()) );
        Log.v("文件存在", String.valueOf( new File(libpath).exists()) );
        //File libFile = new File(libpath);
        //File out=new File(libPath.getAbsolutePath()+"/"+new File(libpath).getName());
        //verifyStoragePermissions(this);
        //copyFile(libFile,out);
        //lib+=out.getAbsolutePath();


//        for(File f:libssd)
//        {
//            Log.v("file Name", f.getName());
//            File out=new File(libPath.getAbsolutePath()+"/"+f.getName());
//            copyFile(f,out);
//            lib+=out.getAbsolutePath();
//            if(++i!=libssd.length)lib+=":";
////        }
        Log.v("该应该程序所在的app_dex文件夹目录", optimizedDirectoryFile.getPath().toString());// /data/data/nativemethod.nativemethod.fuzzing/app_dex
        Log.v("this context",appContext.toString());
        Log.v("application context", MyApplication.getContext().toString());
        //这一步很重要
        PathClassLoader pathClassLoader = (PathClassLoader) appContext.getClassLoader();
//        ClassLoader.getSystemClassLoader()
        //参数：1、包含dex的apk文件或jar文件的路径，2、apk、jar解压缩生成dex存储的目录，3、本地library库目录，一般为null，4、父ClassLoader
        DexClassLoader  dexClassLoader= new DexClassLoader(devicePath + File.separator + apkName,
                optimizedDirectoryFile.getAbsolutePath(), "/home/myw/workspace/eclipse_workspace/interact_nativecode/libs", pathClassLoader);

        Class<?> clazz = dexClassLoader.loadClass(apkPackageName + ".MainActivity");//通过使用apk自己的类加载器，反射出R类中相应的内部类进而获取我们需要的资源id


        if(clazz ==null){Log.v("加载的类为空","");return;}
        Log.v("class name",clazz.getName());

        //反射调用本地方法
        callNativeMth(clazz);

    }



    private void copyFile(File in, File out) {
        BufferedReader reader =null;
        BufferedWriter writer=null;
        try {
             reader = new BufferedReader(new FileReader(in));
            String line;
             writer = new BufferedWriter(new FileWriter(out));
            while((line = reader.readLine())!=null){
                writer.write(line);
            }
        } catch (FileNotFoundException e  ) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
        }
    }

    private void callNativeMth(Class<?> clazz) {
        try{
            Object instance= clazz.newInstance();
            for(Method mth :clazz.getDeclaredMethods()){
                Log.v("method name",mth.getName());
            }
            Class[] nul = new Class[0];
            Method method = clazz.getDeclaredMethod("nativePrint",nul);
            if(method ==null){Log.v("找不到方法名",clazz.getName());return; }
            method.setAccessible(true);
            Object[] empty = new Object[0];
            method.invoke(instance,empty);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取未安装apk的信息
     * @param context
     * @param archiveFilePath apk文件的path
     * @return
     */
    private String[] getUninstallApkInfo(Context context, String archiveFilePath) {
        String[] info = new String[2];
        PackageManager pm = context.getPackageManager();
        PackageInfo pkgInfo = pm.getPackageArchiveInfo(archiveFilePath,PackageManager.GET_ACTIVITIES);
        if (pkgInfo != null) {
            ApplicationInfo appInfo = pkgInfo.applicationInfo;
            String versionName = pkgInfo.versionName;//版本号
            String appName = pm.getApplicationLabel(appInfo).toString();//app名称
            String pkgName = appInfo.packageName;//包名
            info[0] = appName;
            info[1] = pkgName;
        }
        return info;
    }

    /**
     * @param apkName
     * @return 得到对应插件的Resource对象
     */
    private Resources getPluginResources(String apkName,String apkDir) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);//反射调用方法addAssetPath(String path)
            //第二个参数是apk的路径：Environment.getExternalStorageDirectory().getPath()+File.separator+"plugin"+File.separator+"apkplugin.apk"
            addAssetPath.invoke(assetManager, apkDir+ File.separator+apkName);//将未安装的Apk文件的添加进AssetManager中，第二个参数为apk文件的路径带apk名
            Resources superRes = this.getResources();
            Resources mResources = new Resources(assetManager, superRes.getDisplayMetrics(),
                    superRes.getConfiguration());
            return mResources;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private void add(Context context){
        //根据包名，加载Plugin apk中的类
        try{
            String packageName  ="com.example.interact_nativecode.MainActivity";
            Context  mcontext = context.createPackageContext(packageName, Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);
            String path = mcontext.getApplicationInfo().sourceDir;
            Log.v("createPackageContext path",path);
            Log.v("library search path","/data/app-lib/"+path.substring(path.lastIndexOf("/")+1,path.lastIndexOf(".")));
            ClassLoader loader = new PathClassLoader(path,"/data/app-lib/"+path.substring(path.lastIndexOf("/")+1,path.lastIndexOf(".")),context.getClassLoader());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


//    private static final int REQUEST_EXTERNAL_STORAGE = 1;
//    private static String[] PERMISSIONS_STORAGE = {
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//    };
//    public static void verifyStoragePermissions(Activity activity) {
//        // Check if we have write permission
//        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        Log.v("permission",String.valueOf(permission));
//
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            // We don't have permission so prompt the user
//            ActivityCompat.requestPermissions(
//                    activity,
//                    PERMISSIONS_STORAGE,
//                    REQUEST_EXTERNAL_STORAGE
//            );
//        }
//    }


}
