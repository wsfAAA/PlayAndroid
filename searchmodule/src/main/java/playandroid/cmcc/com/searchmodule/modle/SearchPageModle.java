package playandroid.cmcc.com.searchmodule.modle;

import playandroid.cmcc.com.baselibrary.basemvp.BaseModel;
import playandroid.cmcc.com.searchmodule.presenter.SearchPagePresenter;

/**
 * Created by wsf on 2018/9/29.
 */

public class SearchPageModle extends BaseModel<SearchPagePresenter> {



    public void searchRequest(String mes) {
//        SearchApi serviceAPI = DataServiceManager.getServiceAPI(RetrofitService.baseUrl, SearchApi.class);
//        serviceAPI.searchContent(mes).subscribeOn(Schedulers.io()).
//                observeOn(AndroidSchedulers.mainThread()).subscribe(new MgBaseObserver<SearchBean>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                mDisposable = d;
//            }
//
//            @Override
//            public void onNext(SearchBean searchBean) {
//                if (searchBean != null && searchBean.getData() != null && searchBean.getData().getDatas().size() > 0) {
//                    mBasePresenter.searchSucceed(searchBean);
//                } else {
//                    mBasePresenter.searchFailure();
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                mBasePresenter.searchFailure();
//            }
//        });
    }


}
