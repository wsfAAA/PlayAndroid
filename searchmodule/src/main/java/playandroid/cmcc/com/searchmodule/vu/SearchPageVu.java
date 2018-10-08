package playandroid.cmcc.com.searchmodule.vu;

import android.app.Activity;
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
import playandroid.cmcc.com.baselibrary.base.bk.MgMvpXVu;
import playandroid.cmcc.com.searchmodule.R;
import playandroid.cmcc.com.searchmodule.R2;
import playandroid.cmcc.com.searchmodule.activity.SearchWebViewActivity;
import playandroid.cmcc.com.searchmodule.adapter.SearchAdapter;
import playandroid.cmcc.com.searchmodule.bean.SearchBean;
import playandroid.cmcc.com.searchmodule.presenter.SearchPagePresenter;

/**
 * Created by wsf on 2018/9/29.
 */

public class SearchPageVu extends MgMvpXVu<SearchPagePresenter> {

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
        final Intent intent = ((Activity) context).getIntent();
        String searchContent = intent.getStringExtra(SearchVu.INTENT_SEARCH_HOTKEY);
        mPresenter.searchRequest(searchContent);

        mSearchAdapter = new MultiTypeAdapter();
        SearchAdapter searchAdapter=new SearchAdapter(context);
        mSearchAdapter.register(SearchBean.class,searchAdapter);
        mSearchAdapter.setItems(mSearchBean);
        mRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
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
                String url= (String) v.getTag(R.id.search_result_position_id);
                if (TextUtils.isEmpty(url)){
                    ToastUtils.showShort("url空");
                    return;
                }
                Intent intent1=new Intent(context,SearchWebViewActivity.class);
                intent1.putExtra(SearchWebViewActivity.SEARCH_HTTP_URL,url);
                context.startActivity(intent1);
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
}
