package cmcc.com.playandroid.mvp.model;

import cmcc.com.playandroid.bean.NavigationBean;
import cmcc.com.playandroid.mvp.presenter.KnowledgePresenter;
import playandroid.cmcc.com.baselibrary.api.BaseApiService;
import playandroid.cmcc.com.baselibrary.mvp.BaseModel;
import playandroid.cmcc.com.baselibrary.net.RxClient;
import playandroid.cmcc.com.baselibrary.net.callback.RxCallBack;

/**
 * Created by wsf on 2019/1/21.
 */

public class KnowledgeModel extends BaseModel<KnowledgePresenter> {

    public void requestData() {
        RxClient.builder().cache(true).build().rxGet(BaseApiService.NAVIGATION, new RxCallBack<NavigationBean>() {
            @Override
            public void rxOnNext(NavigationBean response) {
                mBasePresenter.requestSucceed(response);
            }

            @Override
            public void rxOnError(Throwable e) {
                mBasePresenter.requestError();
            }
        });
    }
}
