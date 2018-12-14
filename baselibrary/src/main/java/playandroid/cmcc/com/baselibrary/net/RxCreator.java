package playandroid.cmcc.com.baselibrary.net;


import android.util.Log;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import playandroid.cmcc.com.baselibrary.api.BaseApiService;
import playandroid.cmcc.com.baselibrary.base.BaseApplication;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public final class RxCreator {

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

    private static final class OKHttpCacheHolder {

        private static final Cache getCache() {
            File mHttpCacheDirectory = new File(BaseApplication.getApplication().getCacheDir(), "tamic_cache");
            try {
                Cache cache = new Cache(mHttpCacheDirectory, 10 * 1024 * 1024);
                return cache;
            } catch (Exception e) {
                throw new RuntimeException("Cache Exception!");
            }
        }
    }

    public static final Cache getCache() {
        return OKHttpCacheHolder.getCache();
    }


    /**
     * 构建全局Retrofit Builder
     */
    private static final class RetrofitHolder {
        public static final Retrofit.Builder RETROFIT_BUILDER = new Retrofit.Builder()
                .baseUrl(BaseApiService.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    public static Retrofit.Builder getRetrofitBuilde() {
        return RetrofitHolder.RETROFIT_BUILDER;
    }
}
