package playandroid.cmcc.com.searchmodule.mvp;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import playandroid.cmcc.com.baselibrary.base.mvp.BaseModel;
import playandroid.cmcc.com.baselibrary.net.DataServiceManager;
import playandroid.cmcc.com.baselibrary.net.MgBaseObserver;
import playandroid.cmcc.com.baselibrary.net.service.RetrofitService;
import playandroid.cmcc.com.searchmodule.SearchApi;
import playandroid.cmcc.com.searchmodule.SearchBean;

/**
 * Created by wsf on 2018/9/17.
 */

public class SearchModle extends BaseModel<SearchPresenter> {

    private Disposable mDisposable;

    public void searchRequest(String mes) {
        SearchApi serviceAPI = DataServiceManager.getServiceAPI(RetrofitService.baseUrl, SearchApi.class);
        serviceAPI.searchContent(mes).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new MgBaseObserver<SearchBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(SearchBean searchBean) {
                if (searchBean != null&&searchBean.getData()!=null&&searchBean.getData().getDatas().size()>0) {
                    mPresenter.searchSucceed(searchBean);
                }else {
                    mPresenter.searchFailure();
                }
            }

            @Override
            public void onError(Throwable e) {
                mPresenter.searchFailure();
            }
        });
    }
}
