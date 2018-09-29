package playandroid.cmcc.com.searchmodule.modle;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import playandroid.cmcc.com.baselibrary.base.mvp.BaseModel;
import playandroid.cmcc.com.baselibrary.net.DataServiceManager;
import playandroid.cmcc.com.baselibrary.net.MgBaseObserver;
import playandroid.cmcc.com.baselibrary.net.service.RetrofitService;
import playandroid.cmcc.com.searchmodule.SearchApi;
import playandroid.cmcc.com.searchmodule.bean.SearchBean;
import playandroid.cmcc.com.searchmodule.bean.SearchHotKey;
import playandroid.cmcc.com.searchmodule.presenter.SearchPresenter;

/**
 * Created by wsf on 2018/9/17.
 */

public class SearchModle extends BaseModel<SearchPresenter> {

    private Disposable mDisposable;

    /**
     * 热词搜索
     */
    public void searchHotKey() {
        SearchApi serviceAPI = DataServiceManager.getServiceAPI(RetrofitService.baseUrl, SearchApi.class);
        serviceAPI.searchHotKey().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new MgBaseObserver<SearchHotKey>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(SearchHotKey searchBean) {
                if (searchBean != null && searchBean.getData() != null && searchBean.getData().size() > 0) {
                    mPresenter.searchHotKeySucceed(searchBean);
                } else {
                    mPresenter.searchHotKeyFailure();
                }
            }

            @Override
            public void onError(Throwable e) {
                mPresenter.searchHotKeyFailure();
            }
        });
    }
}
