package com.playandroid.newbase;

import com.playandroid.newbase.mvp.BasePresenter;

public class TestPresenterTow extends BasePresenter<TestActivity, TestModelTow> {

    public String getTest(){
        return getModel().getTest();
    }
}
