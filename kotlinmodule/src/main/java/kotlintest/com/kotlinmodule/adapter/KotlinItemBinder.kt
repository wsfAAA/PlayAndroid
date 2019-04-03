package kotlintest.com.kotlinmodule.adapter

import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import cmcc.com.playandroid.bean.CommonListBean
import cmcc.com.playandroid.common.CommonFinal
import cmcc.com.playandroid.common.CommonRequest
import cmcc.com.playandroid.webview.WebviewActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.SPUtils
import me.drakeet.multitype.ItemViewBinder
import playandroid.cmcc.com.baselibrary.api.BaseApiService
import playandroid.cmcc.com.baselibrary.net.interceptor.AddCookiesInterceptor
import playandroid.cmcc.com.baselibrary.util.BaseUtils
import playandroid.cmcc.com.baselibrary.util.WebViewRoute
import kotlin.com.kotlinmodule.R

/**
 * Created by wsf on 2019/4/1.   MultiType版本的 kotlin 使用
 */
class KotlinItemBinder() : ItemViewBinder<CommonListBean.DataBean.DatasBean, KotlinItemBinder.ViewHolder>() {

    lateinit var mContext: Context

    constructor(context: Context) : this() {
        this.mContext = context
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_common_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, homeList: CommonListBean.DataBean.DatasBean) {
//        holder.homeList = homeList
//        holder.context = mContext

        if (!TextUtils.isEmpty(homeList.envelopePic)) {
            holder.mImgCover.visibility = View.VISIBLE
            BaseUtils.loaderGlideImage(mContext, homeList.envelopePic, holder.mImgCover)
        } else {
            holder.mImgCover.visibility = View.GONE
        }
        if (!TextUtils.isEmpty(homeList.title)) {
            var replace = homeList.title.replace("&mdash;", "").replace("<em class='highlight'>", "").replace("</em>", "")
            holder.mTvTitle.text = replace
        }

        if (!TextUtils.isEmpty(homeList.author)) {
            holder.mTvName.text = homeList.author
            if (homeList.tags != null && homeList.tags.size > 0 && !TextUtils.isEmpty(homeList.tags[0].url)) {
                holder.mTvName.text = "查看 " + homeList.author + " 文章"
            }
        }

        if (!TextUtils.isEmpty(homeList.superChapterName)) {
            holder.mTvType.text = homeList.superChapterName
        }
        if (!TextUtils.isEmpty(homeList.niceDate)) {
            holder.mTvTime.text = homeList.niceDate
        }

        if (homeList.isCollect) {
            holder.mImgCollect.setImageResource(R.drawable.collect_no)
        } else {
            holder.mImgCollect.setImageResource(R.drawable.collect_yes)
        }

        onclickListener(holder, homeList)
    }

    private fun onclickListener(holder: ViewHolder, homeList: CommonListBean.DataBean.DatasBean) {
        holder.mCardRoot.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var intent = Intent(mContext, WebviewActivity::class.java)
                intent.putExtra(WebViewRoute.WEBVIEW_URL, homeList.link)
                mContext.startActivity(intent)
            }
        })

        holder.mTvName.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var tags = homeList.tags
                if (tags != null && homeList.tags.size > 0 && !TextUtils.isEmpty(homeList.tags[0].url)) {
                    var intent = Intent(mContext, WebviewActivity::class.java)
                    intent.putExtra(WebViewRoute.WEBVIEW_URL, BaseApiService.BASE_URL + homeList.tags[0].url)
                    mContext.startActivity(intent)
                }
            }
        })

        holder.mImgCollect.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var stringSet = SPUtils.getInstance().getStringSet(AddCookiesInterceptor.COOKIES)
                if (stringSet == null || stringSet.isEmpty()) {
                    ARouter.getInstance().build(CommonFinal.AROUTER_LOGIN).navigation()
                    return
                }
                CommonRequest.setCollectCallblak(object : CommonRequest.CollectCallblak {
                    override fun succeed(collect: Boolean) {
                        if (collect) {
                            holder.mImgCollect.setImageResource(R.drawable.collect_no);
                            homeList.isCollect = true
                        } else {
                            holder.mImgCollect.setImageResource(R.drawable.collect_yes);
                            homeList.isCollect = false
                        }
                    }
                })
                if (homeList.isCollect) { //取消收藏
                    CommonRequest.requsetUnCollect(homeList.id)
                } else { //收藏
//                        CommonRequest.requsetCollect(homeList.title, homeList.author, homeList.link)
                    CommonRequest.requsetCollect(homeList.id)
                }
            }
        })
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        lateinit var homeList: CommonListBean.DataBean.DatasBean
//        lateinit var context: Context

        val mTvTitle: TextView = itemView.findViewById(R.id.m_tv_title)
        val mTvTime: TextView = itemView.findViewById(R.id.m_tv_time)
        val mTvType: TextView = itemView.findViewById(R.id.m_tv_type)
        val mTvName: TextView = itemView.findViewById(R.id.m_tv_name)
        val mCardRoot: CardView = itemView.findViewById(R.id.card_root)
        val mImgCover: ImageView = itemView.findViewById(R.id.m_img_cover)
        val mImgCollect: ImageView = itemView.findViewById(R.id.m_img_collect)
    }
}