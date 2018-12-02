package playandroid.cmcc.com.baselibrary.util;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        BaseUtils.loaderGlideImage(context, path,imageView);
    }
}
