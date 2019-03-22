package playandroid.cmcc.com.baselibrary.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import playandroid.cmcc.com.baselibrary.BuildConfig;
import playandroid.cmcc.com.baselibrary.R;
import playandroid.cmcc.com.baselibrary.view.MiguClassicHeader;
import playandroid.cmcc.com.baselibrary.view.MiguShortVideoFooter;

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


    static {
        // TODO: 2019/1/18 SmartRefreshLayout 全局的Header构建器、Footer构建器， 可以全局配置 也可以xml中配置  参考：https://github.com/scwang90/SmartRefreshLayout/blob/master/art/md_custom.md
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.color_EA1E63, android.R.color.white);//全局设置主题颜色
//                return new ClassicsHeader(context);// 指定为经典Header，默认是 贝塞尔雷达Header

                return new MiguClassicHeader(context);
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
//                return new ClassicsFooter(context).setDrawableSize(20);//指定为经典Footer，默认是 BallPulseFooter

                return new MiguShortVideoFooter(context);
            }
        });
    }


    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        MultiDex.install(this);
        initARouter();
        initUtils();

        Log.i("wsf","BaseApplication  onCreate");
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