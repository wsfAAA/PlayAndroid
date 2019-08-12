package test.opendingding.com.othermodule.activity.designmodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import test.opendingding.com.othermodule.R;

public class ModelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);

        findViewById(R.id.bnt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}
