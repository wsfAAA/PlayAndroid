package cmcc.com.playandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;

import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import cmcc.com.playandroid.bean.CommonListBean;
import cmcc.com.playandroid.common.CommonFinal;
import cmcc.com.playandroid.common.CommonRequest;
import me.drakeet.multitype.ItemViewBinder;
import playandroid.cmcc.com.baselibrary.api.BaseApiService;
import playandroid.cmcc.com.baselibrary.net.interceptor.AddCookiesInterceptor;
import playandroid.cmcc.com.baselibrary.util.BaseUtils;
import playandroid.cmcc.com.baselibrary.util.WebViewRoute;
import cmcc.com.playandroid.webview.WebviewActivity;
import playandroid.cmcc.com.commonmodule.R;
import playandroid.cmcc.com.commonmodule.R2;

/**
 * 公用文章列表适配器
 */
public class CommonListViewBinder extends ItemViewBinder<CommonListBean.DataBean.DatasBean, CommonListViewBinder.ViewHolder> {

    private Context mContext;

    public CommonListViewBinder(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_common_list, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull CommonListBean.DataBean.DatasBean homeList) {
        Log.i("wsf","aaaaaaaaaaaaa:  "+homeList);
        holder.homeList = homeList;
        holder.context = mContext;
        if (!TextUtils.isEmpty(homeList.getEnvelopePic())) {
            holder.mImgCover.setVisibility(View.VISIBLE);
            BaseUtils.loaderGlideImage(mContext, homeList.getEnvelopePic(), holder.mImgCover);
        } else {
            holder.mImgCover.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(homeList.getTitle())) {
            String replace = homeList.getTitle().replace("&mdash;", "").replace("<em class='highlight'>", "").replace("</em>", "");
            holder.mTvTitle.setText(replace);
        }
        if (!TextUtils.isEmpty(homeList.getAuthor())) {
            holder.mTvName.setText(homeList.getAuthor());
            if (homeList.getTags() != null && homeList.getTags().size() > 0 && !TextUtils.isEmpty(homeList.getTags().get(0).getUrl())) {
                holder.mTvName.setText("查看 " + homeList.getAuthor() + " 文章");
            }
        }
        if (!TextUtils.isEmpty(homeList.getSuperChapterName())) {
            holder.mTvType.setText(homeList.getSuperChapterName());
        }
        if (!TextUtils.isEmpty(homeList.getNiceDate())) {
            holder.mTvTime.setText(homeList.getNiceDate());
        }

        if (homeList.isCollect()) {
            holder.mImgCollect.setImageResource(R.drawable.collect_no);
        } else {
            holder.mImgCollect.setImageResource(R.drawable.collect_yes);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.m_tv_title)
        TextView mTvTitle;
        @BindView(R2.id.m_tv_time)
        TextView mTvTime;
        @BindView(R2.id.m_tv_type)
        TextView mTvType;
        @BindView(R2.id.m_tv_name)
        TextView mTvName;
        @BindView(R2.id.card_root)
        CardView mCardRoot;
        @BindView(R2.id.m_img_cover)
        ImageView mImgCover;
        @BindView(R2.id.m_img_collect)
        ImageView mImgCollect;

        private CommonListBean.DataBean.DatasBean homeList;
        private Context context;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mCardRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, WebviewActivity.class);
                    intent.putExtra(WebViewRoute.WEBVIEW_URL, homeList.getLink());
                    context.startActivity(intent);
                }
            });
            mTvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<CommonListBean.DataBean.DatasBean.TagsBean> tags = homeList.getTags();
                    if (tags != null && homeList.getTags().size() > 0 && !TextUtils.isEmpty(homeList.getTags().get(0).getUrl())) {
                        Intent intent = new Intent(context, WebviewActivity.class);
                        intent.putExtra(WebViewRoute.WEBVIEW_URL, BaseApiService.BASE_URL + homeList.getTags().get(0).getUrl());
                        context.startActivity(intent);
                    }
                }
            });

            mImgCollect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Set<String> stringSet = SPUtils.getInstance().getStringSet(AddCookiesInterceptor.COOKIES);
                    if (stringSet==null||stringSet.isEmpty()){
                        ARouter.getInstance().build(CommonFinal.AROUTER_LOGIN).navigation();
                        return;
                    }
                    CommonRequest.setCollectCallblak(new CommonRequest.CollectCallblak() {
                        @Override
                        public void succeed(boolean collect) {
                            if (collect) {
                                mImgCollect.setImageResource(R.drawable.collect_no);
                                homeList.setCollect(true);
                            } else {
                                mImgCollect.setImageResource(R.drawable.collect_yes);
                                homeList.setCollect(false);
                            }
                        }
                    });
                    if (homeList.isCollect()) { //取消收藏
                        CommonRequest.requsetUnCollect(homeList.getId());
                    } else { //收藏
//                        CommonRequest.requsetCollect(homeList.getTitle(), homeList.getAuthor(), homeList.getLink());
                        CommonRequest.requsetCollect(homeList.getId());
                    }
                }
            });
        }
    }
}
