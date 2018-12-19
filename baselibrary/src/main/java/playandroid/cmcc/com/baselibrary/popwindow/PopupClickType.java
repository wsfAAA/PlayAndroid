package playandroid.cmcc.com.baselibrary.popwindow;

/**
 * Created by wsf on 2018/12/19.
 */

public enum PopupClickType {
    /**
     *  点击屏幕外 和 返回键都消失
     */
    SCREEN_YES_AND_RETURN_KEY_YSE,

    /**
     *  点击屏幕外消失，点击返回键退出界面
     */
    SCREEN_YES_AND_RETURN_KEY,

    /**
     *  点击屏幕外不消失，点击返回键退出界面 popup外区域依旧可以操作
     */
    SCREEN_NO_AND_RETURN_KEY_NO_AND_WORK,

}
