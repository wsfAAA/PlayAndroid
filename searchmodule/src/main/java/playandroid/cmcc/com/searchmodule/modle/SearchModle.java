package playandroid.cmcc.com.searchmodule.modle;

import android.util.Log;

import com.google.gson.Gson;
import com.tamic.novate.Throwable;
import com.tamic.novate.callback.RxResultCallback;
import com.tamic.novate.callback.RxStringCallback;

import java.util.HashMap;
import java.util.Map;

import playandroid.cmcc.com.baselibrary.basemvp.BaseModel;
import playandroid.cmcc.com.baselibrary.net.BaseApi;
import playandroid.cmcc.com.baselibrary.net.HttpClient;
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
        HttpClient.getInstance().rxGet(BaseApi.SEARCH_HOT_KEY, new RxCallBack<SearchHotKey>() {

            @Override
            public void onRxSuccess(Object tag, SearchHotKey response) {
                if (response != null && response.getData() != null && response.getData().size() > 0) {
                    mBasePresenter.searchHotKeySucceed(response);
                } else {
                    mBasePresenter.searchHotKeyFailure();
                }
            }

            @Override
            public void onRxError(Object tag, Throwable e) {
                mBasePresenter.searchHotKeyFailure();
            }
        });
    }
}
