package playandroid.cmcc.com.baselibrary.popwindow;


import android.view.View;

/**
 * Created by wsf on 2018/12/19.
 */

public class PopupClientBuilder {
    private int mAnimStyle;                  //动画样式
    private int mHeight;                     //自定义高度
    private int mWidth;                      //自定义宽度
    private View mView;                      //view形式布局view
    private int mLayoutView;                 //layout形式布局view
    private PopupClickType mClickType=PopupClickType.SCREEN_YES_AND_RETURN_KEY_YSE;  //popup区域外点击 和 返回键处理,默认都消失

    public PopupClientBuilder() {
    }

    public final PopupClient build() {
        return new PopupClient(mAnimStyle, mHeight, mWidth, mView, mLayoutView,mClickType);
    }

    public PopupClientBuilder animStyle(int mAnimStyle) {
        this.mAnimStyle = mAnimStyle;
        return this;
    }

    public PopupClientBuilder clickType(PopupClickType clickType) {
        this.mClickType = clickType;
        return this;
    }

    public PopupClientBuilder height(int mHeight) {
        this.mHeight = mHeight;
        return this;
    }

    public PopupClientBuilder width(int mWidth) {
        this.mWidth = mWidth;
        return this;
    }

    public PopupClientBuilder view(View mView) {
        this.mView = mView;
        return this;
    }

    public PopupClientBuilder view(int mView) {
        this.mLayoutView = mView;
        return this;
    }
}
