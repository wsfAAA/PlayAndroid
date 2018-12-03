package playandroid.cmcc.com.searchmodule.presenter;

import playandroid.cmcc.com.baselibrary.base.jadapter.basemvp.BasePresenter;
import playandroid.cmcc.com.searchmodule.activity.SearchPageActivity;
import playandroid.cmcc.com.searchmodule.bean.SearchBean;
import playandroid.cmcc.com.searchmodule.modle.SearchPageModle;

/**
 * Created by wsf on 2018/9/29.
 */

public class SearchPagePresenter extends BasePresenter<SearchPageActivity, SearchPageModle> {

    public void searchRequest(String mes) {
        mBaseModel.searchRequest(mes);
    }


    public void searchFailure() {
        mBaseView.searchFailure();
    }

    public void searchSucceed(SearchBean searchBean) {
        mBaseView.searchSucceed(searchBean);
    }

    @Override
    public SearchPageModle creatModel() {
        return new SearchPageModle();
    }
}
