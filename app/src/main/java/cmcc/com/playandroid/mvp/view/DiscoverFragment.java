package cmcc.com.playandroid.mvp.view;


import cmcc.com.playandroid.R;
import cmcc.com.playandroid.mvp.presenter.DiscoverPresenter;
import playandroid.cmcc.com.baselibrary.mvp.BaseMvpFragment;

/**
 * 发现
 */
public class DiscoverFragment extends BaseMvpFragment<DiscoverPresenter> {


    public void scrollToPosition(int position) {
//        if (mRecyclerview != null) {
//            mRecyclerview.scrollToPosition(position);
//        }
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void onFragmentVisible() {

    }

    @Override
    public DiscoverPresenter creatPersenter() {
        return new DiscoverPresenter();
    }
}
