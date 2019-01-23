package cmcc.com.playandroid.presenter;

import com.blankj.utilcode.util.ToastUtils;

import cmcc.com.playandroid.adapter.BannerViewBinder;
import cmcc.com.playandroid.adapter.HomeList;
import cmcc.com.playandroid.adapter.HomeListViewBinder;
import cmcc.com.playandroid.bean.BannerBean;
import cmcc.com.playandroid.fragment.HomeFragment;
import cmcc.com.playandroid.model.HomeModel;
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
        mMultiTypeAdapter.register(HomeList.DataBean.DatasBean.class, new HomeListViewBinder(mContext)); //首页列表
        mMultiTypeAdapter.setItems(mItems);
        return mMultiTypeAdapter;
    }

    public void bannerError() {
        ToastUtils.showShort("banner请求失败！");
    }

    public void bannerSucceed(BannerBean bannerBean) {
        this.bannerBean = bannerBean;
    }

    public void homeListSucceed(HomeList response) {
        if (isRefresh) {
            mItems.clear();
            if (bannerBean != null) {
                mItems.add(0, bannerBean);
            }
        }
        for (int i = 0; i < response.getData().getDatas().size(); i++) {
            HomeList.DataBean.DatasBean datasBean = response.getData().getDatas().get(i);
            mItems.add(datasBean);
        }
        mMultiTypeAdapter.notifyDataSetChanged();
        mBaseView.getSmartRefresh().finishLoadMore(true);
        mBaseView.getSmartRefresh().finishRefresh(true);
        mBaseView.mLoadingView.showContent();
    }

    public void homeListError() {
        ToastUtils.showShort("列表请求失败！");
        mBaseView.getSmartRefresh().finishLoadMore(true);
        mBaseView.getSmartRefresh().finishRefresh(true);
        if (mItems != null && mItems.size() <= 0) {
            mBaseView.mLoadingView.showEmptyData();
        }
    }
}
