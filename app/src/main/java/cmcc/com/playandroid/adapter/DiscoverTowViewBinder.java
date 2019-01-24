package cmcc.com.playandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cmcc.com.playandroid.R;
import cmcc.com.playandroid.bean.DiscoverBean;
import cmcc.com.playandroid.activity.DetailsContentActivity;
import me.drakeet.multitype.ItemViewBinder;
import cmcc.com.playandroid.common.CommonFinal;

/**
 * Created by wsf on 2019/1/23.
 */
public class DiscoverTowViewBinder extends ItemViewBinder<DiscoverBean.DataBean.ChildrenBean, DiscoverTowViewBinder.ViewHolder> {


    private final Context mContext;

    public DiscoverTowViewBinder(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_discover_tow_layout, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull DiscoverBean.DataBean.ChildrenBean discoverTow) {
        if (discoverTow == null) {
            return;
        }
        holder.mContext = mContext;
        holder.discoverTow = discoverTow;
        holder.mTvContent.setText(discoverTow.getName());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTvContent;
        private Context mContext;
        private DiscoverBean.DataBean.ChildrenBean discoverTow;

        ViewHolder(View itemView) {
            super(itemView);
            mTvContent = itemView.findViewById(R.id.m_tv_content);
            mTvContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (discoverTow != null) {
                        Intent intent = new Intent(mContext, DetailsContentActivity.class);
                        intent.putExtra(CommonFinal.DETAILS_PAGE_TITLE, discoverTow.getName());
                        intent.putExtra(CommonFinal.DETAILS_PAGE_ID, discoverTow.getId());
                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }
}
