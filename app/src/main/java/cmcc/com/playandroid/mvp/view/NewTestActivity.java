package cmcc.com.playandroid.mvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

import cmcc.com.playandroid.R;
import cmcc.com.playandroid.common.CommonFinal;

@Route(path = CommonFinal.AROUTER_HOME)
public class NewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_test);
    }
}
