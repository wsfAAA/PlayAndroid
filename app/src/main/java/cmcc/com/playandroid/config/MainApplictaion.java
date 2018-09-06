package cmcc.com.playandroid.config;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;

import cmcc.com.playandroid.BuildConfig;

/**
 * Created by wsf on 2018/9/6.
 * 主项目的 Application
 */
public class MainApplictaion extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);//初始化工具类

        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        // 初始化 ARouter
        ARouter.init(this);
    }

    private boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
