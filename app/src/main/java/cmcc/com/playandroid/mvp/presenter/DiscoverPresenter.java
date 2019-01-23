package cmcc.com.playandroid.mvp.presenter;

import com.blankj.utilcode.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import cmcc.com.playandroid.bean.DiscoverBean;
import cmcc.com.playandroid.mvp.model.DiscoverModel;
import cmcc.com.playandroid.mvp.view.DiscoverFragment;
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
