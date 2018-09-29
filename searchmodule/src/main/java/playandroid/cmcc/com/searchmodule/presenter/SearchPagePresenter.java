package playandroid.cmcc.com.searchmodule.presenter;

import playandroid.cmcc.com.baselibrary.base.mvp.BasePresenterX;
import playandroid.cmcc.com.searchmodule.bean.SearchBean;
import playandroid.cmcc.com.searchmodule.modle.SearchPageModle;
import playandroid.cmcc.com.searchmodule.vu.SearchPageVu;

/**
 * Created by wsf on 2018/9/29.
 */

public class SearchPagePresenter extends BasePresenterX<SearchPageVu,SearchPageModle>{

    public void searchRequest(String mes) {
        baseModel.searchRequest(mes);
    }


    public void searchFailure() {
        baseView.searchFailure();
    }

    public void searchSucceed(SearchBean searchBean) {
        baseView.searchSucceed(searchBean);
    }
}
