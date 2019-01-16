package playandroid.cmcc.com.baselibrary.banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import playandroid.cmcc.com.baselibrary.R;
import playandroid.cmcc.com.baselibrary.util.BaseUtils;

/**
 * Created by Administrator on 2018/11/28.
 * banner的适配器
 */

public class BannerPagerAdapter extends PagerAdapter {
    private List<String> mList = new ArrayList<>();
    private Context mContext;
    private int defaultImg = R.drawable.ic_banner_error;//默认图片
    private BannerViewPager.IBannerOnClick iBannerOnClick;

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
        BaseUtils.loaderGlideImage(mContext, mList.get(index), imageView);
        container.addView(view);
        if (iBannerOnClick != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iBannerOnClick.onClick(index);
                }
            });
        }
        return view;
    }

    public void setBannerOnClick(BannerViewPager.IBannerOnClick iBannerOnClick) {
        this.iBannerOnClick = iBannerOnClick;
    }
}