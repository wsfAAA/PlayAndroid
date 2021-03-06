package com.example.main_module.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.main_module.R;
import com.example.main_module.bean.BannerBean;

import java.util.ArrayList;
import java.util.List;

import cmcc.com.playandroid.banner.BannerViewPager;
import cmcc.com.playandroid.banner.callback.IBannerOnClick;
import cmcc.com.playandroid.banner.callback.ILoaderImage;
import cmcc.com.playandroid.webview.WebviewActivity;
import me.drakeet.multitype.ItemViewBinder;
import playandroid.cmcc.com.baselibrary.util.BaseUtils;
import playandroid.cmcc.com.baselibrary.util.WebViewRoute;

/**
 * banner itme
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
        List<Object> bannerUrl = new ArrayList<>();
        for (int i = 0; i < banner.getData().size(); i++) {
            bannerUrl.add(banner.getData().get(i).getImagePath());
        }

        holder.mBanner.initBanner(bannerUrl)
                .addIndicator(5)
                .isAutoPlay(true)
                .addViewPagerPageMargin(5)
                .addViewPagerRightLeftPadding(20)
                .addDelayTime(3000)
                .addIndicatorBottom(10)
                .addStyle(BannerViewPager.BANNER_3D_GALLERY_STYLE)
                .isShowIndicator(View.VISIBLE)
                .addLoaderImage(new ILoaderImage() {
                    @Override
                    public void loaderImage(Context context, Object url, ImageView imageView) {
                        BaseUtils.loaderGlideImage(context, url, imageView,10);
                    }
                })
                .addBannerOnClick(new IBannerOnClick() {
                    @Override
                    public void onClick(int position) {
                        Intent intent = new Intent(mContext, WebviewActivity.class);
                        intent.putExtra(WebViewRoute.WEBVIEW_URL, banner.getData().get(position).getUrl());
                        mContext.startActivity(intent);
                    }
                })
                .startBanner();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final BannerViewPager mBanner;

        ViewHolder(View itemView) {
            super(itemView);
            mBanner = itemView.findViewById(R.id.m_banner);
        }
    }
}
