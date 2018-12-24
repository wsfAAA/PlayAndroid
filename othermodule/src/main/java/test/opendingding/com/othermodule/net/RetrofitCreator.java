package test.opendingding.com.othermodule.net;


import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by wsf on 2018/11/23.
 */

public class RetrofitCreator {
    public static final String BASE_URL = "http://wanandroid.com/";

    /**
     * 参数容器
     */
    public static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    public static RetrofitService getRestService() {
        return RestServiceHolder.REAST_SERVICE;
    }

    /**
     * 构建全局Retrofit客户端  静态内部类 单利模式
     */
    public static final class RetrofitHolder {
        private static final Retrofit RETROFIT_CLICENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())//添加 String类型  转换器
//                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .client(OkHttpClientHolder.OK_HTTP_CLIENT)
                .build();
    }

    /**
     * ApiService 实例
     */
    public static final class RestServiceHolder {
        private static final RetrofitService REAST_SERVICE = RetrofitHolder.RETROFIT_CLICENT.create(RetrofitService.class);
    }

    /**
     * OkHttpClient 相关配置
     */
    public static final class OkHttpClientHolder {
        private static final int CONNECT_TIME_OUT = 10;
        private static final int READ_TIME_OUT = 5;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
//                .addInterceptor()  //添加拦截器  参考MiguClient getUnsafeOkHttpClient
                .build();
    }

}
