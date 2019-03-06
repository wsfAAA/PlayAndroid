package test.opendingding.com.othermodule.view;
/**
 * 可以自由移动缩放的图片控件
 * Created by capton on 2017/4/18.
 */

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;


public class ScaleView extends AppCompatImageView implements OnGlobalLayoutListener, OnScaleGestureListener, OnTouchListener {

    /**
     * 表示是否只有一次加载
     */
    private boolean isOnce = false;

    /**
     * 初始时的缩放值
     */
    private float mInitScale;
    /**
     * 最大的缩放值
     */
    private float mMaxScale;
    /**
     * 图片缩放矩阵
     */
    private Matrix mMatrix;
    /**
     * 图片缩放手势
     */
    private ScaleGestureDetector mScaleGesture;

    // ----------------------------自由移动--------------------------------
    /**
     * 可移动最短距离限制，大于这个值时就可移动
     */
    private int mTouchSlop;
    /**
     * 是否可以拖动
     */
    private boolean isCanDrag;

    /**
     * 限制图片的范围
     */
    private RectF mRestrictRect;


    public ScaleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleView(Context context) {
        this(context, null);
    }

    public ScaleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // 必须设置才能触发
        this.setOnTouchListener(this);

        mMatrix = new Matrix();
        // 设置缩放模式
        super.setScaleType(ScaleType.MATRIX);

        //是用于处理缩放的工具类 ScaleGestureDetector 方法onScale、onScaleBegin、onScaleEnd
        mScaleGesture = new ScaleGestureDetector(context, this);

