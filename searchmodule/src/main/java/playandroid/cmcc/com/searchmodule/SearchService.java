package playandroid.cmcc.com.searchmodule;

import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.ccm.idataservice.search.ISearchService;

import cmcc.com.playandroid.common.CommonFinal;
import playandroid.cmcc.com.searchmodule.activity.SearchActivity;

/**
 * Created by wsf on 2019/3/22.
 */
@Route(path = CommonFinal.AROUTER_SEARCH_TEST)
public class SearchService implements ISearchService {
    @Override
    public void initSDK(Context context) {

    }

    @Override
    public void goToSearch(Context context, String message) {
        ToastUtils.showShort(message);
        context.startActivity(new Intent(context, SearchActivity.class));
    }

    @Override
    public void init(Context context) {

    }
}
