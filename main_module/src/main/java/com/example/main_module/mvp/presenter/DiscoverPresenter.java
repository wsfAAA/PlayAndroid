package com.example.main_module.mvp.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.example.main_module.bean.DiscoverBean;
import com.example.main_module.mvp.model.DiscoverModel;
import com.example.main_module.mvp.view.DiscoverFragment;

import java.util.ArrayList;
import java.util.List;

import playandroid.cmcc.com.baselibrary.mvp.BasePresenter;

/**
 * Created by wsf on 2019/1/23.
 */

public class DiscoverPresenter extends BasePresenter<DiscoverFragment, DiscoverModel> {

    private List<DiscoverBean.DataBean> data = new ArrayList<>();

    @Override
    public DiscoverModel creatModel() {
        return new DiscoverModel();
    }

    public void requestData() {
        mBaseModel.requestData();
    }

    public void succeed(DiscoverBean response) {
        data.clear();
        data.addAll(response.getData());
        mBaseView.getMultiTypeAdapter().notifyDataSetChanged();
        mBaseView.mLoadingView.showContent();
    }

    public void error() {
        ToastUtils.showShort("请求失败!");
        mBaseView.mLoadingView.showEmptyData();
    }

    public List<DiscoverBean.DataBean> getData() {
        return data;
    }
}
