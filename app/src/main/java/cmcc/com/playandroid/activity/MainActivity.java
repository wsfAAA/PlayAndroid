package cmcc.com.playandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.live.base.BaseConstant;
import com.live.base.dialog.ProgressPopup;
import com.live.base.net.RxClient;
import com.live.base.net.callback.DownloadListener;
import com.live.base.net.callback.RxCallBack;
import com.live.base.net.http.OkhttpRequest;

import androidx.appcompat.app.AppCompatActivity;
import cmcc.com.playandroid.databinding.ActivityMainBinding;
import cmcc.com.playandroid.mvptest.Test02Activity;

public class MainActivity extends AppCompatActivity {

    private ProgressPopup progressPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        progressPopup = new ProgressPopup(this);

        binding.tvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Test02Activity.class));
            }
        });
        binding.tvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.permission(PermissionConstants.STORAGE).callback(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void onGranted() {
                        download();
                    }

                    @Override
                    public void onDenied() {

                    }
                }).request();
            }
        });

        RxClient.builder()
                .baseUrl("https://www.wanandroid.com")
                .addHttpRequest(new OkhttpRequest())
                .build()
                .rxGet("/wxarticle/chapters/json ", new RxCallBack<String>() {
                    @Override
                    public void rxOnNext(String response) {
                        Log.e("wsf", "数据:  " + response);
                    }

                    @Override
                    public void rxOnError(Throwable e) {
                        Log.e("wsf", "错误:  " + e);
                    }
                });
    }

    private void download() {
        String updateUrl = "/qqmi/aphone_p2p/TencentVideo_V6.0.0.14297_848.apk";  //下载地址
        RxClient.builder()
                .baseUrl("http://dldir1.qq.com")
                .build()
                .rxDownload(updateUrl, BaseConstant.APP_ROOT_PATH + BaseConstant.DOWNLOAD_DIR, "腾讯视频2app.apk", new DownloadListener() {
                    @Override
                    public void onStart(int progress) {
                        Log.e("wsf", "onStart:  " + progress);
                        progressPopup.setProgress(progress, "正在下载中");
                        progressPopup.setOutSideDismiss(false);
                        progressPopup.setBackPressEnable(false);
                        progressPopup.showPopupWindow();
                    }

                    @Override
                    public void onProgress(int progress) {
                        Log.e("wsf", "onProgress:  " + progress);
                        progressPopup.setProgress(progress, "正在下载中");
                    }

                    @Override
                    public void onCompleted(String path) {
                        Log.e("wsf", "下载完成:  " + path);
                        progressPopup.dismiss();
                    }

                    @Override
                    public void onError(String msg) {
                        Log.e("wsf", "下载失败: " + msg);
                        progressPopup.dismiss();
                        ToastUtils.showShort("下载失败!  " + msg);
                    }
                });
    }
}
