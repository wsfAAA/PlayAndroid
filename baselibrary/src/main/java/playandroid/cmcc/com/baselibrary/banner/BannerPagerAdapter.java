package playandroid.cmcc.com.baselibrary.banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import playandroid.cmcc.com.baselibrary.R;
import playandroid.cmcc.com.baselibrary.banner.callback.IBannerOnClick;
import playandroid.cmcc.com.baselibrary.banner.callback.ILoaderImage;

/**
 * Created by Administrator on 2018/11/28.
 * banner的适配器
 */

public class BannerPagerAdapter extends PagerAdapter {
    private List<String> mList = new ArrayList<>();
    private Context mContext;
    private IBannerOnClick iBannerOnClick;
    private ILoaderImage mLoaderImage;

    public BannerPagerAdapter(Context context) {
        this.mContext = context;
    }

    public void setImageData(List<String> data) {
        mList.clear();
        this.mList.addAll(data);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.banner_img_layout, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.banner_img);
        final int index = position % mList.size();
        container.addView(view);
        if (iBannerOnClick != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iBannerOnClick.onClick(index);
                }
            });
        }
        if (mLoaderImage != null) {
            mLoaderImage.loaderImage(mContext, mList.get(index), imageView);
        }
        return view;
    }

    public void setBannerOnClick(IBannerOnClick iBannerOnClick) {
        this.iBannerOnClick = iBannerOnClick;
    }

    public void addLoaderImage(ILoaderImage loaderImage) {
        this.mLoaderImage = loaderImage;
    }
}