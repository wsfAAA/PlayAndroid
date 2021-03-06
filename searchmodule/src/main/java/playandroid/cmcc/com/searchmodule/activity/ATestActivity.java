package playandroid.cmcc.com.searchmodule.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;

import cmcc.com.playandroid.common.CommonFinal;
import playandroid.cmcc.com.searchmodule.R;
import playandroid.cmcc.com.searchmodule.SearchService;

public class ATestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atest);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ARouter.getInstance().build(CommonFinal.AROUTER_HOME).navigation();  // TODO: 2019/7/2  有问题，暂时不能确定
                ARouter.getInstance().build(CommonFinal.AROUTER_LOGIN).navigation();
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchService.getInstance().setTestString("组件接口测试传值");
                Log.i("wsf","SearchService.getInstance():  "+SearchService.getInstance());
                finish();
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchService.getInstance().setTestString("全局--组件接口测试传值");
                Log.i("wsf","SearchService.getInstance():  "+SearchService.getInstance());
            }
        });
    }
}
