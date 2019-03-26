package test.opendingding.com.othermodule.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;

import test.opendingding.com.othermodule.QRcode.QRCodeActivity;
import test.opendingding.com.othermodule.R;
import test.opendingding.com.othermodule.activity.clippic.MainActivity2;
import test.opendingding.com.othermodule.popupclient.*;
import test.opendingding.com.othermodule.utile.PermissionsUtils;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLinear;
    private PopupClient build;
    private WebViewBarMore mWebViewBarMore;

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
        findViewById(R.id.bnt5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScaleImageActivity.class));
            }
        });
        findViewById(R.id.bnt6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PicActivity.class));
            }
        });
        findViewById(R.id.bnt7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });

        findViewById(R.id.bnt8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相机权限
                String[] permissions = new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

                PermissionsUtils.showSystemSetting = false;//是否支持显示系统设置权限设置窗口跳转
                PermissionsUtils.getInstance().chekPermissions(MainActivity.this, permissions, permissionsResult);

            }
        });
        findViewById(R.id.btn_quanxian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PermissionsActivity.class));
            }
        });
    }

    //创建监听权限的接口对象
    PermissionsUtils.IPermissionsResult permissionsResult = new PermissionsUtils.IPermissionsResult() {
        @Override
        public void passPermissons() {
            ToastUtils.showShort("权限通过!");
            startActivity(new Intent(MainActivity.this, QRCodeActivity.class));
        }

        @Override
        public void forbitPermissons() {
            ToastUtils.showShort("权限不通过!");
        }
    };

    /**
     * 权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsUtils.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    private void popup() {
        if (mWebViewBarMore == null) {
            mWebViewBarMore = new WebViewBarMore();
        }
        //指定位置
//        mWebViewBarMore.showPopup(mLinear, ScreenUtils.getScreenWidth(), 0);
        //底部
        mWebViewBarMore.showPopupButton(mLinear);
    }
}
