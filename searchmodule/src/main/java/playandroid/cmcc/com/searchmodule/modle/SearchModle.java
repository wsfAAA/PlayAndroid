package playandroid.cmcc.com.searchmodule.modle;

import playandroid.cmcc.com.baselibrary.api.BaseApiService;
import playandroid.cmcc.com.baselibrary.mvp.BaseModel;
import playandroid.cmcc.com.baselibrary.net.RxClient;
import playandroid.cmcc.com.baselibrary.net.callback.RxCallBack;
import playandroid.cmcc.com.searchmodule.bean.SearchHotKey;
import playandroid.cmcc.com.searchmodule.presenter.SearchPresenter;

/**
 * Created by wsf on 2018/9/17.
 */

public class SearchModle extends BaseModel<SearchPresenter> {


    /**
     * 热词搜索
     */
    public void searchHotKey() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(RxCreator.BASE_URL)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//        retrofit.create(RxService.class)
//                .getGetData("hotkey/json")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.i("wsf", "onSubscribe");
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        Log.i("wsf", "onNext： " + s);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i("wsf", "onError： " + e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.i("wsf", "onComplete");
//                    }
//                });

//        RxService rxService = RxCreator.getRetrofitBuilde().build().create(RxService.class);
//        rxService.get("hotkey/json")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        Log.i("wsf", "onNext:  " + s);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i("wsf", "Throwable:  " + e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        RxClient.builder()
                .build()
                .rxGet(BaseApiService.SEACH_HOTKEY, new RxCallBack<SearchHotKey>() {
                    @Override
                    public void rxOnError(Throwable e) {
                        mBasePresenter.searchHotKeyFailure();
                    }

                    @Override
                    public void rxOnNext(SearchHotKey response) {
                        if (response != null && response.getData() != null && response.getData().size() > 0) {
                            mBasePresenter.searchHotKeySucceed(response);
                        } else {
                            mBasePresenter.searchHotKeyFailure();
                        }
                    }
                });
    }
}
