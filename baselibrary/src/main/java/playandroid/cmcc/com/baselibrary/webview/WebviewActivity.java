package playandroid.cmcc.com.baselibrary.webview;


import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ScreenUtils;

import butterknife.BindView;
import butterknife.OnClick;
import playandroid.cmcc.com.baselibrary.R;
import playandroid.cmcc.com.baselibrary.R2;
import playandroid.cmcc.com.baselibrary.base.BaseWebViewActivity;
import playandroid.cmcc.com.baselibrary.util.WebViewRoute;

@Route(path = "/search/webactivity")
public class WebviewActivity extends BaseWebViewActivity {

    @BindView(R2.id.m_relative)
    RelativeLayout mRelative;
    @BindView(R2.id.m_img_back)
    ImageView mImgBack;
    @BindView(R2.id.m_img_close)
    ImageView mImgClose;
    @BindView(R2.id.m_img_more)
    ImageView mImgMore;
    @BindView(R2.id.m_tv_title)
    TextView mTvTitle;
    @BindView(R2.id.progressBar)
    ProgressBar mProgressBar;

    private String mUrl;
    private boolean mIsLoadUrl; //是否使用本webview加载 url,true使用 false不使用
    private WebView mWebview;
    private WebViewBarMore mWebViewBarMore;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initWebView(WebView webView, WebSettings webSettings) {
        mWebview = webView;
        mRelative.addView(webView);
        mUrl = getIntent().getStringExtra(WebViewRoute.WEBVIEW_URL);
        mIsLoadUrl = getIntent().getBooleanExtra(WebViewRoute.WEBVIEW_LOAD_URL, true);
        mWebview.loadUrl(mUrl);

        webViewClient();
        webChromeClient();
    }

    /**
     * 辅助 WebView 处理 网站图标,网站标题等等
     */
    private void webChromeClient() {
        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress < 100) {
                    mProgressBar.setProgress(newProgress);
                } else {
                    mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (!TextUtils.isEmpty(title)) {
                    mTvTitle.setText(title);
                }
            }
        });
    }

    /**
     * 处理各种通知 & 请求事件
     */
    private void webViewClient() {
        mWebview.setWebViewClient(new WebViewClient() {

            /**
             * 拦截url  true自己处理，false交给webview处理，注意post请求shouldOverrideUrlLoading不会调用此方法
             * @param view
             * @param url
             * @return
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!RegexUtils.isURL(url)) {
                    return false;
                }
                if (mIsLoadUrl) {
                    WebViewRoute.getInstance().loadWebPage(view, url);
                } else {
                    WebViewRoute.getInstance().handleWebUrl("/search/webactivity", url, WebviewActivity.this);
                }
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.GONE);
                checkClose();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                checkClose();
            }
        });
    }

    private void checkClose() {
        if (mWebview.canGoBack()) {
            mImgClose.setVisibility(View.VISIBLE);
        } else {
            mImgClose.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        if (mWebview != null) {
            mRelative.removeAllViews();
            mWebview.clearHistory();
            mWebview.destroy();
            mWebview = null;
        }
        super.onDestroy();
        if (mWebViewBarMore != null) {
            mWebViewBarMore.destory();
        }
    }


    @OnClick({R2.id.m_img_back, R2.id.m_img_close, R2.id.m_img_more})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.m_img_back) {
            goBack();
        } else if (i == R.id.m_img_close) {
            finish();
        } else if (i == R.id.m_img_more) {
            if (mWebViewBarMore == null) {
                mWebViewBarMore = new WebViewBarMore(this);
            }
            mWebViewBarMore.showPopup();
        }
    }

    /**
     * 返回键拦截
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebview.canGoBack()) {
                mWebview.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void goBack() {
        if (mWebview.canGoBack()) {
            mWebview.goBack();
        } else {
            finish();
        }
    }
}
