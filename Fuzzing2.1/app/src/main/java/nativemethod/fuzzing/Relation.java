package nativemethod.fuzzing;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by myw on 18-6-12.
 */

public class Relation {
    private static final String JSON_MTH="method";
    private static final String JSON_PARAS="paras";
    private static final String JSON_VALUES="values";
    private static final String JSON_PARA_REF="para_ref";
    private static final String JSON_VALUE_REF="value_ref";
    private String mthName;

    private String paras;
    private Object values;
    private String para_ref;
    private Object value_ref;
    public Relation(String mthName, String paras, Object values, String para_ref, Object value_ref){
        this.mthName = mthName;
        this.paras = paras;
        this.values = values;
        this.para_ref = para_ref;
        this.value_ref = value_ref;
    }
    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        try {
            json.put(JSON_MTH,this.mthName);
            json.put(JSON_PARAS,this.paras);
            json.put(JSON_VALUES,this.values);
            json.put(JSON_PARA_REF,this.para_ref);
            json.put(JSON_VALUE_REF,this.value_ref);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
