package test.opendingding.com.othermodule.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

import test.opendingding.com.othermodule.R;
import test.opendingding.com.othermodule.activity.clippic.MainActivity2;
import test.opendingding.com.othermodule.activity.clippic.ShowImageActivity;
import test.opendingding.com.othermodule.view.ScaleView;

public class PicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        final ScaleView scaleView = findViewById(R.id.ll);
        scaleView.setImageResource(R.drawable.tst1);
        final TextView tvRect = findViewById(R.id.tv_rect);
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap crop = scaleView.crop();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                crop.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] datas = baos.toByteArray();

                Intent intent = new Intent(PicActivity.this, ShowImageActivity.class);
                intent.putExtra("bitmap", datas);
                startActivity(intent);
            }
        });

        scaleView.post(new Runnable() {
            @Override
            public void run() {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tvRect.getLayoutParams();
                layoutParams.width = scaleView.getWidth()-200;
                layoutParams.height = 600;

                RectF rectF = new RectF(100, 0, layoutParams.width+100, layoutParams.height);

                Log.i("wsf","width: "+layoutParams.width+"    height: "+layoutParams.height);
                scaleView.setRestrict(rectF);
                tvRect.setLayoutParams(layoutParams);
            }
        });
    }
}
