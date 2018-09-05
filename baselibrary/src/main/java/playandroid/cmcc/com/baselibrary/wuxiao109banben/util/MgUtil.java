package playandroid.cmcc.com.baselibrary.wuxiao109banben.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wsf on 2018/9/5.
 */

public class MgUtil {
    public static final long INTERVAL = 500L;
    private static long lastClickTime = 0L;

    public MgUtil() {
    }

    public static boolean filter() {
        return filter(500L);
    }

    public static boolean filter(long interval) {
        if(System.currentTimeMillis() - lastClickTime > interval) {
            lastClickTime = System.currentTimeMillis();
            return true;
        } else {
            return false;
        }
    }

    public static void hideSoftInput(Context context) {
        Activity a = (Activity)context;
        InputMethodManager inputMethodManager = (InputMethodManager)a.getSystemService("input_method");
        if(a.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(a.getCurrentFocus().getWindowToken(), 0);
        }

    }

    public static void hideSoftInput(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)view.getContext().getSystemService("input_method");
        if(inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    public static void showSoftInput(Context context, View view) {
        if(context != null) {
            if(context instanceof Activity) {
                Activity a = (Activity)context;
                InputMethodManager inputMethodManager = (InputMethodManager)a.getSystemService("input_method");
                if(a.getCurrentFocus() != null) {
                    inputMethodManager.showSoftInput(view, 0);
                }
            }

        }
    }

    public static String getVideoData(String bodyHTML) {
        String head = "<head><style>img{max-width: 100%; width:auto; height: auto;}</style></head>";
        return "<html>" + head + "<video width='360' height='270' controls='controls' autoplay='autoplay'><source src='" + bodyHTML + "' type='video/mp4' /> </video></body></html>";
    }

    public static String getHtmlData(String bodyHTML) {
        String head = "<head><style>img{max-width: 100%; width:auto; height: auto;}</style></head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    public static int getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        int versionCode = 0;

        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return versionCode;
    }

    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String versionName = "";

        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return versionName;
    }

    public static final String MD5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes("UTF-8"));
            byte[] b = md5.digest();
            StringBuffer buf = new StringBuffer("");

            for(int offset = 0; offset < b.length; ++offset) {
                int i = b[offset];
                if(i < 0) {
                    i += 256;
                }

                if(i < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(i));
            }

            Log.e("TAG", "MD5: " + buf.toString());
            return buf.toString();
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public static String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    public static String dateToStr(String str) {
        if(str == null) {
            return "";
        } else {
            long lSysTime1 = Long.parseLong(str);
            Date dt = new Date(lSysTime1 * 1000L);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(dt);
            return dateString;
        }
    }

    public static String dateToStr(long time) {
        Date dt = new Date(time * 1000L);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd mm:ss");
        String dateString = formatter.format(dt);
        return dateString;
    }

    public static String fromTodayTime(long time) {
        return fromToday(new Date(time * 1000L));
    }

    public static String fromToday(Date date) {
        if(date == null) {
            return "";
        } else {
            long delta = ((new Date()).getTime() - date.getTime()) / 1000L;
            SimpleDateFormat time;
            if(delta / 31536000L <= 0L && delta >= 0L) {
                if(delta / 2592000L > 0L) {
                    time = new SimpleDateFormat("MM月dd日");
                    return time.format(date);
                } else if(delta / 86400L > 0L) {
                    return delta / 86400L > 1L && delta / 86400L <= 2L?"前天":delta / 86400L + "天前";
                } else if(delta / 3600L > 0L) {
                    int nday = (new Date()).getDate();
                    int bday = date.getDate();
                    return nday != bday?"昨天":delta / 3600L + "小时前";
                } else {
                    return delta / 60L > 0L?delta / 60L + "分钟前":"刚刚";
                }
            } else {
                time = new SimpleDateFormat("yyyy年MM月dd日");
                Log.e("tag", "fromToday: " + time.format(date));
                return time.format(date);
            }
        }
    }

    public static String fromToday(long time) {
        return fromToday(new Date(time));
    }
}
