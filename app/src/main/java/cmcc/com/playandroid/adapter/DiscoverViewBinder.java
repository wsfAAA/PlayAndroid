package cmcc.com.playandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cmcc.com.playandroid.R;
import cmcc.com.playandroid.bean.DiscoverBean;
import cmcc.com.playandroid.bean.NavigationBean;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by wsf on 2019/1/23. 发现页adapter
 */
public class DiscoverViewBinder extends ItemViewBinder<DiscoverBean.DataBean, DiscoverViewBinder.ViewHolder> {

    private Context mContext;

    public DiscoverViewBinder(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_discover_layout, parent, false);
        return new ViewHolder(root, mContext);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull DiscoverBean.DataBean discover) {
        if (discover.getChildren() == null || TextUtils.isEmpty(discover.getName())) {
            return;
        }
        holder.adapter(discover);
        holder.mTvTitle.setText(discover.getName());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.m_tv_title)
        TextView mTvTitle;
        @BindView(R.id.m_recycler_view)
        RecyclerView mRecyclerView;
        @BindView(R.id.card_root)
        CardView mCardRoot;
        private DiscoverBean.DataBean dataBean;
        private MultiTypeAdapter mMultiTypeAdapter;

        ViewHolder(View itemView, Context mContext) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            //发现页 子 recyclerview
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, RecyclerView.VERTICAL));
            mMultiTypeAdapter = new MultiTypeAdapter();
            mMultiTypeAdapter.register(DiscoverBean.DataBean.ChildrenBean.class, new DiscoverTowViewBinder(mContext));
            mRecyclerView.setAdapter(mMultiTypeAdapter);

            mTvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dataBean != null) {
                        ToastUtils.showShort(dataBean.getName());
                    }
                }
            });
        }

        private void adapter(DiscoverBean.DataBean dataBean) {
            this.dataBean = dataBean;
            mMultiTypeAdapter.setItems(this.dataBean.getChildren());
            mMultiTypeAdapter.notifyDataSetChanged();
        }
    }
}