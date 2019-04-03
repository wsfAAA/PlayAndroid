package kotlintest.com.kotlinmodule.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import cmcc.com.playandroid.banner.BannerViewPager
import cmcc.com.playandroid.banner.callback.IBannerOnClick
import cmcc.com.playandroid.banner.callback.ILoaderImage
import cmcc.com.playandroid.webview.WebviewActivity
import kotlintest.com.kotlinmodule.BannerBean
import me.drakeet.multitype.ItemViewBinder
import playandroid.cmcc.com.baselibrary.util.BaseUtils
import playandroid.cmcc.com.baselibrary.util.WebViewRoute
import kotlin.com.kotlinmodule.R

/**
 * Created by wsf on 2019/4/1.   MultiType版本的 kotlin 使用
 */
class KotlinIBannerBinder() : ItemViewBinder<BannerBean, KotlinIBannerBinder.ViewHolder>() {

    lateinit var mContext: Context
    private lateinit var bannerUrl: MutableList<Any>

    constructor(context: Context) : this() {
        this.mContext = context
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_banner, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, banner: BannerBean) {
        bannerUrl = ArrayList()
        for (i in banner.data.indices) {
            bannerUrl.add(banner.data[i].imagePath)
        }

        holder.mBanner.initBanner(bannerUrl)
                .addIndicator(5)
                .isAutoPlay(true)
                .addViewPagerPageMargin(5f)
                .addViewPagerRightLeftPadding(20f)
                .addDelayTime(3000)
                .addIndicatorBottom(10)
                .addStyle(BannerViewPager.BANNER_3D_GALLERY_STYLE)
                .isShowIndicator(View.VISIBLE)
                .addLoaderImage(object : ILoaderImage {
                    override fun loaderImage(context: Context?, url: Any?, imageView: ImageView?) {
                        BaseUtils.loaderGlideImage(context, url, imageView, 10)
                    }
                })
                .addBannerOnClick(object : IBannerOnClick {
                    override fun onClick(position: Int) {
                        var intent = Intent(mContext, WebviewActivity::class.java)
                        intent.putExtra(WebViewRoute.WEBVIEW_URL, banner.data[position].url)
                        mContext.startActivity(intent)
                    }
                })
                .startBanner()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mBanner: BannerViewPager = itemView.findViewById(R.id.m_banner)
    }
}