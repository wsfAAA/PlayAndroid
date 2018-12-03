package playandroid.cmcc.com.baselibrary.mgnet.interceptor;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by caominyan on 2018/4/4.
 */

public class CommonAppInterceptor implements Interceptor {


    public CommonAppInterceptor() {
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
//        Request oldRequest = chain.request();
//        Request newRequest = addQueryParam(oldRequest);
//        return chain.proceed(newRequest);
        return null;
    }


    /**
     * 给所有的网络请求统一加上必需的数据
     */
    private Request addQueryParam(Request oldRequest) {

        boolean isAddCommonInterceptor = true;
        String clazzName = "clazzName";

        String flag = oldRequest.headers().get("miguIntercept");
        if("false".equals(flag)){
            isAddCommonInterceptor = false;
        }
        String callServiceStr = oldRequest.headers().get("callService");
        if(!TextUtils.isEmpty(callServiceStr)){
            clazzName = callServiceStr;
        }


        Request.Builder request = oldRequest.newBuilder();


            // 所有请求必须参数
        String cid = "A_abcdeagagda"  ;
        request.addHeader("x-migutv-cid", cid);
        request.addHeader("x-migutv-logintime", "");
        request.addHeader("x-migutv-token", "");

        HttpUrl.Builder newUrlBuilder = oldRequest.url().newBuilder();

        // 所有网络请求带上渠道名称
        newUrlBuilder.addQueryParameter("channel", "C002");

        String versionName = "3.4.18";
        if (!TextUtils.isEmpty(versionName)) {
            // 所有网络请求带上应用版本号
            newUrlBuilder.addQueryParameter("appVersion", versionName);
        }

        int versionCode = 133;
        if (versionCode != 0) {
            // 所有网络请求带上应用版本号
            newUrlBuilder.addQueryParameter("appVersionCode", versionCode + "");
        }
        /*AESUtil util = new AESUtil();
        String deviceId = util.encrypt(MgVideoUtil.getIEMI(MgApplication.Instance));
        newUrlBuilder.addQueryParameter("deviceid", deviceId);

        newUrlBuilder.addQueryParameter("imei", MgVideoUtil.getIEMI(MgApplication.Instance));*/

        request.url(newUrlBuilder.build());

        request.addHeader("userId", "1092879465");
        request.addHeader("userToken", "A298330ED2FCF2908662");
        request.addHeader("SDKCEId","0c711746-66ec-455d-9608-6178ec176fd7");
        request.addHeader("sourceId", "203007");
        request.addHeader("appType", "3");

        return request.build();
    }
}
