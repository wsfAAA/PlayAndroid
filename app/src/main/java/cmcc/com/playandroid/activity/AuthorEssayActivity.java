package cmcc.com.playandroid.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import butterknife.BindView;
import cmcc.com.playandroid.R;
import cmcc.com.playandroid.presenter.AuthorEssayPresenter;
import playandroid.cmcc.com.baselibrary.base.ConfigMvpActivity;
import playandroid.cmcc.com.baselibrary.common.CommonFinal;

/**
 * Created by wsf on 2019/1/4.
 */

public class AuthorEssayActivity extends ConfigMvpActivity<AuthorEssayPresenter> {
    @BindView(R.id.m_recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected int getLayoutResID() {
        return R.layout.author_essay_activity;
    }

    @Override
    protected void initView() {
        isActionBar(true);
        isShowBack(View.GONE);
        isShowMore(View.GONE);
        setSmartRefreshLayout((SmartRefreshLayout) findViewById(R.id.m_smart_refresh));

        String title = getIntent().getStringExtra(CommonFinal.TITLE_TYPE);
        setTitleText(title);
        String mUrl = getIntent().getStringExtra(CommonFinal.REQUEST_URL);
        mBaseLoadView.showLoading();
        mBasePresenter.requestData(mUrl);
        mConfigSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });
    }

    @Override
    public AuthorEssayPresenter creatPersenter() {
        return new AuthorEssayPresenter();
    }
}
