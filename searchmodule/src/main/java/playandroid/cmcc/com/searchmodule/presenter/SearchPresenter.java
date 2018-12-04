package playandroid.cmcc.com.searchmodule.presenter;

import playandroid.cmcc.com.baselibrary.basemvp.BasePresenter;
import playandroid.cmcc.com.searchmodule.activity.SearchActivity;
import playandroid.cmcc.com.searchmodule.bean.SearchHotKey;
import playandroid.cmcc.com.searchmodule.modle.SearchModle;

/**
 * Created by wsf on 2018/9/17.
 */

public class SearchPresenter extends BasePresenter<SearchActivity, SearchModle> {

    public void searchHotKeyRequest() {
        mBaseModel.searchHotKey();
    }

    public void searchHotKeySucceed(SearchHotKey searchBean) {
        mBaseView.searchHotKeySucceed(searchBean);
    }

    public void searchHotKeyFailure() {
        mBaseView.searchHotKeyFailure();
    }

    @Override
    public SearchModle creatModel() {
        return new SearchModle();
    }
}
