package cmcc.com.playandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;

import cmcc.com.playandroid.common.CommonFinal;


//@Route(path = CommonFinal.AROUTER_HOME)
public class AppNewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_test_app);
    }
}
