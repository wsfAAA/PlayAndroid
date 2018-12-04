package playandroid.cmcc.com.searchmodule.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import me.drakeet.multitype.MultiTypeAdapter;
import playandroid.cmcc.com.baselibrary.basemvp.BaseMvpActivity;
import playandroid.cmcc.com.searchmodule.R;
import playandroid.cmcc.com.searchmodule.R2;
import playandroid.cmcc.com.searchmodule.adapter.SearchAdapter;
import playandroid.cmcc.com.searchmodule.bean.SearchBean;
import playandroid.cmcc.com.searchmodule.presenter.SearchPagePresenter;

public class SearchPageActivity extends BaseMvpActivity<SearchPagePresenter> {

    @BindView(R2.id.mrecycler)
    RecyclerView mRecycler;
    @BindView(R2.id.mSmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    private MultiTypeAdapter mSearchAdapter;
    private ArrayList<SearchBean> mSearchBean = new ArrayList<>();

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_search_page;
    }

    @Override
    protected void initMvpView() {
        Intent intent = getIntent();
        String searchContent = intent.getStringExtra(SearchActivity.INTENT_SEARCH_HOTKEY);
        mBasePresenter.searchRequest(searchContent);

        mSearchAdapter = new MultiTypeAdapter();
        SearchAdapter searchAdapter = new SearchAdapter(this);
        mSearchAdapter.register(SearchBean.class, searchAdapter);
        mSearchAdapter.setItems(mSearchBean);
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecycler.setAdapter(mSearchAdapter);

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
                Intent intent1 = new Intent(mContext, SearchWebViewActivity.class);
                intent1.putExtra(SearchWebViewActivity.SEARCH_HTTP_URL, url);
                startActivity(intent1);
            }
        });
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

    @Override
    public SearchPagePresenter creatPersenter() {
        return new SearchPagePresenter();
    }
}
