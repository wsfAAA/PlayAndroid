package playandroid.cmcc.com.searchmodule.vu;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import me.drakeet.multitype.MultiTypeAdapter;
import playandroid.cmcc.com.baselibrary.base.mvp.BaseMvpXVu;
import playandroid.cmcc.com.searchmodule.R;
import playandroid.cmcc.com.searchmodule.R2;
import playandroid.cmcc.com.searchmodule.adapter.SearchAdapter;
import playandroid.cmcc.com.searchmodule.bean.SearchBean;
import playandroid.cmcc.com.searchmodule.presenter.SearchPagePresenter;

/**
 * Created by wsf on 2018/9/29.
 */

public class SearchPageVu extends BaseMvpXVu<SearchPagePresenter> {

    @BindView(R2.id.mrecycler)
    RecyclerView mRecycler;
    @BindView(R2.id.mSmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    private MultiTypeAdapter mSearchAdapter;
    private ArrayList<SearchBean> mSearchBean = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_page;
    }

    @Override
    public void bindView() {
        super.bindView();
        Intent intent = ((Activity) context).getIntent();
        String searchContent = intent.getStringExtra(SearchVu.INTENT_SEARCH_HOTKEY);
        mPresenter.searchRequest(searchContent);
        mRecycler= ((Activity) context).findViewById(R.id.mrecycler);
        ToastUtils.showShort(""+mRecycler);
        mSmartRefreshLayout= ((Activity) context).findViewById(R.id.mSmartRefreshLayout);

        //为何这个页面使用  butterknife 控件会空指针
        mSearchAdapter = new MultiTypeAdapter();
        SearchAdapter searchAdapter=new SearchAdapter(context);
        mSearchAdapter.register(SearchBean.class,searchAdapter);
        mSearchAdapter.setItems(mSearchBean);
        mRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
        mRecycler.setAdapter(mSearchAdapter);
    }

    public void searchFailure() {
        ToastUtils.showShort("请求失败");
    }

    public void searchSucceed(SearchBean searchBean) {
        for (int i = 0; i < searchBean.getData().getDatas().size(); i++) {
            mSearchBean.add(searchBean);
        }
        mSearchAdapter.notifyDataSetChanged();
    }
}
