package playandroid.cmcc.com.baselibrary.base;

import android.webkit.WebSettings;
import android.webkit.WebView;

import playandroid.cmcc.com.baselibrary.base.BaseActivity;
import playandroid.cmcc.com.baselibrary.base.BaseApplication;

/**
 * Created by wsf on 2018/12/18.
 */

public abstract class BaseWebViewActivity extends BaseActivity {

    private WebView webView;
    private WebSettings webSettings;

    @Override
    protected void initView() {
        initWeb();
        initWebView(webView,webSettings);
    }

    private void initWeb() {
        webView = new WebView(BaseApplication.getApplication());
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);     //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
//        webSettings.setSupportZoom(true); //支持缩放，默认为true。是setBuiltInZoomControls的前提。
        webSettings.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
//        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
//        webSettings.setAllowFileAccess(true); //设置可以访问文件
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
//        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格

        //优先使用缓存:
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //缓存模式如下：
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。

    }

    protected abstract void initWebView(WebView webView,WebSettings webSettings);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView = null;
        webSettings=null;
    }
}
