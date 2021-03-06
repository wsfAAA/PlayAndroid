package com.example.main_module.mvp.model;

import com.example.main_module.bean.NavigationBean;
import com.example.main_module.mvp.presenter.KnowledgePresenter;

import playandroid.cmcc.com.baselibrary.api.BaseApiService;
import playandroid.cmcc.com.baselibrary.mvp.BaseModel;
import playandroid.cmcc.com.baselibrary.net.RxClient;
import playandroid.cmcc.com.baselibrary.net.callback.RxCallBack;

/**
 * Created by wsf on 2019/1/21.
 */

public class KnowledgeModel extends BaseModel<KnowledgePresenter> {

    public void requestData() {
        RxClient.builder().cache(true).build().rxGet(BaseApiService.NAVIGATION, new RxCallBack<NavigationBean>() {
            @Override
            public void rxOnNext(NavigationBean response) {
                if (response != null && response.getData() != null) {
                    mBasePresenter.requestSucceed(response);
                }
            }

            @Override
            public void rxOnError(Throwable e) {
                mBasePresenter.requestError();
            }
        });
    }
}
