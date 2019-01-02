package cmcc.com.playandroid.presenter;

import cmcc.com.playandroid.fragment.HomeFragment;
import cmcc.com.playandroid.model.HomeModel;
import playandroid.cmcc.com.baselibrary.mvp.BasePresenter;

/**
 * Created by wsf on 2019/1/2.
 */

public class HomePresenter extends BasePresenter<HomeFragment, HomeModel> {
    @Override
    public HomeModel creatModel() {
        return new HomeModel();
    }
}
