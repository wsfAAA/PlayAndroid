package playandroid.cmcc.com.searchmodule.activity;

import android.content.Intent;
import android.webkit.WebView;

import butterknife.BindView;
import playandroid.cmcc.com.baselibrary.base.jadapter.basemvp.BaseActivity;
import playandroid.cmcc.com.searchmodule.R;

public class SearchWebViewActivity extends BaseActivity {

    public final static String SEARCH_HTTP_URL = "SEARCH_HTTP_URL";
    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        String url = intent.getStringExtra(SEARCH_HTTP_URL);
        webview.loadUrl(url);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_search_web_view;
    }
}
