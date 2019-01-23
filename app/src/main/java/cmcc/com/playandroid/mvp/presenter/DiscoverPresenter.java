package cmcc.com.playandroid.mvp.presenter;

import cmcc.com.playandroid.mvp.model.DiscoverModel;
import cmcc.com.playandroid.mvp.view.DiscoverFragment;
import playandroid.cmcc.com.baselibrary.mvp.BasePresenter;

/**
 * Created by wsf on 2019/1/23.
 */

public class DiscoverPresenter extends BasePresenter<DiscoverFragment, DiscoverModel> {
    @Override
    public DiscoverModel creatModel() {
        return new DiscoverModel();
    }
}
