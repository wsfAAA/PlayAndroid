package com.example.main_module.mvp.presenter;


import android.support.v4.app.Fragment;

import com.example.main_module.mvp.model.NewMainModel;
import com.example.main_module.mvp.view.DiscoverFragment;
import com.example.main_module.mvp.view.HomeFragment;
import com.example.main_module.mvp.view.KnowledgeFragment;
import com.example.main_module.mvp.view.NewMainActivity;

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
