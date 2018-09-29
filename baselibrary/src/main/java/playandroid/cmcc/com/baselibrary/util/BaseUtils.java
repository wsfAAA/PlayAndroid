package playandroid.cmcc.com.baselibrary.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by wsf on 2018/9/29.
 */

public class BaseUtils {

    public static void loaderImage(Context context, String imageUrl, ImageView imageView) {
        if (TextUtils.isEmpty(imageUrl) || imageView == null) {
            return;
        }
        Glide.with(context).load(imageUrl).into(imageView);
    }
}
