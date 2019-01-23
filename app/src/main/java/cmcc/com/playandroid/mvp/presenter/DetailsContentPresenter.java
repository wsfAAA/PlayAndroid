package cmcc.com.playandroid.mvp.presenter;

import com.blankj.utilcode.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import cmcc.com.playandroid.adapter.HomeList;
import cmcc.com.playandroid.mvp.model.DetailsContentModel;
import cmcc.com.playandroid.mvp.view.DetailsContentActivity;
import playandroid.cmcc.com.baselibrary.mvp.BasePresenter;

/**
 * Created by wsf on 2019/1/23.
 */

public class DetailsContentPresenter extends BasePresenter<DetailsContentActivity, DetailsContentModel> {

    private List<HomeList.DataBean.DatasBean> datasBeans = new ArrayList<>();
    private boolean isRefresh;

    @Override
    public DetailsContentModel creatModel() {
        return new DetailsContentModel();
    }

    public void requestData(int mId, int pageCount, boolean isRefresh) {
        this.isRefresh = isRefresh;
        mBaseModel.requestData(mId, pageCount);
    }

    public void error() {
        ToastUtils.showShort("请求失败!");
        if (datasBeans.size() <= 0) {
            mBaseView.mBaseLoadView.showEmptyData();
        }
    }

    public void succeed(HomeList response) {
        if (isRefresh) {
            datasBeans.clear();
            mBaseView.mSmartRefreshLayout.finishRefresh();
        } else {
            if (response.getData().isOver()) {
                mBaseView.mSmartRefreshLayout.finishLoadMoreWithNoMoreData();
            } else {
                mBaseView.mSmartRefreshLayout.finishLoadMore();
            }
        }

        datasBeans.addAll(response.getData().getDatas());
        mBaseView.getMultiTypeAdapter().notifyDataSetChanged();
        mBaseView.mBaseLoadView.showContent();
    }

    public List<HomeList.DataBean.DatasBean> getData() {
        return datasBeans;
    }
}
