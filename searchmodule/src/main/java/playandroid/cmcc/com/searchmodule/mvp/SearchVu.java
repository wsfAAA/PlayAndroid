package playandroid.cmcc.com.searchmodule.mvp;


import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import playandroid.cmcc.com.baselibrary.base.bk.MgMvpXVu;
import playandroid.cmcc.com.searchmodule.R;
import playandroid.cmcc.com.searchmodule.R2;
import playandroid.cmcc.com.searchmodule.SearchBean;

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void bindView() {
        super.bindView();
    }

    @OnClick({R2.id.img_search_back, R2.id.et_seach_content, R2.id.img_search})
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
        }
    }


    public void searchFailure() {

    }

    public void searchSucceed(SearchBean searchBean) {

    }
}
