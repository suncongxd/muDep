package nativemethod.fuzzing;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.rits.cloning.Cloner;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;
import nativemethod.fuzzing.Utils.ClassUtil;
import test.A;
import test.B;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("nativemethod.nativemethod.fuzzing", appContext.getPackageName());
    }

    @Test
    public void getClassUnderPackage() throws Exception {
        // Context of the app under test.
        Log.v("print test log", "-------");
//        List<Class<?>> overidedSubclasses = ClassUtil.getClassesFromPackage("nativemethod.nativemethod.fuzzing");
        Class[] overidedSubclasses = ClassUtil.getClasses("nativemethod.nativemethod.fuzzing");
        for(Class c : overidedSubclasses){
            Log.v("overidedSubclasses: ",c.getCanonicalName());
        }


        Context thisContext = MyApplication.getContext();
        String PACKAGE_NAME = thisContext.getPackageName();

        Log.v("PACKAGE_NAME",PACKAGE_NAME);




        try {
            DexFile df = new DexFile(MyApplication.getContext().getPackageCodePath());
            for (Enumeration<String> iter = df.entries(); iter.hasMoreElements();) {
                String s = iter.nextElement();
                Log.v("DexFile",s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testCloner() throws Exception {
        List<Integer> lsit = new ArrayList<>();
        for(int i=0;i<10;i++) lsit.add(i);
        B b = new B(lsit);
        A a = A.genA("hello", b);
        Cloner cloner = new Cloner();
        A a2  = cloner.deepClone(a);

        b.set(0,100);

        a.getB().print();
        a2.getB().print();

    }

    @Test
    public void testclass() throws Exception {
        String str= "android.util.Size"; //#android.util.Size
        Class sd = Class.forName(str);
        System.out.println("class name:"+sd.getName());
    }
}
