package test.opendingding.com.othermodule.activity;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import test.opendingding.com.othermodule.R;
import test.opendingding.com.othermodule.view.ScaleView;

public class PicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        final ScaleView scaleView = findViewById(R.id.ll);
        scaleView.setImageResource(R.drawable.cover1);
        final TextView tvRect = findViewById(R.id.tv_rect);
        final ImageView imgPic = findViewById(R.id.img_pic);
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap crop = scaleView.crop();
                Log.i("wsf","picwidth: "+crop.getWidth()+"    picheight: "+crop.getHeight());
                if (crop != null) {
                    imgPic.setVisibility(View.VISIBLE);
                    imgPic.setImageBitmap(crop);
                } else {
                    imgPic.setVisibility(View.GONE);
                }
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgPic.setVisibility(View.GONE);
            }
        });

        scaleView.post(new Runnable() {
            @Override
            public void run() {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tvRect.getLayoutParams();
                layoutParams.width = scaleView.getWidth()-200;
                layoutParams.height = 600;

                RectF rectF = new RectF(0, 0, layoutParams.width, layoutParams.height);

                Log.i("wsf","width: "+layoutParams.width+"    height: "+layoutParams.height);
                scaleView.setRestrict(rectF);
                tvRect.setLayoutParams(layoutParams);
            }
        });
    }
}
