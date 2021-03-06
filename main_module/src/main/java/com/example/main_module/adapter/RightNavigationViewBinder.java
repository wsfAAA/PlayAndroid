package com.example.main_module.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.main_module.R;
import com.example.main_module.bean.NavigationBean;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 导航 右边adapter
 */
public class RightNavigationViewBinder extends ItemViewBinder<NavigationBean.DataBean, RightNavigationViewBinder.ViewHolder> {

    private Context mContext;

    public RightNavigationViewBinder(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_navigation_right_layout, parent, false);
        return new ViewHolder(root,mContext);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull NavigationBean.DataBean navigation) {
        if (TextUtils.isEmpty(navigation.getName())) {
            return;
        }
        List<NavigationBean.DataBean.ArticlesBean> articles = navigation.getArticles();
        holder.adapter(articles, navigation.getName());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView mRecyclerView;
        private List<NavigationBean.DataBean.ArticlesBean> articles = new ArrayList<>();
        private MultiTypeAdapter mMultiTypeAdapter;
        private TextView mTitle;

        ViewHolder(View itemView,Context mContext) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.m_recycler_view);
            mTitle = itemView.findViewById(R.id.m_tv_name);
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

            //recyclervie 内部嵌套 recyclerview
            mMultiTypeAdapter = new MultiTypeAdapter();
            mMultiTypeAdapter.register(NavigationBean.DataBean.ArticlesBean.class, new NavigationContentViewBinder(mContext));
            mMultiTypeAdapter.setItems(articles);

            mRecyclerView.setAdapter(mMultiTypeAdapter);
        }

        private void adapter(List<NavigationBean.DataBean.ArticlesBean> articles, String name) {
            this.articles.clear();
            this.articles.addAll(articles);
            mTitle.setText(name);
            mMultiTypeAdapter.notifyDataSetChanged();
        }
    }
}
