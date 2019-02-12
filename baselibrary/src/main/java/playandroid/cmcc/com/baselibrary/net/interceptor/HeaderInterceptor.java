package playandroid.cmcc.com.baselibrary.net.interceptor;

import com.blankj.utilcode.util.SPUtils;

import java.io.IOException;
import java.util.Set;
import java.util.WeakHashMap;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加公共请求头 和 cookies
 */
public class HeaderInterceptor implements Interceptor {
    private WeakHashMap<String, String> mHeaders;
    public HeaderInterceptor(WeakHashMap<String, String> headers) {
        this.mHeaders = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request()
                .newBuilder();

        if (mHeaders != null && mHeaders.size() > 0) {
            // TODO: 2018/12/14  添加公共请求头
//            mHeaders.put("ee","gg");
            Set<String> keys = mHeaders.keySet();
            for (String headerKey : keys) {
                builder.addHeader(headerKey, mHeaders.get(headerKey)).build();
            }
        }

        /**
         * 检查是否存入本地cookie 有请求头添加cookie
         */
        Set<String> preferences = SPUtils.getInstance().getStringSet(AddCookiesInterceptor.COOKIES);
        if (preferences != null) {
            for (String cookie : preferences) {
                builder.addHeader("Cookie", cookie);
            }
        }

//        Log.i("wsf",  "Okhttp url:" + builder.build().url()+"  ,headers:  "+builder.build().headers());
        return chain.proceed(builder.build());

    }
}