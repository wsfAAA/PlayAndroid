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

    public HomeFragment() {
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onFragmentVisible() {

    }

    @Override
    public HomePresenter creatPersenter() {
        return new HomePresenter();
    }
}
