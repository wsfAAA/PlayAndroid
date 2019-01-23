package cmcc.com.playandroid.mvp.view;


import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import butterknife.BindView;
import cmcc.com.playandroid.R;
import cmcc.com.playandroid.mvp.presenter.HomePresenter;
import playandroid.cmcc.com.baselibrary.mvp.BaseMvpFragment;
import playandroid.cmcc.com.baselibrary.ui.BaseLoadingView;
import playandroid.cmcc.com.baselibrary.ui.ScrollRecyclerView;

/**
 * 首页
 */
public class HomeFragment extends BaseMvpFragment<HomePresenter> {

    @BindView(R.id.m_recyclerview)
    ScrollRecyclerView mRecyclerview;
    @BindView(R.id.m_smart_refresh)
    SmartRefreshLayout mSmartRefresh;
    @BindView(R.id.m_base_loading_view)
    public BaseLoadingView mLoadingView;

    private int pageCount = 0;

    public HomeFragment() {
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_home;
    }

    public void scrollToPosition(int position) {
        if (mRecyclerview != null) {
            mRecyclerview.scrollToPosition(position);
        }
    }

    @Override
    protected void onFragmentVisible() {
        mLoadingView.showContent();
        mBasePresenter.requestData(pageCount, true);
        mBasePresenter.requestBanner();
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerview.setAdapter(mBasePresenter.initAdapter());

        mLoadingView.setAnewListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageCount = 0;
                mBasePresenter.requestBanner();
                mBasePresenter.requestData(pageCount, true);
            }
        });

        mSmartRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageCount = 0;
                mBasePresenter.requestData(pageCount, false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageCount++;
                mBasePresenter.requestData(pageCount, true);
            }
        });
    }

    public SmartRefreshLayout getSmartRefresh() {
        return mSmartRefresh;
    }

    @Override
    public HomePresenter creatPersenter() {
        return new HomePresenter();
    }
}
