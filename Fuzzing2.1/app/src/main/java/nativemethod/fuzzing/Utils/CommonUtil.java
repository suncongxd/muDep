package nativemethod.fuzzing.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by myw on 18-11-6.
 */

public class CommonUtil {

    public static Object getObjBySerializable(Object obj){
        try{
            ByteArrayOutputStream bout =  new ByteArrayOutputStream();
            ObjectOutputStream out2 = new ObjectOutputStream(bout);

            out2.writeObject(obj);
            out2.flush();

            //反序列化
            ObjectInputStream in2 = new ObjectInputStream( new ByteArrayInputStream(bout.toByteArray()));
            return in2.readObject();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }








}
