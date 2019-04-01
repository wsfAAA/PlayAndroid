package kotlintest.com.kotlinmodule.mvp.module

import cmcc.com.playandroid.bean.CommonListBean
import kotlintest.com.kotlinmodule.BannerBean
import kotlintest.com.kotlinmodule.mvp.persenter.KotlinPersenter
import playandroid.cmcc.com.baselibrary.api.BaseApiService
import playandroid.cmcc.com.baselibrary.mvp.BaseModel
import playandroid.cmcc.com.baselibrary.net.RxClient
import playandroid.cmcc.com.baselibrary.net.callback.RxCallBack

/**
 * Created by wsf on 2019/3/29.
 */
class KotlinModle : BaseModel<KotlinPersenter>() {

    fun requestListData(mCountPage: Int) {
        RxClient.builder().cache(true).build().rxGet(BaseApiService.HOME_LIST + mCountPage + "/json", object : RxCallBack<CommonListBean>() {
            override fun rxOnNext(response: CommonListBean?) {
                if (response==null){
                    return
                }
                if (response.getErrorCode() == 0 && !response.getData().getDatas().isEmpty()) {
                    mBasePresenter.homeListSucceed(response);
                } else {
                    mBasePresenter.homeListError();
                }
            }

            override fun rxOnError(e: Throwable?) {
                mBasePresenter.homeListError();
            }
        })
    }

    fun requestBannner() {
        RxClient.builder().cache(false).build().rxGet(BaseApiService.BANNER, object : RxCallBack<BannerBean>() {
            override fun rxOnNext(response: BannerBean?) {
                if (response == null) {
                    return
                }
                if (response.getErrorCode() == 0 && !response.getData().isEmpty()) {
                    mBasePresenter.bannerSucceed(response);
                } else {
                    mBasePresenter.bannerError();
                }
            }

            override fun rxOnError(e: Throwable?) {

            }
        })
    }

}

