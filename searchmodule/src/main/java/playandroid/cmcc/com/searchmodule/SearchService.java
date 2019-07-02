package playandroid.cmcc.com.searchmodule;

import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.ccm.idataservice.TestCallBack;
import com.ccm.idataservice.search.ISearchService;

import cmcc.com.playandroid.common.CommonFinal;
import playandroid.cmcc.com.searchmodule.activity.ATestActivity;
import playandroid.cmcc.com.searchmodule.activity.SearchActivity;

/**
 * Created by wsf on 2019/3/22.  组件对外暴露的接口
 */
@Route(path = CommonFinal.AROUTER_SEARCH_TEST)
public class SearchService implements ISearchService {

    private static SearchService searchService;

    //-------------------------------------------------------------------------------------------
    public SearchService() {
        searchService = this;
    }

    public static SearchService getInstance() {
        if (searchService == null) {
            synchronized (SearchService.class) {
                if (searchService == null) {
                    searchService = new SearchService();
                }
            }
        }
        return searchService;
    }
    //-------------------------------------------------------------------------------------------

    @Override
    public void initSDK(Context context) {

    }

    @Override
    public void goToSearch(Context context, String message) {
        ToastUtils.showShort(message);
        context.startActivity(new Intent(context, ATestActivity.class));
    }

    TestCallBack testCallBack;

    @Override
    public void testCallBack(TestCallBack testCallBack) {
        this.testCallBack = testCallBack;
    }

    public void setTestString(String mes){
        if (testCallBack!=null){
            testCallBack.testString(mes);
        }
    }

    @Override
    public void init(Context context) {

    }
}
