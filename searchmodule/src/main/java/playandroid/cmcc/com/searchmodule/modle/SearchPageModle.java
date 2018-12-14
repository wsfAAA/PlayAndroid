package playandroid.cmcc.com.searchmodule.modle;

import playandroid.cmcc.com.baselibrary.api.BaseApiService;
import playandroid.cmcc.com.baselibrary.basemvp.BaseModel;
import playandroid.cmcc.com.baselibrary.net.RxClient;
import playandroid.cmcc.com.baselibrary.net.callback.RxCallBack;
import playandroid.cmcc.com.searchmodule.bean.SearchBean;
import playandroid.cmcc.com.searchmodule.presenter.SearchPagePresenter;

/**
 * Created by wsf on 2018/9/29.
 */

public class SearchPageModle extends BaseModel<SearchPagePresenter> {


    public void searchRequest(String mes) {
        RxClient.builder()
                .addParams("k", mes)
                .build()
                .rxPost(BaseApiService.SEARCH, new RxCallBack<SearchBean>() {
                    @Override
                    public void rxOnNext(SearchBean response) {
                        if (response != null && response.getData() != null && response.getData().getDatas().size() > 0) {
                            mBasePresenter.searchSucceed(response);
                        } else {
                            mBasePresenter.searchFailure();
                        }
                    }

                    @Override
                    public void rxOnError(Throwable e) {
                        mBasePresenter.searchFailure();
                    }
                });
    }


}
