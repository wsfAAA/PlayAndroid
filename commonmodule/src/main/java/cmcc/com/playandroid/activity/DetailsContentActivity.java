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
 * 文章列表页 详情页
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
        final String mTitle = getIntent().getStringExtra(CommonFinal.PAGE_TITLE);
        mId = getIntent().getIntExtra(CommonFinal.DETAILS_PAGE_ID, 0);
        final int mIntetnType = getIntent().getIntExtra(CommonFinal.INTENT_TYPE, 0); //  1 发现页跳转 0 搜索页跳转 2 收藏页跳转
        setTitleText(mTitle);

        mScrollRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mMultiTypeAdapter = new MultiTypeAdapter();
        mMultiTypeAdapter.register(CommonListBean.DataBean.DatasBean.class, new CommonListViewBinder(mContext));
        mMultiTypeAdapter.setItems(mBasePresenter.getData());
        mScrollRecyclerView.setAdapter(mMultiTypeAdapter);

        mBaseLoadView.showLoading();
        if (mIntetnType == 1) {
            mBasePresenter.requestData(mId, pageCount, true);
        } else if (mIntetnType == 2) {
            mBasePresenter.searchRequest(mTitle, pageCount, true);
        } else if (mIntetnType == 3) {
            mBasePresenter.requestCollect(pageCount,true);
        }

        mBaseLoadView.setAnewListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageCount = 0;
                if (mIntetnType == 1) {
                    mBasePresenter.requestData(mId, pageCount, true);
                } else if (mIntetnType == 2) {
                    mBasePresenter.searchRequest(mTitle, pageCount, true);
                } else if (mIntetnType == 3) {
                    mBasePresenter.requestCollect(pageCount,true);
                }
            }
        });

        mSmartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mSmartRefreshLayout.setEnableScrollContentWhenRefreshed(true);
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageCount++;
                if (mIntetnType == 1) {
                    mBasePresenter.requestData(mId, pageCount, false);
                } else if (mIntetnType == 2) {
                    mBasePresenter.searchRequest(mTitle, pageCount, false);
                } else if (mIntetnType == 3) {
                    mBasePresenter.requestCollect(pageCount,false);
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageCount = 0;
                if (mIntetnType == 1) {
                    mBasePresenter.requestData(mId, pageCount, true);
                } else if (mIntetnType == 2) {
                    mBasePresenter.searchRequest(mTitle, pageCount, true);
                } else if (mIntetnType == 3) {
                    mBasePresenter.requestCollect(pageCount,true);
                }
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
