package cmcc.com.playandroid.mvp.presenter;


import android.support.v4.app.Fragment;

import cmcc.com.playandroid.mvp.view.NewMainActivity;
import cmcc.com.playandroid.mvp.view.DiscoverFragment;
import cmcc.com.playandroid.mvp.view.HomeFragment;
import cmcc.com.playandroid.mvp.view.KnowledgeFragment;
import cmcc.com.playandroid.mvp.model.NewMainModel;
import playandroid.cmcc.com.baselibrary.mvp.BasePresenter;

/**
 * Created by wsf on 2018/9/5.
 */

public class NewMainPresenter extends BasePresenter<NewMainActivity, NewMainModel> {

    @Override
    public NewMainModel creatModel() {
        return new NewMainModel();
    }

    public Fragment[] initFragment() {
        HomeFragment mHomeFragment = new HomeFragment();
        KnowledgeFragment mKnowledgeFragment = new KnowledgeFragment();
        DiscoverFragment mDiscoverFragment = new DiscoverFragment();
        return new Fragment[]{mDiscoverFragment, mHomeFragment, mKnowledgeFragment};
    }

}
