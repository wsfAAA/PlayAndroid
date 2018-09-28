package playandroid.cmcc.com.searchmodule.mvp;

import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import playandroid.cmcc.com.baselibrary.base.bk.MgBaseVu;
import playandroid.cmcc.com.searchmodule.R;
import playandroid.cmcc.com.searchmodule.SearchHotKey;

/**
 * Created by wsf on 2018/9/27.
 */

public class SearchHotKeyVu extends MgBaseVu<SearchHotKey> {

    @BindView(R.id.tv_setchkhotkey)
    TextView tvSetchkhotkey;

    @Override
    public int getLayoutId() {
        return R.layout.search_hotkey_item_layout;
    }

    @Override
    public void bindView() {
        super.bindView();
    }

    @Override
    public void bindData(SearchHotKey data) {
        super.bindData(data);

    }
}
