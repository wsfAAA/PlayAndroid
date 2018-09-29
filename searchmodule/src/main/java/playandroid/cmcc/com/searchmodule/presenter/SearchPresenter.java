package playandroid.cmcc.com.searchmodule.presenter;

import playandroid.cmcc.com.baselibrary.base.mvp.BasePresenterX;
import playandroid.cmcc.com.searchmodule.bean.SearchBean;
import playandroid.cmcc.com.searchmodule.bean.SearchHotKey;
import playandroid.cmcc.com.searchmodule.modle.SearchModle;
import playandroid.cmcc.com.searchmodule.vu.SearchVu;

/**
 * Created by wsf on 2018/9/17.
 */

public class SearchPresenter extends BasePresenterX<SearchVu,SearchModle>{

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
