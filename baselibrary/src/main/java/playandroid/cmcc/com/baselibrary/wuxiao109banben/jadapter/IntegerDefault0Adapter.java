package playandroid.cmcc.com.baselibrary.wuxiao109banben.jadapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by wsf on 2018/9/5.
 */

public class IntegerDefault0Adapter implements JsonSerializer<Integer>, JsonDeserializer<Integer> {
    public IntegerDefault0Adapter() {
    }

    public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            if(json.getAsString().equals("") || json.getAsString().equals("null")) {
                return Integer.valueOf(0);
            }
        } catch (Exception var6) {
            ;
        }

        try {
            return Integer.valueOf(json.getAsInt());
        } catch (NumberFormatException var5) {
            var5.printStackTrace();
            return Integer.valueOf(0);
        }
    }

    public JsonElement serialize(Integer src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }
}