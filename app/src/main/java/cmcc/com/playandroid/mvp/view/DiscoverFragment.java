package cmcc.com.playandroid.mvp.view;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import cmcc.com.playandroid.R;
import cmcc.com.playandroid.adapter.DiscoverViewBinder;
import cmcc.com.playandroid.bean.DiscoverBean;
import cmcc.com.playandroid.mvp.presenter.DiscoverPresenter;
import me.drakeet.multitype.MultiTypeAdapter;
import playandroid.cmcc.com.baselibrary.mvp.BaseMvpFragment;
import playandroid.cmcc.com.baselibrary.ui.BaseLoadingView;

/**
 * 发现
 */
public class DiscoverFragment extends BaseMvpFragment<DiscoverPresenter> {


    @BindView(R.id.m_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.m_loading_view)
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
