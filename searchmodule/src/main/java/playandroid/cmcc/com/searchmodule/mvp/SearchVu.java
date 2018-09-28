package playandroid.cmcc.com.searchmodule.mvp;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.multitype.MultiTypeAdapter;
import playandroid.cmcc.com.baselibrary.base.bk.MgMvpXVu;
import playandroid.cmcc.com.searchmodule.R;
import playandroid.cmcc.com.searchmodule.R2;
import playandroid.cmcc.com.searchmodule.SearchBean;
import playandroid.cmcc.com.searchmodule.SearchHistoryAdapter;
import playandroid.cmcc.com.searchmodule.SearchHotKey;
import playandroid.cmcc.com.searchmodule.SraechItemBinder;

/**
 * Created by wsf on 2018/9/17.
 */

public class SearchVu extends MgMvpXVu<SearchPresenter> {

    @BindView(R2.id.img_search_back)
    ImageView imgSearchBack;
    @BindView(R2.id.et_seach_content)
    AutoCompleteTextView etSeachContent;
    @BindView(R2.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.recycler_hot)
    RecyclerView mRecyclerHot;
    @BindView(R.id.tv_search_clean)
    TextView tvSearchClean;
    @BindView(R.id.recycler_history)
    RecyclerView mRecyclerHistory;
    @BindView(R.id.tv_no_history)
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
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void bindView() {
        super.bindView();
        searchHotKey();
        searchHistory();
    }

    /**
     * 历史记录
     */
    private void searchHistory() {
        searchHistoryAdapter = new SearchHistoryAdapter(context, mSearchHistoryList, mRootListeren, mCleanListener);
        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mRecyclerHistory.setLayoutManager(linearLayoutManager);
        mRecyclerHistory.setAdapter(searchHistoryAdapter);

        Set<String> stringSet = SPUtils.getInstance().getStringSet(SEARCH_HOTKEY);
        if (stringSet != null&&stringSet.size()>0) {
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
                ToastUtils.showShort(historyKey);
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
            SPUtils.getInstance().put(SEARCH_HOTKEY,mSearchHistory);
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
        mPresenter.searchHotKeyRequest();
        multiTypeAdapterHot = new MultiTypeAdapter();
        SraechItemBinder sraechItemBinder = new SraechItemBinder();
        sraechItemBinder.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchHotKey.DataBean hotKeyBean = (SearchHotKey.DataBean) v.getTag(R.id.tv_search_hotkey);
                if (hotKeyBean != null) {
                    saveSearchHistory(hotKeyBean.getName());
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
            ((Activity) context).finish();
        } else if (view.getId() == R.id.img_search) {
            String searchContent = etSeachContent.getText().toString();
            if (TextUtils.isEmpty(searchContent)) {
                ToastUtils.showShort("请输入搜索内容");
            } else {
                mPresenter.searchRequest(searchContent);
                saveSearchHistory(searchContent);
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


    public void searchFailure() {

    }

    public void searchSucceed(SearchBean searchBean) {

    }

    public void searchHotKeyFailure() {

    }

    public void searchHotKeySucceed(SearchHotKey searchBean) {
        mHotKeyData.clear();
        for (int i = 0; i < searchBean.getData().size(); i++) {
            mHotKeyData.add(searchBean);
        }
        multiTypeAdapterHot.notifyDataSetChanged();
    }
}
