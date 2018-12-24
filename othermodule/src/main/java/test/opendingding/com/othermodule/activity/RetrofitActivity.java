package test.opendingding.com.othermodule.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import test.opendingding.com.othermodule.R;
import test.opendingding.com.othermodule.net.RetrofitClient;
import test.opendingding.com.othermodule.net.callback.IError;
import test.opendingding.com.othermodule.net.callback.IFailure;
import test.opendingding.com.othermodule.net.callback.ISuccess;
import test.opendingding.com.othermodule.net.ui.LoaderStyle;

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get();
            }
        });

        findViewById(R.id.upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload();
            }
        });
    }

    /**
     * 下载使用
     */
    private void upload() {
        RetrofitClient.builder().url("").success(new ISuccess() {
            @Override
            public void onSuccess(String response) {

            }
        }).failure(new IFailure() {
            @Override
            public void onFailure(Throwable throwable) {

            }
        }).file("").build().upload();
    }

    /**
     * git 使用
     */
    private void get() {
        RetrofitClient.builder().url("/wxarticle/chapters/json")
                .loader(this, LoaderStyle.BallBeatIndicator)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        ToastUtils.showShort("成功:"+response);
                    }
                }).error(new IError() {
            @Override
            public void onError(int code, String msg) {
                ToastUtils.showShort(code + "  " + msg);
            }
        }).failure(new IFailure() {
            @Override
            public void onFailure(Throwable throwable) {
                ToastUtils.showShort("失败:  " + throwable.toString());
            }
        }).build().get();
    }
}
