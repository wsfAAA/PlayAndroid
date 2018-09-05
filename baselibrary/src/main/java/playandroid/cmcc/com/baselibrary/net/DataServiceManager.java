package playandroid.cmcc.com.baselibrary.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import playandroid.cmcc.com.baselibrary.base.jadapter.DoubleDefault0Adapter;
import playandroid.cmcc.com.baselibrary.base.jadapter.IntegerDefault0Adapter;
import playandroid.cmcc.com.baselibrary.base.jadapter.LongDefault0Adapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class DataServiceManager {

    public static <T> T getServiceAPI(String endpoint, final Class<T> service) {
        OkHttpClient okHttpClient = MiguClient.getUnsafeOkHttpClient();
        StringBuffer buffer = new StringBuffer();
        buffer.append(endpoint).append("/");
        final Gson customGsonInstance = new GsonBuilder().registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                .registerTypeAdapter(long.class, new LongDefault0Adapter())
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(buffer.toString())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(customGsonInstance))
                .client(okHttpClient)//自定义OkHttpClient
                .build();
        return retrofit.create(service);
    }

    /**
     * 可设置 读取请求超时
     * @param endpoint
     * @param service
     * @param connectTimeOut
     * @param readTimeOut
     * @param <T>
     * @return
     */
    public static <T> T getServiceAPI(String endpoint, final Class<T> service,int connectTimeOut,int readTimeOut) {
        OkHttpClient okHttpClient = MiguClient.getUnsafeOkHttpClient(connectTimeOut,readTimeOut);
        StringBuffer buffer = new StringBuffer();
        buffer.append(endpoint).append("/");
        final Gson customGsonInstance = new GsonBuilder().registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                .registerTypeAdapter(long.class, new LongDefault0Adapter())
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(buffer.toString())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(customGsonInstance))
                .client(okHttpClient)
                .build();

        return retrofit.create(service);
    }

//    public static <T> T getSafeServiceAPI(String endpoint, final Class<T> service) {
//        OkHttpClient okHttpClient = MiguClient.getSafeOkHttpClient();
//        StringBuffer buffer = new StringBuffer();
//        buffer.append(endpoint).append("/");
//        final Gson customGsonInstance = new GsonBuilder().registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
//                .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
//                .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
//                .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
//                .registerTypeAdapter(Long.class, new LongDefault0Adapter())
//                .registerTypeAdapter(long.class, new LongDefault0Adapter())
//                .create();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(buffer.toString())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create(customGsonInstance))
//                .client(okHttpClient)
//                .build();
//
//        return retrofit.create(service);
//    }


//    public static <T> T getSafeServiceAPI(String endpoint, final Class<T> service,int outTime,int readTime) {
//        OkHttpClient okHttpClient = MiguClient.getSafeOkHttpClient(outTime,readTime);
//        StringBuffer buffer = new StringBuffer();
//        buffer.append(endpoint).append("/");
//        final Gson customGsonInstance = new GsonBuilder().registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
//                .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
//                .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
//                .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
//                .registerTypeAdapter(Long.class, new LongDefault0Adapter())
//                .registerTypeAdapter(long.class, new LongDefault0Adapter())
//                .create();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(buffer.toString())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create(customGsonInstance))
//                .client(okHttpClient)
//                .build();
//
//        return retrofit.create(service);
//    }

}
