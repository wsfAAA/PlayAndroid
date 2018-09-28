package playandroid.cmcc.com.searchmodule.mvp;

import playandroid.cmcc.com.baselibrary.base.mvp.BasePresenterX;
import playandroid.cmcc.com.searchmodule.SearchBean;
import playandroid.cmcc.com.searchmodule.SearchHotKey;

/**
 * Created by wsf on 2018/9/17.
 */

public class SearchPresenter extends BasePresenterX<SearchVu,SearchModle>{

    public void searchRequest(String mes) {
        baseModel.searchRequest(mes);
    }

    public void searchFailure() {
        baseView.searchFailure();
    }

    public void searchSucceed(SearchBean searchBean) {
        baseView.searchSucceed(searchBean);
    }

    public void searchHotKeyRequest() {
        baseModel.searchHotKey();
    }

    public void searchHotKeySucceed(SearchHotKey searchBean) {
        baseView.searchHotKeySucceed(searchBean);
    }

    public void searchHotKeyFailure() {
        baseView.searchHotKeyFailure();
    }
}
