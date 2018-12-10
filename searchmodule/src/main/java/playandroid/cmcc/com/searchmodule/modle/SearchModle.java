package playandroid.cmcc.com.searchmodule.modle;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import playandroid.cmcc.com.baselibrary.basemvp.BaseModel;
import playandroid.cmcc.com.baselibrary.net.RxCreator;
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


        RxCreator.getRestService().getGetData("hotkey/json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("wsf", "onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("wsf", "onNext： " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("wsf", "onError： " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.i("wsf", "onComplete");
                    }
                });
    }
}
