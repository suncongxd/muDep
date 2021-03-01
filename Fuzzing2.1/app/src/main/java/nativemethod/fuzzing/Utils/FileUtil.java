package nativemethod.fuzzing.Utils;

import android.content.Context;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import nativemethod.fuzzing.MyApplication;
import pojo.Methoder;

/**
 * Created by myw on 18-6-12.
 */

public class FileUtil {
    private static Context mContext  =MyApplication.getContext();;


    public static String obj2Str(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static void write(String fileName, Methoder method){

        if(method.getDependencies() == null || method.getDependencies().size() == 0) return;


        String content = obj2Str(method) + "------";

        appendContent(fileName, content);

    }

    public static void appendContent(String fileName,String content){
        //write the file to disk
        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(fileName,Context.MODE_APPEND);
            writer = new OutputStreamWriter(out);
            writer.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static void delete(String fileName) {
        mContext.deleteFile(fileName);
    }

    public static void append(String fileName, String content) {

        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content+"\n");
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeJson(String path,String content) {
        try {
            FileWriter fw = new FileWriter(path);
            fw.write(content);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
