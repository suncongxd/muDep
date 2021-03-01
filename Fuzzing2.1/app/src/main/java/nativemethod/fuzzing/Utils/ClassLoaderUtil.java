package nativemethod.fuzzing.Utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;
import nativemethod.fuzzing.MyApplication;

/**
 * Created by myw on 20-3-25.
 */

public class ClassLoaderUtil {

    public static DexClassLoader getClassLoader(String apkName){
        try{
            Context thisContext = MyApplication.getContext();

            PathClassLoader pathClassLoader = (PathClassLoader) thisContext.getClassLoader();
            File optimizedDirectoryFile = thisContext.getDir("dex", Context.MODE_PRIVATE);//在应用安装目录下创建一个名为app_dex文件夹目录,如果已经存在则不创建

            String devicePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            return  new DexClassLoader( devicePath+ File.separator + apkName,
                    optimizedDirectoryFile.getAbsolutePath(),
//                    thisContext.getApplicationInfo().nativeLibraryDir,
                    null,
                    pathClassLoader /*pathClassLoader*/);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
