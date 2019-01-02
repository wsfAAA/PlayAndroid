package cmcc.com.playandroid.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import cmcc.com.playandroid.R;
import cmcc.com.playandroid.presenter.HomePresenter;
import cmcc.com.playandroid.test.TestAdapter;
import cmcc.com.playandroid.test.TestAdapterViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;
import playandroid.cmcc.com.baselibrary.mvp.BaseMvpFragment;

public class HomeFragment extends BaseMvpFragment<HomePresenter> {

    @BindView(R.id.m_recyclerview)
    RecyclerView mRecyclerview;
    private ArrayList<TestAdapter> data = new ArrayList<>();

    public HomeFragment() {
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onFragmentVisible() {
        for (int i = 0; i < 100; i++) {
            data.add(new TestAdapter(i + "数据"));
        }
        MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter();
        multiTypeAdapter.setItems(data);
        multiTypeAdapter.register(TestAdapter.class, new TestAdapterViewBinder());
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerview.setAdapter(multiTypeAdapter);
    }

    @Override
    public HomePresenter creatPersenter() {
        return new HomePresenter();
    }
}
