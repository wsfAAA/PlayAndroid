package playandroid.cmcc.com.searchmodule.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import playandroid.cmcc.com.searchmodule.R;

public class SearchWebViewActivity extends AppCompatActivity {

    public final static String SEARCH_HTTP_URL = "SEARCH_HTTP_URL";
    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_web_view);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String url = intent.getStringExtra(SEARCH_HTTP_URL);
        webview.loadUrl(url);
    }
}
