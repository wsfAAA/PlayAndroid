package cmcc.com.playandroid.mvp.view;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import butterknife.BindView;
import cmcc.com.playandroid.R;
import cmcc.com.playandroid.adapter.HomeList;
import cmcc.com.playandroid.adapter.HomeListViewBinder;
import cmcc.com.playandroid.mvp.presenter.DetailsContentPresenter;
import me.drakeet.multitype.MultiTypeAdapter;
import playandroid.cmcc.com.baselibrary.base.ConfigMvpActivity;
import playandroid.cmcc.com.baselibrary.common.CommonFinal;
import playandroid.cmcc.com.baselibrary.ui.BaseLoadingView;
import playandroid.cmcc.com.baselibrary.ui.ScrollRecyclerView;

/**
 * 详情页
 */
public class DetailsContentActivity extends ConfigMvpActivity<DetailsContentPresenter> {

    @BindView(R.id.m_ScrollRecyclerView)
    ScrollRecyclerView mScrollRecyclerView;
    @BindView(R.id.m_SmartRefreshLayout)
    public SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.m_fab)
    FloatingActionButton mFab;

    private int mId;
    private int pageCount = 0;
    private MultiTypeAdapter mMultiTypeAdapter;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_details_content;
    }

    @Override
    protected void initView() {
        String mTitle = getIntent().getStringExtra(CommonFinal.DETAILS_PAGE_TITLE);
        mId = getIntent().getIntExtra(CommonFinal.DETAILS_PAGE_ID, 0);
        setTitleText(mTitle);

        mScrollRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mMultiTypeAdapter = new MultiTypeAdapter();
        mMultiTypeAdapter.register(HomeList.DataBean.DatasBean.class, new HomeListViewBinder(mContext));
        mMultiTypeAdapter.setItems(mBasePresenter.getData());
        mScrollRecyclerView.setAdapter(mMultiTypeAdapter);

        mBaseLoadView.showLoading();
        mBasePresenter.requestData(mId, pageCount, true);

        mBaseLoadView.setAnewListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageCount = 0;
                mBasePresenter.requestData(mId, pageCount, true);
            }
        });

        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageCount++;
                mBasePresenter.requestData(mId, pageCount, false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageCount = 0;
                mBasePresenter.requestData(mId, pageCount, true);
            }
        });

        mScrollRecyclerView.setRecyclerCallBack(new ScrollRecyclerView.RecyclerCallBack() {
            @Override
            public void onScrolledUp() {
                mFab.setVisibility(View.VISIBLE);
            }

            @Override
            public void onScrolledDown() {
                mFab.setVisibility(View.GONE);
            }

            @Override
            public void onScrolledToTop() {
                mFab.setVisibility(View.GONE);
            }

            @Override
            public void onScrolledToBottom() {
                mFab.setVisibility(View.GONE);
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScrollRecyclerView.scrollToPosition(0);
            }
        });
    }

    public MultiTypeAdapter getMultiTypeAdapter() {
        return mMultiTypeAdapter;
    }

    @Override
    public DetailsContentPresenter creatPersenter() {
        return new DetailsContentPresenter();
    }

}
