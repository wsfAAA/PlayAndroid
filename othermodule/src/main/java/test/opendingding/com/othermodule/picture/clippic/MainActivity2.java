package test.opendingding.com.othermodule.picture.clippic;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;


import test.opendingding.com.othermodule.R;
import test.opendingding.com.othermodule.view.ClipImageLayout;

/**
 * http://blog.csdn.net/lmj623565791/article/details/39761281
 *
 * @author zhy
 */
public class MainActivity2 extends Activity {
    private ClipImageLayout mClipImageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mClipImageLayout = (ClipImageLayout) findViewById(R.id.id_clipImageLayout);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = mClipImageLayout.clip();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] datas = baos.toByteArray();

                Intent intent = new Intent(MainActivity2.this, ShowImageActivity.class);
                intent.putExtra("bitmap", datas);
                startActivity(intent);
            }
        });
    }
}
