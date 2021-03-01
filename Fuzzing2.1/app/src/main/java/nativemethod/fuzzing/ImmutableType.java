package nativemethod.fuzzing;

/**
 * Created by myw on 18-5-14.
 */

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ImmutableType {
    private List<String> immutableTypeList = new ArrayList();
    private Context mcontext;

    public ImmutableType(){
        mcontext = MyApplication.getContext();
        BufferedReader reader = null;
        try {
            InputStream in = mcontext.getResources().openRawResource(R.raw.immutable);
            reader = new BufferedReader(new InputStreamReader(in));

            String tempString;
            while ((tempString = reader.readLine()) != null) {
                immutableTypeList.add(tempString);
            }
        } catch (FileNotFoundException e) {
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
    }

    public List<String> getImmutableTypeList() {
        return immutableTypeList;
    }

    public void setImmutableTypeList(List<String> immutableTypeList) {
        this.immutableTypeList = immutableTypeList;
    }

    public void setImmutableTypeList(String type){
        immutableTypeList.add(type);
    }
    public boolean isImmutable(String str){
        return immutableTypeList.contains(str);
    }


}
