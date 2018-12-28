package test.opendingding.com.othermodule.popupclient;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import test.opendingding.com.othermodule.MyApp;


/**
 * Created by wsf on 2018/12/19.
 */

public class PopupClient extends PopupWindow {

    private final int ANIM_STYLE;            //动画样式
    private final int HEIGHT;                //自定义高度
    private final int WIDTH;                 //自定义宽度
    private View VIEW;                       //view形式布局view
    private final int LAYOUT_VIEW;           //layout形式布局view
    private final PopupClickType CLICK_TYPE; //popup区域外点击 和 返回键处理

    public PopupClient(int mAnimStyle, int mHeight, int mWidth, View mView, int mLayoutView, PopupClickType clickType) {
        this.ANIM_STYLE = mAnimStyle;
        this.HEIGHT = mHeight;
        this.WIDTH = mWidth;
        this.VIEW = mView;
        this.LAYOUT_VIEW = mLayoutView;
        this.CLICK_TYPE = clickType;
        checkConfig();
    }


    public static PopupClientBuilder builder() {
        return new PopupClientBuilder();
    }

    /**
     * 获取 popupwindow视图view
     *
     * @return
     */
    public View getView() {
        return VIEW;
    }


    /**
     * 显示到指定位置
     *
     * @param view view父视图
     * @param x    x偏移量
     * @param y    y偏移量
     * @return
     */
    public PopupClient showPopupWindowPosition(View view, int x, int y) {
        /**
         *  showAtLocation: 在指定位置的弹出窗口中显示内容视图
         *  parent: View父视图
         *  gravity: 弹出窗口位置的重力值 例如：底部弹出、左边弹出、右边弹出等
         *
         *  控制显示位置，0，0左上角
         *  x: 弹出窗口的x坐标偏移
         *  y: 弹出窗口的y坐标偏移
         */
        showAtLocation(view, Gravity.NO_GRAVITY, x, y);
        return this;
    }

    /**
     * 底部弹出
     *
     * @param view
     * @return
     */
    public PopupClient showPopupWindowBottom(View view) {
        showAtLocation(view, Gravity.BOTTOM, 0, 0);
        return this;
    }

    /**
     * 显示在某一view下方
     *
     * @param view
     * @return
     */
    public PopupClient showPopupWindowViewBottom(View view) {
        showAsDropDown(view);
        return this;
    }

    private void checkConfig() {
        if (LAYOUT_VIEW != 0) {
            VIEW = View.inflate(MyApp.MY_APP, LAYOUT_VIEW, null);
        }
        if (VIEW == null) {
            new NullPointerException("view no null!");
        }
        setContentView(VIEW);
        setAnimationStyle(ANIM_STYLE);
        if (HEIGHT > 0) {
            setHeight(HEIGHT);
        } else {
            setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        }
        if (WIDTH > 0) {
            setWidth(WIDTH);
        } else {
            setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        }

        if (PopupClickType.SCREEN_YES_AND_RETURN_KEY_YSE == CLICK_TYPE) {//点击屏幕外 和 返回键都消失
            setFocusable(true);
//            setBackgroundDrawable(new PaintDrawable());
        }
        if (PopupClickType.SCREEN_NO_AND_RETURN_KEY_NO_AND_WORK == CLICK_TYPE) {//点击屏幕外不消失,返回键退出界面 popup外区域依旧可以操作
            setOutsideTouchable(false);
        }
        if (PopupClickType.SCREEN_YES_AND_RETURN_KEY == CLICK_TYPE) {//点击屏幕外消失，点击返回键退出界面
            /**
             * setFocusable 为true,setOutsideTouchable 无效
             */
            setOutsideTouchable(true);
        }
    }

}
