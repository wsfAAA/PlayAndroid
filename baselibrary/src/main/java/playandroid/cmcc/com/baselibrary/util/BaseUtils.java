package playandroid.cmcc.com.baselibrary.util;

import android.content.Context;
import android.os.Looper;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.WeakHashMap;

import playandroid.cmcc.com.baselibrary.R;
import playandroid.cmcc.com.baselibrary.banner.GlideRoundTransform;

/**
 * Created by wsf on 2018/9/29.
 */

public class BaseUtils {

    public static void loaderGlideImage(Context context, Object imageUrl, ImageView imageView) {
        if (imageUrl == null || imageView == null) {
            return;
        }
//        Glide.with(context).load(imageUrl).into(imageView);
        Glide.with(context)
                .load(imageUrl)
                .centerCrop()
                .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
                .placeholder(R.drawable.ic_banner_error)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }

    /**
     *  gile 添加圆角
     * @param context
     * @param imageUrl
     * @param imageView
     * @param corners    圆角
     */
    public static void loaderGlideImage(Context context, Object imageUrl, ImageView imageView,int corners) {
        if (imageUrl == null || imageView == null) {
            return;
        }
        Glide.with(context)
                .load(imageUrl)
                .centerCrop()
                .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
                .placeholder(R.drawable.ic_banner_error)
                .transform(new CenterCrop(context), new GlideRoundTransform(context,corners))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }


    public static final class GsonHoler{
        private static final Gson GSON=new Gson();
    }

    public static Gson getGsonInstance(){
        return GsonHoler.GSON;
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

    /**
     * 当前线程是否是主线程
     * @return
     */
    public static boolean isMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }
}
