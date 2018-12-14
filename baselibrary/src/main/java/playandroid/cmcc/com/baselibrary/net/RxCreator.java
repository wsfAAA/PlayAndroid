package playandroid.cmcc.com.baselibrary.net;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public final class RxCreator {
    public static final String BASE_URL = "http://wanandroid.com/";

    /**
     * 构建OkHttp
     */
    private static final class OKHttpHolder {
        private static final int CONNECT_TIME_OUT = 10;
        private static final int READ_TIME_OUT = 5;

        private static final OkHttpClient.Builder getOkhttpBuilder() {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);
            builder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);
            return builder;
        }
    }

    public static OkHttpClient.Builder getOkhttpBuilder() {
        return OKHttpHolder.getOkhttpBuilder();
    }


    /**
     * 构建全局Retrofit Builder
     */
    private static final class RetrofitHolder {
        public static final Retrofit.Builder RETROFIT_BUILDER = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    public static Retrofit.Builder getRetrofitBuilde() {
        return RetrofitHolder.RETROFIT_BUILDER;
    }
}
