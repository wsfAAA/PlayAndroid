package cmcc.com.playandroid.mvp.model;


import cmcc.com.playandroid.adapter.HomeList;
import cmcc.com.playandroid.mvp.presenter.DetailsContentPresenter;
import playandroid.cmcc.com.baselibrary.api.BaseApiService;
import playandroid.cmcc.com.baselibrary.mvp.BaseModel;
import playandroid.cmcc.com.baselibrary.net.RxClient;
import playandroid.cmcc.com.baselibrary.net.callback.RxCallBack;

/**
 * Created by wsf on 2019/1/23.
 */

public class DetailsContentModel extends BaseModel<DetailsContentPresenter> {

    public void requestData(int mId, int pageCount) {
        RxClient.builder().cache(false).build().rxGet(BaseApiService.DETAILS_CONTENT + pageCount + "/json?cid=" + mId, new RxCallBack<HomeList>() {
            @Override
            public void rxOnNext(HomeList response) {
                if (response != null && response.getData() != null && response.getData().getDatas() != null) {
                    mBasePresenter.succeed(response);
                } else {
                    mBasePresenter.error();
                }
            }

            @Override
            public void rxOnError(Throwable e) {
                mBasePresenter.error();
            }
        });
    }
}
