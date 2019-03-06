package test.opendingding.com.othermodule.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wsf on 2019/3/5.
 */

public class RectView extends View {

    public RectView(Context context) {
        super(context);
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.STROKE);//设置空心
//        paint.setStrokeWidth(5);
//        rect = new Rect(left, top, right, bottom);
//        canvas.drawRect(rect, paint);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.DKGRAY);
        Path path = new Path();
        path.moveTo(0, 10);
        path.lineTo(480, 10);
        PathEffect effects = new DashPathEffect(new float[]{5, 5, 5, 5}, 1);
        paint.setPathEffect(effects);
        canvas.drawPath(path, paint);
    }


}
