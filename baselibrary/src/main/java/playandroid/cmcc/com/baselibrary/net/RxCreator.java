package playandroid.cmcc.com.baselibrary.net;


import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public final class RxCreator {
    public static final String BASE_URL = "http://wanandroid.com/";

    /**
     * 参数容器
     */
    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    /**
     * 构建OkHttp
     */
    private static final class OKHttpHolder {
        private static final int CONNECT_TIME_OUT = 10;
        private static final int READ_TIME_OUT = 5;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
//                .addInterceptor()  //添加拦截器  参考MiguClient getUnsafeOkHttpClient
                .build();
    }

    /**
     * 构建全局Retrofit客户端
     */
    private static final class RetrofitHolder {
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static final class RestServiceHolder {
        private static final RxService RX_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RxService.class);
    }

    public static RxService getRestService() {
        return RestServiceHolder.RX_SERVICE;
    }
}
