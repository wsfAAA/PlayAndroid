package test.opendingding.com.othermodule.view.core;

import android.graphics.Canvas;

import test.opendingding.com.othermodule.view.core.mask.ColorMask;


/**
 * 在视图上面显示的遮罩层。通常均是覆盖在预览图片上的半透明遮罩层，目前也只实现了半透明样式的遮罩层 {@link ColorMask}
 */
public interface IMask {

    /**
     * 绘画具体的遮罩内容
     * @param layer 预览层
     * @param canvas 画布
     */
    void draw(ILayer layer, Canvas canvas);
}
