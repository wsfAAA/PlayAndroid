package test.opendingding.com.othermodule.activity;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import test.opendingding.com.othermodule.R;
import test.opendingding.com.othermodule.view.ScaleView;

public class PicActivity extends AppCompatActivity {

    /**
     * 问题点
     * 1、边界限制
     * 2、图片截取
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        final ScaleView scaleView = findViewById(R.id.ll);
        final TextView tvRect = findViewById(R.id.tv_rect);
        scaleView.post(new Runnable() {
            @Override
            public void run() {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tvRect.getLayoutParams();
                layoutParams.width = scaleView.getWidth();
                layoutParams.height = 600;

                int centerX = layoutParams.width/ 2;
                int centerY = layoutParams.height / 2;
                int shapeLeft = centerX - layoutParams.width / 2;
                int shapeRight = centerX + layoutParams.width / 2;
                int shapeTop = centerY - layoutParams.height / 2;
                int shapeBottom = centerY + layoutParams.height / 2;
                RectF rectF = new RectF(shapeLeft, shapeTop, shapeRight, shapeBottom);
                scaleView.setRestrict(rectF);
                tvRect.setLayoutParams(layoutParams);
            }
        });
    }
}
