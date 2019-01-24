package cmcc.com.playandroid.mvp.presenter;

import com.blankj.utilcode.util.ToastUtils;

import cmcc.com.playandroid.adapter.BannerViewBinder;
import cmcc.com.playandroid.adapter.CommonListViewBinder;
import cmcc.com.playandroid.bean.CommonListBean;
import cmcc.com.playandroid.bean.BannerBean;
import cmcc.com.playandroid.mvp.view.HomeFragment;
import cmcc.com.playandroid.mvp.model.HomeModel;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import playandroid.cmcc.com.baselibrary.mvp.BasePresenter;

/**
 * Created by wsf on 2019/1/2.
 */

public class HomePresenter extends BasePresenter<HomeFragment, HomeModel> {

    private MultiTypeAdapter mMultiTypeAdapter;
    private Items mItems;
    private boolean isRefresh; //是否是刷新
    private BannerBean bannerBean;

    @Override
    public HomeModel creatModel() {
        return new HomeModel();
    }

    public void requestData(int pageCount, boolean isRefresh) {
        this.isRefresh = isRefresh;
        mBaseModel.requestHomeList(pageCount);
    }

    public void requestBanner() {
        mBaseModel.requestBanner();
    }

    public MultiTypeAdapter initAdapter() {
        mMultiTypeAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mMultiTypeAdapter.register(BannerBean.class, new BannerViewBinder(mContext));  //banner
        mMultiTypeAdapter.register(CommonListBean.DataBean.DatasBean.class, new CommonListViewBinder(mContext)); //首页列表
        mMultiTypeAdapter.setItems(mItems);
        return mMultiTypeAdapter;
    }

    public void bannerError() {
        ToastUtils.showShort("banner请求失败！");
    }

    public void bannerSucceed(BannerBean bannerBean) {
        this.bannerBean = bannerBean;
    }

    public void homeListSucceed(CommonListBean response) {
        if (isRefresh) {
            mItems.clear();
            if (bannerBean != null) {
                mItems.add(0, bannerBean);
            }
            mBaseView.getSmartRefresh().finishRefresh();
        } else {
            if (response.getData().isOver()) {
                mBaseView.getSmartRefresh().finishLoadMoreWithNoMoreData();
            } else {
                mBaseView.getSmartRefresh().finishLoadMore();
            }
        }
        for (int i = 0; i < response.getData().getDatas().size(); i++) {
            CommonListBean.DataBean.DatasBean datasBean = response.getData().getDatas().get(i);
            mItems.add(datasBean);
        }
        mMultiTypeAdapter.notifyDataSetChanged();
        mBaseView.mLoadingView.showContent();
    }

    public void homeListError() {
        ToastUtils.showShort("列表请求失败！");
        if (isRefresh) {
            mBaseView.getSmartRefresh().finishRefresh(1000,false);
        } else {
            mBaseView.getSmartRefresh().finishLoadMore(1000,false,false);
        }
        if (mItems != null && mItems.size() <= 0) {
            mBaseView.mLoadingView.showEmptyData();
        }
    }
}
