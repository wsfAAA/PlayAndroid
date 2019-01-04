package cmcc.com.playandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import cmcc.com.playandroid.R;
import cmcc.com.playandroid.bean.BannerBean;
import me.drakeet.multitype.ItemViewBinder;
import playandroid.cmcc.com.baselibrary.util.GlideImageLoader;
import playandroid.cmcc.com.baselibrary.util.WebViewRoute;
import playandroid.cmcc.com.baselibrary.webview.WebviewActivity;

/**
 * Created by wsf on 2019/1/3.
 */
public class BannerViewBinder extends ItemViewBinder<BannerBean, BannerViewBinder.ViewHolder> {

    private Context mContext;

    public BannerViewBinder(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_banner, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull final BannerBean banner) {
        List<String> bannerUrl = new ArrayList<>();
        for (int i = 0; i < banner.getData().size(); i++) {
            bannerUrl.add(banner.getData().get(i).getImagePath());
        }
        //设置图片加载器
        holder.mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        holder.mBanner.setImages(bannerUrl);
        //设置自动轮播，默认为true
        holder.mBanner.isAutoPlay(true);
        //banner设置方法全部调用完毕时最后调用
        holder.mBanner.start();
        holder.mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int i) {
                Intent intent = new Intent(mContext, WebviewActivity.class);
                intent.putExtra(WebViewRoute.WEBVIEW_URL, banner.getData().get(i).getUrl());
                mContext.startActivity(intent);
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final Banner mBanner;

        ViewHolder(View itemView) {
            super(itemView);
            mBanner = itemView.findViewById(R.id.m_banner);
        }
    }
}
