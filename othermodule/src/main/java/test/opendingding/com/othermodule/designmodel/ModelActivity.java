package test.opendingding.com.othermodule.designmodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import test.opendingding.com.othermodule.R;

/**
 * 设计模式
 */
public class ModelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);

        //静态代理  https://www.jianshu.com/p/f82a03ec5110
        findViewById(R.id.bnt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDaoProxy daoProxy = new UserDaoProxy(new UserDao());
                daoProxy.addRecord("添加记录");
            }
        });
    }
}
