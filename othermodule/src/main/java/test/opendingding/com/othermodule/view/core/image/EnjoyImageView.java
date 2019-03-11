package test.opendingding.com.othermodule.view.core.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * 支持缩放功能、拖动功能的ImageView
 */
public class EnjoyImageView extends ImageView {
    private ImageMatrixTouchImpl mImageToucheHandler;

    public EnjoyImageView(Context context) {
        super(context);
        init();
    }


    public EnjoyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mImageToucheHandler = ImageMatrixTouchImpl.newInstance(this);
        mImageToucheHandler.init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public double getScale() {
        return mImageToucheHandler.getScale();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = mImageToucheHandler.touch(event);
        if (result) {
            return result;
        } else {
            return super.onTouchEvent(event);
        }
    }

    /**
     * x 轴实际滚动
     *
     * @return
     */
    public float getActuallyScrollX() {
        return getScrollX() + mImageToucheHandler.getScrollX();
    }

    /**
     * y 轴实际滚动
     *
     * @return
     */
    public float getActuallyScrollY() {
        return getScrollY() + mImageToucheHandler.getScrollY();
    }

    /**
     * 设置图片移动放大边界
     *
     * @param rectF
     * @return
     */
    public void setRestrictBound(RectF rectF) {
        mImageToucheHandler.setRestrictRect(rectF);
    }
}
