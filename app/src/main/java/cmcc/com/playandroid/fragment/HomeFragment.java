package cmcc.com.playandroid.fragment;


import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import butterknife.BindView;
import cmcc.com.playandroid.R;
import cmcc.com.playandroid.presenter.HomePresenter;
import playandroid.cmcc.com.baselibrary.mvp.BaseMvpFragment;

public class HomeFragment extends BaseMvpFragment<HomePresenter> {

    @BindView(R.id.m_recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.m_smart_refresh)
    SmartRefreshLayout mSmartRefresh;

    private int pageCount = 0;

    public HomeFragment() {
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onFragmentVisible() {
        mBasePresenter.requestData(pageCount,true);
        mBasePresenter.requestBanner();
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerview.setAdapter(mBasePresenter.initAdapter());

        mSmartRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageCount = 0;
                mBasePresenter.requestData(pageCount,false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageCount++;
                mBasePresenter.requestData(pageCount,true);
            }
        });
    }

    public SmartRefreshLayout getSmartRefresh(){
        return mSmartRefresh;
    }

    @Override
    public HomePresenter creatPersenter() {
        return new HomePresenter();
    }
}
