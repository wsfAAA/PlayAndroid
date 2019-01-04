package playandroid.cmcc.com.baselibrary.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.webkit.WebView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by wsf on 2018/12/18.
 */

public class WebViewRoute {

    public static final String WEBVIEW_URL = "WEBVIEW_URL";
    public static final String WEBVIEW_LOAD_URL = "WEBVIEW_LOAD_URL"; //是否使用本webview加载 url,true使用 false不使用 ，默认使用

    private WebViewRoute() {
    }

    private static class Holder {
        private static final WebViewRoute INSTANCE = new WebViewRoute();
    }

    public static WebViewRoute getInstance() {
        return Holder.INSTANCE;
    }

    /**
     *  web路由
     * @param aRouter  跳转页面路由地址
     * @param url      web url
     * @param activity
     * @return
     */
    public final boolean handleWebUrl(String aRouter,String url, Activity activity) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }

        //建议可以针url中对不同协议进行不同处理,如电话协议
        if (url.contains("tel:")) {
            callPhone(activity, url);
            return true;
        }

        ARouter.getInstance().build(aRouter)
                .withBoolean(WEBVIEW_LOAD_URL,false)
                .withString(WEBVIEW_URL,url)
                .navigation();
        return true;
    }


    /**
     * 本webview加载
     *
     * @param webView
     * @param url
     */
    public void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("WebView is null!");
        }
    }

    /**
     * 打电话
     *
     * @param context
     * @param uri
     */
    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }
}
