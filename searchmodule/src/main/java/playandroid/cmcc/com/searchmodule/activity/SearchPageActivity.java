package playandroid.cmcc.com.searchmodule.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import me.drakeet.multitype.MultiTypeAdapter;
import playandroid.cmcc.com.baselibrary.base.ConfigMvpActivity;
import playandroid.cmcc.com.searchmodule.R;
import playandroid.cmcc.com.searchmodule.R2;
import playandroid.cmcc.com.searchmodule.presenter.SearchPagePresenter;

public class SearchPageActivity extends ConfigMvpActivity<SearchPagePresenter> {

    @BindView(R2.id.mrecycler)
    RecyclerView mRecycler;

    private int pageCount = 0;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_search_page;
    }

    @Override
    protected void initView() {
        Log.i("cesi---->", "SearchPageActivity initView");
        isShowMore(View.GONE);
        setSmartRefreshLayout((SmartRefreshLayout) findViewById(R.id.mSmartRefreshLayout));
        Intent intent = getIntent();
        final String searchContent = intent.getStringExtra(SearchActivity.INTENT_SEARCH_HOTKEY);
        setTitleText(searchContent);
        showLoading();
        mBasePresenter.searchRequest(searchContent, pageCount, true);

        MultiTypeAdapter multiTypeAdapter = mBasePresenter.initAdapter();
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecycler.setAdapter(multiTypeAdapter);

        mBaseLoadView.setAnewListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageCount = 0;
                mBasePresenter.searchRequest(searchContent, pageCount, true);
            }
        });

        mConfigSmartRefreshLayout .setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageCount = 0;
                mBasePresenter.searchRequest(searchContent, pageCount, true);
            }
        });
        mConfigSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageCount++;
                mBasePresenter.searchRequest(searchContent, pageCount, false);
            }
        });
    }

    public void stopRefresh(boolean success) {
        mConfigSmartRefreshLayout.finishRefresh(1000, success);
        mConfigSmartRefreshLayout.finishLoadMore(1000, success, false);
    }

    @Override
    public SearchPagePresenter creatPersenter() {
        return new SearchPagePresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("cesi---->", "SearchPageActivity onDestroy");
    }
}