        /**
         * 触发移动事件的最小距离，自定义View处理touch事件的时候，有的时候需要判断用户是否真的存在movie，
         * 系统提供了这样的方法。表示滑动的时候，手的移动要大于这个返回的距离值才开始移动控件。
         */
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }


    @Override
    public void onGlobalLayout() {
        // 如果还没有加载图片
        if (!isOnce) {

            // 获得控件的宽高
            int width = getWidth();
            int height = getHeight();

            Drawable drawable = getDrawable();
            if (drawable == null) {
                return;
            }

            // 获得图片的宽高
            int bitmapWidth = drawable.getIntrinsicWidth();
            int bitmapHeight = drawable.getIntrinsicHeight();

            // 设定比例值
            float scale = 0.0f;

            // 如果图片的宽度>控件的宽度，缩小
            if (bitmapWidth > width && bitmapHeight < height) {
                scale = width * 1.0f / bitmapWidth;
            }
            // 如果图片的高度>控件的高度，缩小
            if (bitmapHeight > height && bitmapWidth < width) {
                scale = height * 1.0f / bitmapHeight;
            }
            // 如果图片的宽高度>控件的宽高度，缩小 或者 如果图片的宽高度<控件的宽高度，放大
            if ((bitmapWidth > width && bitmapHeight > height) || (bitmapWidth < width && bitmapHeight < height)) {
                float f1 = width * 1.0f / bitmapWidth;
                float f2 = height * 1.0f / bitmapHeight;
                scale = Math.min(f1, f2);
            }

            // 初始化缩放值
            mInitScale = scale;
            mMaxScale = mInitScale * 4;

            // 得到移动的距离
            int dx = width / 2 - bitmapWidth / 2;
            int dy = height / 2 - bitmapHeight / 2;

            // 平移
            mMatrix.postTranslate(dx, dy);

            // 在控件的中心缩放
            mMatrix.postScale(scale, scale, width / 2, height / 2);

            // 设置矩阵
            setImageMatrix(mMatrix);

            // 关于matrix，就是个3*3的矩阵
            /**
             * xscale xskew xtrans yskew yscale ytrans 0 0 0
             */

            isOnce = true;
        }
    }

    /**
     * 注册全局事件
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    /**
     * 移除全局事件
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    /**
     * 获得缩放值
     *
     * @return
     */
    public float getScale() {
        float[] values = new float[9];
        /**
         * Copy 9 values from the matrix into the array.
         */
        mMatrix.getValues(values);
        return values[Matrix.MSCALE_X];
    }

    /**
     * 缩放进行中，返回值表示是否下次缩放需要重置，如果返回ture，那么detector就会重置缩放事件，如果返回false，detector会在之前的缩放上继续进行计算
     */
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        // 如果没有图片，返回
        if (getDrawable() == null) {
            return true;
        }
        // 缩放因子，>0表示正在放大，<0表示正在缩小
        float intentScale = detector.getScaleFactor();
        float scale = getScale();

        /**
         *  进行缩放范围的控制
         */
        // 判断，如果<最大缩放值，表示可以放大，如果>最小缩放，说明可以缩小
        if ((scale < mMaxScale && intentScale > 1.0f) || (scale > mInitScale && intentScale < 1.0f)) {

            // scale 变小时， intentScale变小
            if (scale * intentScale < mInitScale) {
                // intentScale * scale = mInitScale ;
                intentScale = mInitScale / scale;
            }

            // scale 变大时， intentScale变大
            if (scale * intentScale > mMaxScale) {
                // intentScale * scale = mMaxScale ;
                intentScale = mMaxScale / scale;
            }

            // 以控件为中心缩放
            // mMatrix.postScale(intentScale, intentScale, getWidth()/2,
            // getHeight()/2);
            // 以手势为中心缩放
            mMatrix.postScale(intentScale, intentScale, detector.getFocusX(), detector.getFocusY());

//            Log.i("wsf","intentScale: "+intentScale+"   ,scale:  "+scale+"   ,mMaxScale: "+mMaxScale+"  ,mInitScale: "+mInitScale);

            // 检测边界与中心点
            checkSideAndCenterWhenScale();

            setImageMatrix(mMatrix);
        }

        return true;
    }

    /**
     * 缩放开始，返回值表示是否受理后续的缩放事件
     */
    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    /**
     * 缩放结束
     */
    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    /**
     * 获得图片缩放后的矩阵
     *
     * @return
     */
    public RectF getMatrixRectF() {
        Matrix matrix = mMatrix;
        RectF rectF = new RectF();
        Drawable drawable = getDrawable();
        if (drawable != null) {
            // 初始化矩阵
            rectF.set(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            // 移动s  将此矩阵应用于矩形，并将变换后的矩形写回其中
            matrix.mapRect(rectF);
        }
        return rectF;
    }

    private void checkSideAndCenterWhenScale() {
        RectF rectF = getMatrixRectF();
        float deltaX = 0f;
        float deltaY = 0f;
        int width = getWidth();
        int height = getHeight();

        // 情况1， 如果图片的宽度大于控件的宽度
        if (rectF.width() >= width) {
            if (rectF.left > 0) {
                deltaX = -rectF.left;// 如果图片没有左边对齐，就往左边移动
            }
            if (rectF.right < width) {
                deltaX = width - rectF.right;// 如果图片没有右边对齐，就往右边移动
            }
        }
        // 情况2， 如果图片的宽度大于控件的宽度
        if (rectF.height() >= height) {
            if (rectF.top > 0) {
                deltaY = -rectF.top;//
            }
            if (rectF.bottom < height) {
                deltaY = height - rectF.bottom;// 往底部移动
            }
        }

        // 情况3,如图图片在控件内，则让其居中
        if (rectF.width() < width) {
            // deltaX = width/2-rectF.left - rectF.width()/2;
            // 或
            deltaX = width / 2f - rectF.right + rectF.width() / 2f;
        }

        if (rectF.height() < height) {
            deltaY = height / 2f - rectF.bottom + rectF.height() / 2f;
        }

        mMatrix.postTranslate(deltaX, deltaY);
    }


    private float mLastX;
    private float mLastY;
    /**
     * 上次手指的数量
     */
    private int mLastPointerCount;

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        // 把事件传递给缩放手势
        mScaleGesture.onTouchEvent(event);

        float x = event.getX();
        float y = event.getY();

        int pointerCount = event.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            x += event.getX(i);
            y += event.getY(i);
        }
        x /= pointerCount;
        y /= pointerCount;

        // 说明手指改变
        if (mLastPointerCount != pointerCount) {
            isCanDrag = false;
            mLastX = x;
            mLastY = y;
        }
        mLastPointerCount = pointerCount;

        RectF rectF = getMatrixRectF();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (rectF.width() > getWidth()) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (rectF.width() > getWidth()) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                float dx = x - mLastX;
                float dy = y - mLastY;

                if (!isCanDrag) {
                    isCanDrag = isMoveAction(dx, dy);
                }
                /**
                 * 如果能移动
                 */
                if (isCanDrag) {
                    if (getDrawable() == null) {
                        return true;
                    }

                    /**
                     * 边界限制
                     */
                    if (mRestrictRect != null) {
                        if (dx > 0) {
                            if (rectF.left + dx > mRestrictRect.left) {
                                dx = mRestrictRect.left - rectF.left;
                            }
                        } else {
                            if (rectF.right + dx < mRestrictRect.right) {
                                dx = mRestrictRect.right - rectF.right;
                            }
                        }
                        if (dy > 0) {
                            if (rectF.top + dy > mRestrictRect.top) {
                                dy = mRestrictRect.top - rectF.top;
                            }
                        } else {
                            if (rectF.bottom + dy < mRestrictRect.bottom) {
                                dy = mRestrictRect.bottom - rectF.bottom;
                            }
                        }
                    }
                    mMatrix.postTranslate(dx, dy);
                    setImageMatrix(mMatrix);
                }

                mLastX = x;
                mLastY = y;

                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // 清楚手指
                mLastPointerCount = 0;

                break;
        }

        return true;
    }

    public void setRestrict(RectF restrict) {
        this.mRestrictRect = restrict;
    }

    /**
     * 求得两点的距离
     *
     * @param dx
     * @param dy
     * @return
     */
    private boolean isMoveAction(float dx, float dy) {
        return Math.sqrt(dx * dx + dy * dy) > mTouchSlop;
    }

}
