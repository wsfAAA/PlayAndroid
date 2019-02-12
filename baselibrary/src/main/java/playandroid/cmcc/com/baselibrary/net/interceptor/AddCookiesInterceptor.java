package playandroid.cmcc.com.baselibrary.net.interceptor;

import android.content.SharedPreferences;
import android.util.Log;

import com.blankj.utilcode.util.SPUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by wsf on 2019/2/5.  添加cookies到本地
 */

public class AddCookiesInterceptor implements Interceptor {

    public static final String COOKIES = "COOKIES";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            Set<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

            SPUtils.getInstance().put(COOKIES, cookies);
        }

        return originalResponse;
    }
}
