package playandroid.cmcc.com.baselibrary.mgnet;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import playandroid.cmcc.com.baselibrary.BuildConfig;
import playandroid.cmcc.com.baselibrary.mgnet.interceptor.ResponseInterceptor;

/**
 * Created by caominyan on 2018/4/4.
 */

public class MiguClient {

    private static final int CONNECT_TIMEOUT_MILLIS = 10000;
    private static final int READ_TIMEOUT_MILLIS = 5000;

    private static boolean mOpenNetworkLog = false;

    private static OkHttpClient mOkHttpClient;

    public static void init() {
        getUnsafeOkHttpClient();
    }

    public static OkHttpClient getUnsafeOkHttpClient() {
        if (mOkHttpClient == null) {
            try {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();

                //缓存
//                int cacheSize = 10 * 1024 * 1024;
//                Cache cache = new Cache(MgApplication.Instance.getCacheDir(), cacheSize);
//                builder.cache(cache);

                builder.connectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
                builder.readTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);

                //builder.addInterceptor(new CommonAppInterceptor()); //添加公共的 参数

                if (BuildConfig.DEBUG || mOpenNetworkLog) {
                    //todo 加入网络日志
//                    LogInterceptor httpLoggingInterceptor = new LogInterceptor();
//                    builder.addInterceptor(httpLoggingInterceptor);
                } else {
                    //todo 不加入网络日志
                }
                builder.addInterceptor(new ResponseInterceptor());
                OkHttpClient okHttpClient = builder.build();
                mOkHttpClient = okHttpClient;
                return mOkHttpClient;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return mOkHttpClient;
        }
    }

    public static OkHttpClient getUnsafeOkHttpClient(int connectTimeOut, int readTimeOut) {
        if (mOkHttpClient == null) {
            try {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();

//                int cacheSize = 10 * 1024 * 1024;
//                Cache cache = new Cache(MgApplication.Instance.getCacheDir(), cacheSize);
//                builder.cache(cache);
                builder.connectTimeout(connectTimeOut, TimeUnit.MILLISECONDS);
                builder.readTimeout(readTimeOut, TimeUnit.MILLISECONDS);

//                builder.addInterceptor(new CommonAppInterceptor());//添加公共的 参数

                if (BuildConfig.DEBUG || mOpenNetworkLog) {
                    //todo 加入网络日志
//                    LogInterceptor httpLoggingInterceptor = new LogInterceptor();
//                    builder.addInterceptor(httpLoggingInterceptor);
                } else {
                    //todo 不加入网络日志
                }
                builder.addInterceptor(new ResponseInterceptor());
                OkHttpClient okHttpClient = builder.build();
                mOkHttpClient = okHttpClient;
                return mOkHttpClient;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return mOkHttpClient;
        }
    }


//    /**
//     * 默认信任所有的证书
//     * TODO 最好加上证书认证，主流App都有自己的证书
//     *
//     * @return
//     */
//    private static SSLSocketFactory createSSLSocketFactory() {
//        SSLSocketFactory sSLSocketFactory = null;
//        try {
//            SSLContext sc = SSLContext.getInstance("TLS");
//            sc.init(null, new TrustManager[]{new TrustAllManager()},
//                    new SecureRandom());
//            sSLSocketFactory = sc.getSocketFactory();
//        } catch (Exception e) {
//        }
//        return sSLSocketFactory;
//    }
//
//    private static class TrustAllManager implements X509TrustManager {
//        @Override
//        public void checkClientTrusted(X509Certificate[] chain, String authType)
//                throws CertificateException {
//        }
//
//        @Override
//        public void checkServerTrusted(X509Certificate[] chain, String authType)
//                throws CertificateException {
//        }
//
//        @Override
//        public X509Certificate[] getAcceptedIssuers() {
//            return new X509Certificate[0];
//        }
//    }
//
//    private static class TrustAllHostnameVerifier implements HostnameVerifier {
//        @Override
//        public boolean verify(String hostname, SSLSession session) {
//            return true;
//        }
//    }

    /**
     * 设置okhttpclient 参数 不安全的请求
     *
     * @return
     */

//    /**
//     * 安全的请求 加http证书
//     * @return
//     */
//    public static OkHttpClient getSafeOkHttpClient() {
//            try {
//                OkHttpClient.Builder builder = new OkHttpClient.Builder();
//                builder.hostnameVerifier(new TrustAllHostnameVerifier());
//                int cacheSize = 10 * 1024 * 1024;
//                Cache cache = new Cache(MgApplication.Instance.getCacheDir(), cacheSize);
//                builder.cache(cache);
//                builder.connectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
//                builder.readTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
//
//                builder.addInterceptor(new CommonAppInterceptor());
//
//                if (BuildConfig.DEBUG || mOpenNetworkLog) {
//                    //todo 加入网络日志
//                    LogInterceptor httpLoggingInterceptor = new LogInterceptor();
//                    builder.addInterceptor(httpLoggingInterceptor);
//                } else {
//                    //todo 不加入网络日志
//                }
//                builder.addInterceptor(new ResponseInterceptor());
//                SSLSocketFactory sslSocketFactory = null;
//                MGLiveHttpsUtil.SSLParams sslParams = MGLiveHttpsUtil.getSslSocketFactory();
//                sslSocketFactory = sslParams.sSLSocketFactory;
//                builder.sslSocketFactory(sslSocketFactory)
//                        .hostnameVerifier(new HostnameVerifier() {
//                            @Override
//                            public boolean verify(String hostname, SSLSession session) {
//                                return true;
//                            }
//                        });
//                OkHttpClient okHttpClient = builder.build();
//                return okHttpClient;
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//    }

//    /**
//     * 安全的请求 加http证书
//     *
//     * @return
//     */
//    public static OkHttpClient getSafeOkHttpClient(int timeOut, int readTime) {
//        try {
//            OkHttpClient.Builder builder = new OkHttpClient.Builder();
//            builder.hostnameVerifier(new TrustAllHostnameVerifier());
//            int cacheSize = 10 * 1024 * 1024;
//            Cache cache = new Cache(MgApplication.Instance.getCacheDir(), cacheSize);
//            builder.cache(cache);
//            builder.connectTimeout(timeOut, TimeUnit.MILLISECONDS);
//            builder.readTimeout(readTime, TimeUnit.MILLISECONDS);
//
//            builder.addInterceptor(new CommonAppInterceptor());
//
//            if (BuildConfig.DEBUG || mOpenNetworkLog) {
//                //todo 加入网络日志
//                LogInterceptor httpLoggingInterceptor = new LogInterceptor();
//                builder.addInterceptor(httpLoggingInterceptor);
//            } else {
//                //todo 不加入网络日志
//            }
//            builder.addInterceptor(new ResponseInterceptor());
//            SSLSocketFactory sslSocketFactory = null;
//            MGLiveHttpsUtil.SSLParams sslParams = MGLiveHttpsUtil.getSslSocketFactory();
//            sslSocketFactory = sslParams.sSLSocketFactory;
//            builder.sslSocketFactory(sslSocketFactory)
//                    .hostnameVerifier(new HostnameVerifier() {
//                        @Override
//                        public boolean verify(String hostname, SSLSession session) {
//                            return true;
//                        }
//                    });
//            OkHttpClient okHttpClient = builder.build();
//            return okHttpClient;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    /**
     * 设置okhttpclient 参数,设置读取、连接超时 不安全请求、无证书
     *
     * @param connectTimeOut
     * @param readTimeOut
     * @return
     */
}
