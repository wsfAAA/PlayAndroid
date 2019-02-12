package cmcc.com.playandroid.activity;

import com.blankj.utilcode.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import cmcc.com.playandroid.bean.CommonListBean;
import playandroid.cmcc.com.baselibrary.mvp.BasePresenter;

/**
 * Created by wsf on 2019/1/23.
 */

public class DetailsContentPresenter extends BasePresenter<DetailsContentActivity, DetailsContentModel> {

    private List<CommonListBean.DataBean.DatasBean> datasBeans = new ArrayList<>();
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
        if (isRefresh) {
            mBaseView.mSmartRefreshLayout.finishRefresh(false);
        } else {
            mBaseView.mSmartRefreshLayout.finishLoadMore(false);
        }
    }

    public void succeed(CommonListBean response) {
        if (isRefresh) {
            datasBeans.clear();
            mBaseView.mSmartRefreshLayout.finishRefresh(true);
        } else {
            if (response.getData().isOver()) {
                mBaseView.mSmartRefreshLayout.finishLoadMoreWithNoMoreData();
            } else {
                mBaseView.mSmartRefreshLayout.finishLoadMore(true);
            }
        }

        datasBeans.addAll(response.getData().getDatas());
        mBaseView.getMultiTypeAdapter().notifyDataSetChanged();
        mBaseView.mBaseLoadView.showContent();
    }

    public List<CommonListBean.DataBean.DatasBean> getData() {
        return datasBeans;
    }


    public void searchRequest(String mTitle, int pageCount, boolean isRefresh) {
        this.isRefresh = isRefresh;
        mBaseModel.searchRequest(mTitle, pageCount);
    }

    public void requestCollect(int pageCount, boolean isRefresh) {
        this.isRefresh = isRefresh;
        mBaseModel.requestCollect(pageCount);
    }
}
