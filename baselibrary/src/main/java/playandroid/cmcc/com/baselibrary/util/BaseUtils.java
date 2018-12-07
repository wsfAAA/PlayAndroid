package playandroid.cmcc.com.baselibrary.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by wsf on 2018/9/29.
 */

public class BaseUtils {

    public static void loaderGlideImage(Context context, Object imageUrl, ImageView imageView) {
        if (imageUrl == null || imageView == null) {
            return;
        }
        Glide.with(context).load(imageUrl).into(imageView);
    }

    /**
     * 请求参数转Json
     *
     * @return String
     */
    public static String toJson(WeakHashMap<String, Object> params) {
        JSONObject json = new JSONObject();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            try {
                json.put(entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return json.toString();
    }
}
