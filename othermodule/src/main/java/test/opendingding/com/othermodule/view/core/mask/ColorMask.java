package test.opendingding.com.othermodule.view.core.mask;

import android.graphics.Canvas;
import android.graphics.Color;

import test.opendingding.com.othermodule.view.core.ILayer;
import test.opendingding.com.othermodule.view.core.IMask;


/**
 * 颜色遮罩层
 */
public class ColorMask implements IMask {

    private int mColorId;

    public ColorMask(String color) {
        mColorId = Color.parseColor(color);
    }

    public ColorMask(int colorId) {
        mColorId = colorId;
    }

    @Override
    public void draw(ILayer layer, Canvas c) {
        c.drawColor(mColorId);
    }

    /**
     * 获取半透明的遮罩层
     *
     * @return
     */
    public static final ColorMask getTranslucentMask() {
        return new ColorMask("#90000000");
    }
}
