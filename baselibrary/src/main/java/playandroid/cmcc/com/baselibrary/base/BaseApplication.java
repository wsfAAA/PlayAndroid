package playandroid.cmcc.com.baselibrary.base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;

import playandroid.cmcc.com.baselibrary.BuildConfig;

/**
 * 所有需要模块化开发的module都需要继承自BaseApplication
 * 提供：
 * 1、第三方库初始化
 * 2、通过全局 context
 * 3、Activity管理器
 */
public class BaseApplication extends Application {

    private static BaseApplication application;

    private ActivityManage activityManage;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        application = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initARouter();
        initUtils();
    }

    /**
     * 初始化工具类
     */
    private void initUtils() {
        Utils.init(this);
    }


    /**
     * 初始化路由
     */
    private void initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();  // 打印日志
            ARouter.openDebug(); // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application);// 尽可能早，推荐在Application中初始化
    }

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        exitApp();
    }

    /**
     * 获取全局唯一上下文
     *
     * @return BaseApplication
     */
    public static BaseApplication getApplication() {
        return application;
    }


    /**
     * 退出应用
     */
    public void exitApp() {
        if (activityManage != null) {
            activityManage.finishAll();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /**
     * 返回Activity管理器
     */
    public ActivityManage getActivityManage() {
        if (activityManage == null) {
            synchronized (BaseApplication.class) {
                if (activityManage == null) {
                    activityManage = new ActivityManage();
                }
            }
        }
        return activityManage;
    }

}