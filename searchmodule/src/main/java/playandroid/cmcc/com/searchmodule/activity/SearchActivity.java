package playandroid.cmcc.com.searchmodule.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import cmcc.com.playandroid.activity.DetailsContentActivity;
import cmcc.com.playandroid.common.CommonFinal;
import me.drakeet.multitype.MultiTypeAdapter;
import playandroid.cmcc.com.baselibrary.mvp.BaseMvpActivity;
import playandroid.cmcc.com.searchmodule.R;
import playandroid.cmcc.com.searchmodule.R2;
import playandroid.cmcc.com.searchmodule.adapter.SearchHistoryAdapter;
import playandroid.cmcc.com.searchmodule.adapter.SearchItemBinder;
import playandroid.cmcc.com.searchmodule.bean.SearchHotKey;
import playandroid.cmcc.com.searchmodule.presenter.SearchPresenter;

@Route(path = "/home/search")
public class SearchActivity extends BaseMvpActivity<SearchPresenter> {

    @BindView(R2.id.img_search_back)
    ImageView imgSearchBack;
    @BindView(R2.id.et_seach_content)
    AutoCompleteTextView etSeachContent;
    @BindView(R2.id.img_search)
    ImageView imgSearch;
    @BindView(R2.id.recycler_hot)
    RecyclerView mRecyclerHot;
    @BindView(R2.id.tv_search_clean)
    TextView tvSearchClean;
    @BindView(R2.id.recycler_history)
    RecyclerView mRecyclerHistory;
    @BindView(R2.id.tv_no_history)
    TextView tvNoHistory;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private MultiTypeAdapter multiTypeAdapterHot;
    private LinearLayoutManager linearLayoutManager;

    private ArrayList<SearchHotKey> mHotKeyData = new ArrayList<>();
    private Set<String> mSearchHistory = new HashSet<>();//历史记录
    private ArrayList<String> mSearchHistoryList = new ArrayList<>();//历史记录

    public final static String SEARCH_HOTKEY = "SEARCH_HOTKEY";
    private SearchHistoryAdapter searchHistoryAdapter;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        searchHotKey();
        searchHistory();
    }

    /**
     * 历史记录
     */
    private void searchHistory() {
        searchHistoryAdapter = new SearchHistoryAdapter(this, mSearchHistoryList, mRootListeren, mCleanListener);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerHistory.setLayoutManager(linearLayoutManager);
        mRecyclerHistory.setAdapter(searchHistoryAdapter);

        Set<String> stringSet = SPUtils.getInstance().getStringSet(SEARCH_HOTKEY);
        if (stringSet != null && stringSet.size() > 0) {
            mSearchHistory.clear();
            mSearchHistory.addAll(stringSet);
            for (String hotkey : stringSet) {
                mSearchHistoryList.add(hotkey);
            }
            VisibleView(View.GONE);
            searchHistoryAdapter.notifyDataSetChanged();
        } else {
            VisibleView(View.VISIBLE);
        }
    }

    private void VisibleView(int view) {
        if (view == View.VISIBLE) {
            tvNoHistory.setVisibility(View.VISIBLE);
            mRecyclerHistory.setVisibility(View.GONE);
            tvSearchClean.setVisibility(View.GONE);
        } else {
            tvNoHistory.setVisibility(View.GONE);
            tvSearchClean.setVisibility(View.VISIBLE);
            mRecyclerHistory.setVisibility(View.VISIBLE);
        }
    }

    View.OnClickListener mRootListeren = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String historyKey = (String) v.getTag(R.id.tv_search_history_id);
            if (!TextUtils.isEmpty(historyKey)) {
                startActivity(historyKey);
            }
        }
    };

    View.OnClickListener mCleanListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag(R.id.tv_search_history_position_id);
            String s = mSearchHistoryList.get(position);
            mSearchHistoryList.remove(position);
            searchHistoryAdapter.setData(mSearchHistoryList);
            mSearchHistory.remove(s);
            SPUtils.getInstance().put(SEARCH_HOTKEY, mSearchHistory);
            int itemCount = searchHistoryAdapter.getItemCount();
            if (itemCount <= 0) {
                VisibleView(View.VISIBLE);
            }
        }
    };

    /**
     * 热词相关
     */
    private void searchHotKey() {
        mBasePresenter.searchHotKeyRequest();
        multiTypeAdapterHot = new MultiTypeAdapter();
        SearchItemBinder sraechItemBinder = new SearchItemBinder();
        sraechItemBinder.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchHotKey.DataBean hotKeyBean = (SearchHotKey.DataBean) v.getTag(R.id.tv_search_hotkey);
                if (hotKeyBean != null) {
                    saveSearchHistory(hotKeyBean.getName());
                    startActivity(hotKeyBean.getName());
                }
            }
        });
        multiTypeAdapterHot.register(SearchHotKey.class, sraechItemBinder);
        multiTypeAdapterHot.setItems(mHotKeyData);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);
        mRecyclerHot.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerHot.setAdapter(multiTypeAdapterHot);
    }

    private void saveSearchHistory(String hotKey) {
        mSearchHistory.add(hotKey);
        SPUtils.getInstance().put(SEARCH_HOTKEY, mSearchHistory);
    }

    @OnClick({R2.id.img_search_back, R2.id.et_seach_content, R2.id.img_search, R2.id.tv_search_clean})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.img_search_back) {
            finish();
        } else if (view.getId() == R.id.img_search) {
            String searchContent = etSeachContent.getText().toString();
            if (TextUtils.isEmpty(searchContent)) {
                ToastUtils.showShort("请输入搜索内容");
            } else {
                saveSearchHistory(searchContent);
                startActivity(searchContent);
            }
        } else if (view.getId() == R.id.tv_search_clean) {//清空
            VisibleView(View.VISIBLE);
            mSearchHistoryList.clear();
            if (searchHistoryAdapter != null) {
                searchHistoryAdapter.notifyDataSetChanged();
            }
            SPUtils.getInstance().remove(SEARCH_HOTKEY);
        }
    }

    private void startActivity(String searchContent) {
        Intent intent = new Intent(this, DetailsContentActivity.class);
        intent.putExtra(CommonFinal.PAGE_TITLE, searchContent);
        intent.putExtra(CommonFinal.INTENT_TYPE,2);
        startActivity(intent);
    }


    public void searchHotKeyFailure() {
        ToastUtils.showShort("热词请求失败");
    }

    public void searchHotKeySucceed(SearchHotKey searchBean) {
        mHotKeyData.clear();
        for (int i = 0; i < searchBean.getData().size(); i++) {
            mHotKeyData.add(searchBean);
        }
        multiTypeAdapterHot.notifyDataSetChanged();
    }

    @Override
    public SearchPresenter creatPersenter() {
        return new SearchPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
