package test.opendingding.com.othermodule.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import playandroid.cmcc.com.baselibrary.view.BaseLoadingView;
import test.opendingding.com.othermodule.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        BaseLoadingView baseLoadingView = findViewById(R.id.mBaseLoadingView);
//        baseLoadingView.showLoading();
    }
}
