package playandroid.cmcc.com.baselibrary.banner.callback;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by wsf on 2019/1/16.
 */

public interface ILoaderImage {
    /**
     *  图片 加载回调
     * @param context
     * @param url      url 、id
     * @param imageView
     */
    void loaderImage(Context context, Object url, ImageView imageView);
}
