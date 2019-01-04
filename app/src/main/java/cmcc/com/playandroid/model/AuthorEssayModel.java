package cmcc.com.playandroid.model;

import cmcc.com.playandroid.presenter.AuthorEssayPresenter;
import playandroid.cmcc.com.baselibrary.mvp.BaseModel;
import playandroid.cmcc.com.baselibrary.net.RxClient;
import playandroid.cmcc.com.baselibrary.net.callback.RxCallBack;

/**
 * Created by wsf on 2019/1/4.
 */

public class AuthorEssayModel extends BaseModel<AuthorEssayPresenter> {

    public void requestData(String mUrl) {
        RxClient.builder().cache(false).build().rxGet(mUrl, new RxCallBack<String>() {
            @Override
            public void rxOnNext(String response) {
                
            }

            @Override
            public void rxOnError(Throwable e) {

            }
        });
    }
}
