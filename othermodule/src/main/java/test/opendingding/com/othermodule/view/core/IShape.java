package test.opendingding.com.othermodule.view.core;

import test.opendingding.com.othermodule.view.core.clippath.ClipPathCircle;

/**
 * 裁剪框接口,其返回的长宽会是最终裁剪的裁剪大小.具体实现方式参照{@link ClipPathCircle}
 */
public interface IShape  {

    /**
     * 裁剪框宽度
     * @return
     */
    int width();

    /**
     * 裁剪框高度
     * @return
     */
    int height();
}
