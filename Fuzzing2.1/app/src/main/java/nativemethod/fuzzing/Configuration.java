package nativemethod.fuzzing;


import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by myw on 18-5-14.
 */

public class Configuration {
    private static Context mcontext = MyApplication.getContext();
    public static Random ran = new Random();

    public static HashMap<String,String> configMap = readConfig();;
    public static Set<String> nativeMthSig = readAppNativeMth();
    public static Map<String, String> implementors = readImplementor();

    private static Map<String,String> readImplementor() {
        Map<String,String> implementors = new HashMap<>();
        BufferedReader reader = null;
        try {
            InputStream in = mcontext.getResources().openRawResource(R.raw.implementor);
            reader = new BufferedReader(new InputStreamReader(in));
            String line ;
            while ((line = reader.readLine()) != null) {
                String[] temp = line.split(": ");
                implementors.put(temp[0], temp[1]);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            Log.e("无法找到文件",e.getMessage());
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException e1) {
                }
            }
        }
        return implementors;
    }

    public List<String> getAppNativeSigs() {
        return appNativeSigs;
    }

    public void setAppNativeSigs(List<String> appNativeSigs) {
        this.appNativeSigs = appNativeSigs;
    }

    public List<String> appNativeSigs ;

    public Configuration(){
        //configMap = readConfig();
        //nativeMthSig = readAppNativeMth();
        //ran = new Random();
    }





    public static HashMap readConfig(){
        HashMap<String,String> map = new HashMap();
        BufferedReader reader = null;
        try {
            InputStream in = mcontext.getResources().openRawResource(R.raw.configure);
            reader = new BufferedReader(new InputStreamReader(in));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                String[] tempStr = tempString.split("=");
                Log.v("item",tempStr[0]+":"+tempStr[1]);
                map.put(tempStr[0],tempStr[1]);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            Log.e("无法找到文件",e.getMessage());
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException e1) {
                }
            }
        }
        return map;
    }

    public static Set<String> readAppNativeMth(){
        Set<String> appNativeMthSig = new LinkedHashSet<>();
        BufferedReader reader = null;
        try {
            InputStream in = mcontext.getResources().openRawResource(R.raw.app_native_mth);
            reader = new BufferedReader(new InputStreamReader(in));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                appNativeMthSig.add(tempString);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            Log.e("无法找到文件",e.getMessage());
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException e1) {
                }
            }
        }
        return appNativeMthSig;
    }

    public static byte[] intToByte(int number) {
        int temp = number;
        byte[] b = new byte[4];
        for (int i = 0; i < b.length; i++) {
            b[i] = new Integer(temp & 0xff).byteValue();//
//将最低位保存在最低位
            temp = temp >> 8; // 向右移8位
        }
        return b;
    }
    public static char getRandomCharacter(char ch1,char ch2){
        return (char)(ch1+Math.random()*(ch2-ch1+1));//因为random<1.0，所以需要+1，才能取到ch2
    }
    public static char getRandomLowerCaseLetter(){
        return getRandomCharacter('a','z');
    }
    public static char getRandomUpperCaseLetter(){
        return getRandomCharacter('A','Z');
    }
    public static char getRandomDigitLetter(){
        return getRandomCharacter('0','9');
    }

    public static int getTestNum(){return Integer.valueOf(configMap.get("testNum")).intValue() ;}
    public static int randomInt(){
        return ran.nextInt(Integer.valueOf(configMap.get("int")).intValue());
    }
    public static short randomShort(){
        return new Integer(ran.nextInt(Short.valueOf(configMap.get("short")).intValue())).shortValue();
    }
    public static byte randombyte(){
        return new Integer(ran.nextInt(Byte.valueOf(configMap.get("byte")).intValue())).byteValue();
    }
    public static long randomLong(){
        Long max = new Long((String) configMap.get("long"));
        //if((long)max > Integer.MAX_VALUE){return ran.nextLong();}
        //else{return new Integer(ran.nextInt(max.intValue())).longValue();}
        return (long)(ran.nextDouble()*max);
    }
    public static float randomFloat(){
        Integer max = new Integer( configMap.get("float"));
        return ran.nextFloat()*max;}
    public static double randomDouble(){
        Long max = new Long((String) configMap.get("double"));
        return ran.nextDouble()*max;
    }
    public static boolean randomBoolean(){
        return ran.nextBoolean();
    }
    public static int getLenOfEachDim(){
        return new Integer(configMap.get("lenOfEachDim")).intValue();
    }
    public static int getStringLen(){
        return new Integer((String) configMap.get("StringLen")).intValue();
    }
    public static int getMutatuDepth(){
        return Integer.valueOf(configMap.get("mutateDepth")).intValue();
    }





    public static char getRandomCharacter(){
        return getRandomCharacter('\u0000','\uFFFF');
    }

    public static String getRandomString() {
        //随机字符串的随机字符库
        String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb = new StringBuffer();
        int len = KeyString.length();
        int length = getStringLen();
        for (int i = 0; i < length; i++) {
            sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
        }
        return sb.toString();
    }
    public static char getRandomChar(){
        //随机字符串的随机字符库
        String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,./;'[]=-!@#$%^&*";
        int len = KeyString.length();
        return KeyString.charAt((int) Math.round(Math.random() * (len - 1)));
    }



}
