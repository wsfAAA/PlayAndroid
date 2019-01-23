package playandroid.cmcc.com.searchmodule.presenter;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import java.util.ArrayList;

import me.drakeet.multitype.MultiTypeAdapter;
import playandroid.cmcc.com.baselibrary.mvp.BasePresenter;
import playandroid.cmcc.com.baselibrary.util.WebViewRoute;
import playandroid.cmcc.com.baselibrary.webview.WebviewActivity;
import playandroid.cmcc.com.searchmodule.R;
import playandroid.cmcc.com.searchmodule.activity.SearchPageActivity;
import playandroid.cmcc.com.searchmodule.adapter.SearchAdapter;
import playandroid.cmcc.com.searchmodule.bean.SearchBean;
import playandroid.cmcc.com.searchmodule.modle.SearchPageModle;

/**
 * Created by wsf on 2018/9/29.
 */

public class SearchPagePresenter extends BasePresenter<SearchPageActivity, SearchPageModle> {

    private MultiTypeAdapter mSearchAdapter;
    private ArrayList<SearchBean.DataBean.DatasBean> mSearchBean = new ArrayList<>();

    public MultiTypeAdapter initAdapter() {
        mSearchAdapter = new MultiTypeAdapter();
        SearchAdapter searchAdapter = new SearchAdapter(mContext);
        mSearchAdapter.register(SearchBean.DataBean.DatasBean.class, searchAdapter);
        mSearchAdapter.setItems(mSearchBean);


        searchAdapter.setCollectOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("收藏");
            }
        });
        searchAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = (String) v.getTag(R.id.search_result_position_id);
                if (TextUtils.isEmpty(url)) {
                    ToastUtils.showShort("url空");
                    return;
                }

                Intent intent1 = new Intent(mContext, WebviewActivity.class);
                intent1.putExtra(WebViewRoute.WEBVIEW_URL, url);
//                intent1.putExtra(WebViewRoute.WEBVIEW_LOAD_URL, false);
                mContext.startActivity(intent1);
            }
        });
        return mSearchAdapter;
    }

    public void searchRequest(String mes, int pageCount, boolean isRefresh) {
        if (isRefresh) {
            mSearchBean.clear();
        }
        mBaseModel.searchRequest(mes, pageCount);
    }


    public void searchFailure() {
        ToastUtils.showShort("请求失败");
        if (mSearchBean.size() <= 0) {
            mBaseView.mBaseLoadView.showEmptyData();
        }
        mBaseView.mSmartRefreshLayout.finishRefresh(1000, false);
    }

    public void searchSucceed(SearchBean searchBean) {
        for (int i = 0; i < searchBean.getData().getDatas().size(); i++) {
            SearchBean.DataBean.DatasBean datasBean = searchBean.getData().getDatas().get(i);
            mSearchBean.add(datasBean);
        }
        if (searchBean.getData().isOver()) {
//            mBaseView..finishLoadMore(1000,true,true);
        } else {
            mBaseView.mBaseLoadView.showContent();
            mSearchAdapter.notifyDataSetChanged();
            mBaseView.mSmartRefreshLayout.finishRefresh(1000, true);
            mBaseView.mSmartRefreshLayout.finishLoadMore(1000, true, false);
        }
    }

    @Override
    public SearchPageModle creatModel() {
        return new SearchPageModle();
    }
}
