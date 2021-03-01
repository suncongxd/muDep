package stub.generation.utils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import stub.generation.pojo.Methoder;

import java.io.*;

import java.lang.reflect.Type;
import java.util.*;


public class FileUtils {
	
	public static String readToString(String fileName) {  
		String encoding = "UTF-8";
		File file = new File(fileName);
		if(!file.exists()){ return null; }

		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return new String(filecontent, encoding);
		} catch (UnsupportedEncodingException e) {
			System.err.println("The OS does not support " + encoding);
			e.printStackTrace();
			return null;
		}
	}

	public static Map<String , Set<String>> parseSSInNative(String name) {
		Map<String , Set<String>> ssMap = new HashMap<>();
		ArrayList<String> arrayList = new ArrayList<>();
		try {
			FileReader fr = new FileReader(name);
			BufferedReader bf = new BufferedReader(fr);
			String str;
			// 按行读取字符串
			Set<String> sourceList = new HashSet<>();
			Set<String> sinkList = new HashSet();
			Set<String> logList = new HashSet();
			Set<String> time = new HashSet();
			while ((str = bf.readLine()) != null) {
				if(str.contains("source: ") && str.split(": ").length > 1){
					for(String s : str.split(": ")[1].split("; ")){
						sourceList.add(s);
					}
				}
				if(str.contains("sink: ")  && str.split(": ").length > 1){
					for(String s : str.split(": ")[1].split("; ")){
						sinkList.add(s);
					}
				}if(str.contains("android_log_print: ") && str.split(": ").length > 1){
					for(String s : str.split(": ")[1].split("; ")){
						logList.add(s);
					}
				}
				if(str.contains("runtime: ")){
					time.add(str.split(": ")[1]);
				}

			}
			bf.close();
			fr.close();


            ssMap.put("source", sourceList);

            ssMap.put("sink", sinkList);

            ssMap.put("log", logList);

            ssMap.put("runtime", time);

			return ssMap;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static <T>   List<T> getMethod(String content,  Class<T> clx){

		List<T> res = new ArrayList<>();

		if(content == null || content.length() == 0)
			return res;

		String[] temp = content.split("------");
		for(String s : temp){
			T methoder = json2Obj(s , clx);
			if(methoder != null){
				res.add(methoder);
			}
		}

		return res;
	}

	public static <T> T json2Obj(String json, Class<T> clazz){
		Gson gson = new Gson();
		T bodyObj= gson.fromJson(json,clazz);
		return bodyObj;
	}

	public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz){
		Type type = new TypeToken<ArrayList<JsonObject>>(){}.getType();
		ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);

		ArrayList arrayList = new ArrayList();
		for(JsonObject jsonObject : jsonObjects)
			arrayList.add(new Gson().fromJson(jsonObject,clazz));
		return arrayList;
	}




    public static void append(String fileName, String content) {
        try {

            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");

            long fileLength = randomFile.length();

            randomFile.seek(fileLength);
            randomFile.writeBytes(content+"\r\n");
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

	

