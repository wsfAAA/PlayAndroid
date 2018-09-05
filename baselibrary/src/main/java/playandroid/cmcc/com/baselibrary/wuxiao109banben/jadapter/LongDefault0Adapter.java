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

public class LongDefault0Adapter implements JsonSerializer<Long>, JsonDeserializer<Long> {
    public LongDefault0Adapter() {
    }

    public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            if(json.getAsString().equals("") || json.getAsString().equals("null")) {
                return Long.valueOf(0L);
            }
        } catch (Exception var6) {
            ;
        }

        try {
            return Long.valueOf(json.getAsLong());
        } catch (NumberFormatException var5) {
            var5.printStackTrace();
            return Long.valueOf(0L);
        }
    }

    public JsonElement serialize(Long src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }
}
