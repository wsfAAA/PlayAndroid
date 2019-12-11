package test.opendingding.com.othermodule.permission;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import test.opendingding.com.othermodule.R;

/**
 * 动态权限
 */
public class PermissionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);

        // 1、两个日历权限和一个数据读写权限
        final String[] permissions = new String[]{Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //2、是否支持显示系统设置权限设置窗口跳转,fales不支持、true支持
                PermissionsUtils.showSystemSetting = false;
                //3、工具类 权限检测
                PermissionsUtils.getInstance().chekPermissions(PermissionsActivity.this, permissions, permissionsResult);
            }
        });
    }

    //4、权限检测结果 监听
    PermissionsUtils.IPermissionsResult permissionsResult = new PermissionsUtils.IPermissionsResult() {
        @Override
        public void passPermissons() {
            ToastUtils.showShort("权限通过!");
        }

        @Override
        public void forbitPermissons() {
            ToastUtils.showShort("权限不通过!");
        }
    };

    //5、重写 onRequestPermissionsResult 权限处理结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //6、权限处理结果给工具类进行处理
        PermissionsUtils.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
}
