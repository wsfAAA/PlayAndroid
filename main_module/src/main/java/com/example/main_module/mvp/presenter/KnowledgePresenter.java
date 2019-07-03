package com.example.main_module.mvp.presenter;


import com.blankj.utilcode.util.ToastUtils;
import com.example.main_module.bean.NavigationBean;
import com.example.main_module.mvp.model.KnowledgeModel;
import com.example.main_module.mvp.view.KnowledgeFragment;

import java.util.ArrayList;
import java.util.List;

import playandroid.cmcc.com.baselibrary.mvp.BasePresenter;


/**
 * Created by wsf on 2019/1/21.
 */

public class KnowledgePresenter extends BasePresenter<KnowledgeFragment, KnowledgeModel> {

    private ArrayList<NavigationBean.DataBean> mTabData = new ArrayList<>();

    @Override
    public KnowledgeModel creatModel() {
        return new KnowledgeModel();
    }

    public void requestData() {
        mBaseModel.requestData();
    }

    public void requestSucceed(NavigationBean navigationBean) {
        if (navigationBean == null) {
            return;
        }
        List<NavigationBean.DataBean> data = navigationBean.getData();
        if (data != null && data.size() > 0) {
            mTabData.clear();
            for (int i = 0; i < data.size(); i++) {
                NavigationBean.DataBean dataBean = data.get(i);
                mTabData.add(dataBean);
            }
        }
        mBaseView.getLeftMultiTypeAdapter().notifyDataSetChanged();
        mBaseView.getRightMultiTypeAdapter().notifyDataSetChanged();
        mBaseView.mLoadingView.showContent();
    }

    public void requestError() {
        ToastUtils.showShort("请求失败!");
        mBaseView.mLoadingView.showEmptyData();
    }

    public ArrayList<NavigationBean.DataBean> getTabData() {
        return mTabData;
    }

}
