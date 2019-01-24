package cmcc.com.playandroid.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import butterknife.BindView;
import cmcc.com.playandroid.R;
import cmcc.com.playandroid.R2;
import cmcc.com.playandroid.adapter.CommonListViewBinder;
import cmcc.com.playandroid.bean.CommonListBean;
import cmcc.com.playandroid.view.ScrollRecyclerView;
import me.drakeet.multitype.MultiTypeAdapter;
import playandroid.cmcc.com.baselibrary.base.ConfigMvpActivity;
import cmcc.com.playandroid.common.CommonFinal;

/**
 * 详情页
 */
public class DetailsContentActivity extends ConfigMvpActivity<DetailsContentPresenter> {

    @BindView(R2.id.m_ScrollRecyclerView)
    ScrollRecyclerView mScrollRecyclerView;
    @BindView(R2.id.m_SmartRefreshLayout)
    public SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R2.id.m_fab)
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
        mMultiTypeAdapter.register(CommonListBean.DataBean.DatasBean.class, new CommonListViewBinder(mContext));
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

        mSmartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mSmartRefreshLayout.setEnableScrollContentWhenRefreshed(true);
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
                if (mFab != null) {
                    mFab.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onScrolledDown() {
                if (mFab != null) {
                    mFab.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScrolledToTop() {
                if (mFab != null) {
                    mFab.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScrolledToBottom() {
                if (mFab != null) {
                    mFab.setVisibility(View.GONE);
                }
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
