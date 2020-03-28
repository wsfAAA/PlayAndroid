package com.playandroid.newbase.mvp02.test;

import com.playandroid.newbase.mvp02.BasePresenter;
import com.playandroid.newbase.mvp02.InjectModel;

public class Test02Presenter extends BasePresenter<Test02Callback> {

    @InjectModel
    Test02Model test02Model;

    public void getTest() {
        getView().setTest(test02Model.getTest());
    }
}
