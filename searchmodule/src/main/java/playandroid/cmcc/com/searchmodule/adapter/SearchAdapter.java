package playandroid.cmcc.com.searchmodule.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ReflectUtils;
import com.blankj.utilcode.util.RegexUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;
import playandroid.cmcc.com.baselibrary.util.BaseUtils;
import playandroid.cmcc.com.searchmodule.R;
import playandroid.cmcc.com.searchmodule.R2;
import playandroid.cmcc.com.searchmodule.bean.SearchBean;

/**
 * Created by wsf on 2018/9/29.
 */
public class SearchAdapter extends ItemViewBinder<SearchBean.DataBean.DatasBean, SearchAdapter.ViewHolder> {

    private Context context;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.search_adapter_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull SearchBean.DataBean.DatasBean item) {
        List<SearchBean.DataBean.DatasBean.TagsBean> tags = item.getTags();
        if (tags != null && tags.size() > 0 && tags.get(0) != null && !TextUtils.isEmpty(tags.get(0).getUrl())) {
            holder.mImgPicture.setVisibility(View.VISIBLE);
            BaseUtils.loaderGlideImage(context,item.getEnvelopePic(),holder.mImgPicture);
            loaderData(holder, item);
        } else {
            holder.mImgPicture.setVisibility(View.GONE);
            loaderData(holder, item);
        }

        holder.mLlRoot.setTag(R.id.search_result_position_id,item.getLink());
        holder.mLlRoot.setOnClickListener(mListener);
        holder.mImagCollect.setOnClickListener(mCollectListener);
    }

    private void loaderData(@NonNull ViewHolder holder, SearchBean.DataBean.DatasBean datasBean) {
        holder.mTvAuthor.setText(datasBean.getAuthor());
        holder.mTvDate.setText(datasBean.getNiceDate());
        holder.mTvContent.setText(datasBean.getTitle()
                .replace("<em class='highlight'>","").replace("</em>",""));
        holder.mTvType.setText(datasBean.getChapterName());
    }

    private View.OnClickListener mListener;
    private View.OnClickListener mCollectListener;

    public void setOnItemClickListener(View.OnClickListener listener){
        mListener=listener;
    }

    /**
     * 收藏
     * @param listener
     */
    public void setCollectOnItemClickListener(View.OnClickListener listener){
        mCollectListener=listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.mtv_author)
        TextView mTvAuthor;
        @BindView(R2.id.mtv_date)
        TextView mTvDate;
        @BindView(R2.id.mImg_picture)
        ImageView mImgPicture;
        @BindView(R2.id.mtv_content)
        TextView mTvContent;
        @BindView(R2.id.mtv_type)
        TextView mTvType;
        @BindView(R2.id.mImag_collect)
        ImageView mImagCollect;
        @BindView(R2.id.mll_root)
        LinearLayout mLlRoot;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
