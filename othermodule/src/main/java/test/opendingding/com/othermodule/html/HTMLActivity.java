package test.opendingding.com.othermodule.html;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import test.opendingding.com.othermodule.R;

public class HTMLActivity extends AppCompatActivity {

    private WebView contentWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);


        contentWebView = (WebView) findViewById(R.id.webview);
        // 启用javascript
        contentWebView.getSettings().setJavaScriptEnabled(true);
        // 从assets目录下面的加载html
        contentWebView.loadUrl("file:///android_asset/web.html");
        contentWebView.addJavascriptInterface(HTMLActivity.this,"android");


        //Button按钮 无参调用HTML js方法
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 无参数调用 JS的方法
                contentWebView.loadUrl("javascript:javacalljs()");
            }
        });

        //Button按钮 有参调用HTML js方法
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 传递参数调用JS的方法
                contentWebView.loadUrl("javascript:javacalljswith(" + "'android传递的参数'" + ")");  //给js传参 不要忘记 '' 号
            }
        });

    }


    //由于安全原因 targetSdkVersion>=17需要加 @JavascriptInterface
    //JS调用Android JAVA方法名和HTML中的按钮 onclick后的别名后面的名字对应
    @JavascriptInterface
    public void startFunction(){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(HTMLActivity.this,"js调用android无参参数",Toast.LENGTH_LONG).show();
            }
        });
    }

    @JavascriptInterface
    public void startFunction(final String text){
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                new AlertDialog.Builder(HTMLActivity.this).setMessage(text).show();
            }
        });
    }
}
