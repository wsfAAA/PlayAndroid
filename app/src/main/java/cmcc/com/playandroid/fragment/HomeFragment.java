package cmcc.com.playandroid.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import cmcc.com.playandroid.R;
import cmcc.com.playandroid.presenter.HomePresenter;
import playandroid.cmcc.com.baselibrary.mvp.BaseMvpFragment;

public class HomeFragment extends BaseMvpFragment<HomePresenter> {

    @BindView(R.id.m_recyclerview)
    RecyclerView mRecyclerview;

    private int pageCount = 0;

    public HomeFragment() {
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onFragmentVisible() {
        mBasePresenter.requestData(pageCount);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerview.setAdapter(mBasePresenter.initAdapter());
    }

    @Override
    public HomePresenter creatPersenter() {
        return new HomePresenter();
    }
}
