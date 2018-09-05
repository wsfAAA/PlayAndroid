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

public class DoubleDefault0Adapter implements JsonSerializer<Double>, JsonDeserializer<Double> {
    public DoubleDefault0Adapter() {
    }

    public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            if(json.getAsString().equals("") || json.getAsString().equals("null")) {
                return Double.valueOf(0.0D);
            }
        } catch (Exception var6) {
            ;
        }

        try {
            return Double.valueOf(json.getAsDouble());
        } catch (NumberFormatException var5) {
            var5.printStackTrace();
            return Double.valueOf(0.0D);
        }
    }

    public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }
}