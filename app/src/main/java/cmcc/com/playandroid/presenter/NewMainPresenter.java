package cmcc.com.playandroid.presenter;


import android.support.v4.app.Fragment;

import cmcc.com.playandroid.activity.NewMainActivity;
import cmcc.com.playandroid.fragment.DiscoverFragment;
import cmcc.com.playandroid.fragment.HomeFragment;
import cmcc.com.playandroid.fragment.KnowledgeFragment;
import cmcc.com.playandroid.main.MainActivity;
import cmcc.com.playandroid.main.MainModel;
import cmcc.com.playandroid.model.NewMainModel;
import playandroid.cmcc.com.baselibrary.basemvp.BasePresenter;

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
