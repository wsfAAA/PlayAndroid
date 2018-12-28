package test.opendingding.com.othermodule.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import test.opendingding.com.othermodule.R;
import test.opendingding.com.othermodule.popupclient.*;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLinear;
    private PopupClient build;
    private test.opendingding.com.othermodule.popupclient.WebViewBarMore mWebViewBarMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLinear = findViewById(R.id.root);
        Button button = findViewById(R.id.btn1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup();
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InterfacedActivity.class));
            }
        });
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DialogActivity.class));
            }
        });
        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RetrofitActivity.class));
            }
        });
    }

    private void popup() {
        if (mWebViewBarMore == null) {
            mWebViewBarMore = new test.opendingding.com.othermodule.popupclient.WebViewBarMore();
        }
        //指定位置
//        mWebViewBarMore.showPopup(mLinear, ScreenUtils.getScreenWidth(), 0);
        //底部
        mWebViewBarMore.showPopupButton(mLinear);
    }
}
