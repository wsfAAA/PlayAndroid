package playandroid.cmcc.com.baselibrary.base.jadapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class LongDefault0Adapter implements JsonSerializer<Long>, JsonDeserializer<Long> {
 @Override
 public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
  throws JsonParseException {
  try {
   if (json.getAsString().equals("") || json.getAsString().equals("null")) {//定义为long类型,如果后台返回""或者null,则返回0
     return 0l;
    }
   } catch (Exception ignore) {
  }
  try {
   return json.getAsLong();
  } catch (NumberFormatException e) {
   e.printStackTrace();
   //throw new JsonSyntaxException(e);
   return 0l;
  }
 }
 
 @Override
 public JsonElement serialize(Long src, Type typeOfSrc, JsonSerializationContext context) {
  return new JsonPrimitive(src);
 }
}