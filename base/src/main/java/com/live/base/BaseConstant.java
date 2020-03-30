package com.live.base;

import android.os.Environment;

public class BaseConstant {
    public final static String APP_ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + BaseApplication.getBaseApplication().getPackageName();
    public final static String DOWNLOAD_DIR = "/downlaod/";
}
