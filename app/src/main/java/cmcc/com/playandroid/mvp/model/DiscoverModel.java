package cmcc.com.playandroid.mvp.model;

import cmcc.com.playandroid.bean.DiscoverBean;
import cmcc.com.playandroid.mvp.presenter.DiscoverPresenter;
import playandroid.cmcc.com.baselibrary.api.BaseApiService;
import playandroid.cmcc.com.baselibrary.mvp.BaseModel;
import playandroid.cmcc.com.baselibrary.net.RxClient;
import playandroid.cmcc.com.baselibrary.net.callback.RxCallBack;

/**
 * Created by wsf on 2019/1/23.
 */

public class DiscoverModel extends BaseModel<DiscoverPresenter> {

    public void requestData() {
        RxClient.builder().cache(true).build().rxGet(BaseApiService.DISCOVER, new RxCallBack<DiscoverBean>() {
            @Override
            public void rxOnNext(DiscoverBean response) {
                if (response != null && response.getData() != null) {
                    mBasePresenter.succeed(response);
                }
            }

            @Override
            public void rxOnError(Throwable e) {
                mBasePresenter.error();
            }
        });
    }
}
