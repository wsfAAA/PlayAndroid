package com.example.main_module.mvp.view;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.main_module.R;
import com.example.main_module.R2;
import com.example.main_module.adapter.DiscoverViewBinder;
import com.example.main_module.bean.DiscoverBean;
import com.example.main_module.mvp.presenter.DiscoverPresenter;

import butterknife.BindView;
import me.drakeet.multitype.MultiTypeAdapter;
import playandroid.cmcc.com.baselibrary.mvp.BaseMvpFragment;
import playandroid.cmcc.com.baselibrary.view.BaseLoadingView;

/**
 * 发现
 */
public class DiscoverFragment extends BaseMvpFragment<DiscoverPresenter> {


    @BindView(R2.id.m_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.m_loading_view)
    public BaseLoadingView mLoadingView;
    private MultiTypeAdapter mMultiTypeAdapter;

    public void scrollToPosition(int position) {
        if (mRecyclerView != null) {
            mRecyclerView.scrollToPosition(position);
        }
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void onFragmentVisible() {
        mLoadingView.showLoading();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mMultiTypeAdapter = new MultiTypeAdapter();
        mMultiTypeAdapter.register(DiscoverBean.DataBean.class, new DiscoverViewBinder(mContext));
        mMultiTypeAdapter.setItems(mBasePresenter.getData());
        mRecyclerView.setAdapter(mMultiTypeAdapter);

        mBasePresenter.requestData();

        mLoadingView.setAnewListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBasePresenter.requestData();
            }
        });
    }

    public MultiTypeAdapter getMultiTypeAdapter() {
        return mMultiTypeAdapter;
    }

    @Override
    public DiscoverPresenter creatPersenter() {
        return new DiscoverPresenter();
    }

}
