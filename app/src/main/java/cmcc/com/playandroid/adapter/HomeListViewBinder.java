package cmcc.com.playandroid.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cmcc.com.playandroid.R;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by wsf on 2019/1/3.
 */
public class HomeListViewBinder extends ItemViewBinder<HomeList.DataBean.DatasBean, HomeListViewBinder.ViewHolder> {


    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_home_list, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull HomeList.DataBean.DatasBean homeList) {
        if (!TextUtils.isEmpty(homeList.getTitle())) {
            String replace = homeList.getTitle().replace("&mdash;", "");
            holder.mTvTitle.setText(replace);
        }
        if (!TextUtils.isEmpty(homeList.getAuthor())){
            holder.mTvName.setText(homeList.getAuthor());
        }
        if (!TextUtils.isEmpty(homeList.getSuperChapterName())){
            holder.mTvType.setText(homeList.getSuperChapterName());
        }
        if (!TextUtils.isEmpty(homeList.getNiceDate())){
            holder.mTvTime.setText(homeList.getNiceDate());
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.m_tv_title)
        TextView mTvTitle;
        @BindView(R.id.m_tv_time)
        TextView mTvTime;
        @BindView(R.id.m_tv_type)
        TextView mTvType;
        @BindView(R.id.m_tv_name)
        TextView mTvName;
        @BindView(R.id.card_root)
        CardView cardRoot;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
