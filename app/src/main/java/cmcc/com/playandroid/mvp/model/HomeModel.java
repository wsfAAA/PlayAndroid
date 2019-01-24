package cmcc.com.playandroid.mvp.model;

import cmcc.com.playandroid.bean.CommonListBean;
import cmcc.com.playandroid.bean.BannerBean;
import cmcc.com.playandroid.mvp.presenter.HomePresenter;
import playandroid.cmcc.com.baselibrary.api.BaseApiService;
import playandroid.cmcc.com.baselibrary.mvp.BaseModel;
import playandroid.cmcc.com.baselibrary.net.RxClient;
import playandroid.cmcc.com.baselibrary.net.callback.RxCallBack;

/**
 * Created by wsf on 2019/1/2.
 */

public class HomeModel extends BaseModel<HomePresenter> {

    public void requestBanner() {
        RxClient.builder().cache(false).build().rxGet(BaseApiService.BANNER, new RxCallBack<BannerBean>() {
            @Override
            public void rxOnNext(BannerBean response) {
                if (response == null) {
                    return;
                }
                if (response.getErrorCode() == 0 && !response.getData().isEmpty()) {
                    mBasePresenter.bannerSucceed(response);
                } else {
                    mBasePresenter.bannerError();
                }
            }

            @Override
            public void rxOnError(Throwable e) {
                mBasePresenter.bannerError();
            }
        });
    }

    public void requestHomeList(int pageCount) {
        RxClient.builder().cache(false).build().rxGet(BaseApiService.HOME_LIST + pageCount + "/json", new RxCallBack<CommonListBean>() {
            @Override
            public void rxOnNext(CommonListBean response) {
                if (response.getErrorCode() == 0 && !response.getData().getDatas().isEmpty()) {
                    mBasePresenter.homeListSucceed(response);
                } else {
                    mBasePresenter.homeListError();
                }
            }

            @Override
            public void rxOnError(Throwable e) {
                mBasePresenter.homeListError();
            }
        });
    }

}
