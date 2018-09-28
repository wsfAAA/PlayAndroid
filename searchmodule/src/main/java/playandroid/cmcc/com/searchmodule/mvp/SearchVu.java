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

import com.blankj.utilcode.util.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.multitype.MultiTypeAdapter;
import playandroid.cmcc.com.baselibrary.base.bk.MgMvpXVu;
import playandroid.cmcc.com.searchmodule.R;
import playandroid.cmcc.com.searchmodule.R2;
import playandroid.cmcc.com.searchmodule.SearchBean;
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
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private MultiTypeAdapter multiTypeAdapterHot;
    private LinearLayoutManager linearLayoutManager;
    private MultiTypeAdapter multiTypeAdapterHistory;

    private ArrayList<SearchHotKey> mHotKeyData = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void bindView() {
        super.bindView();
        multiTypeAdapterHot = new MultiTypeAdapter();
        multiTypeAdapterHot.register(SearchHotKey.class, new SraechItemBinder());
        multiTypeAdapterHot.setItems(mHotKeyData);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);
        mRecyclerHot.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerHot.setAdapter(multiTypeAdapterHot);

        multiTypeAdapterHistory = new MultiTypeAdapter();
//        multiTypeAdapterHistory.register()
//        multiTypeAdapterHistory.setItems();
        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerHistory.setLayoutManager(linearLayoutManager);
        mRecyclerHistory.setAdapter(multiTypeAdapterHistory);

        mPresenter.searchHotKeyRequest();
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
            }
        } else if (view.getId() == R.id.tv_search_clean) {

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
