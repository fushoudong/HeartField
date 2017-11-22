package com.bupt.heartarea.retrofit;

import android.content.Context;
import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetroFactory {
    private static final String BASE_URL = "http://v.j1uhe.cn/";

    private static Retrofit jsonRetrofit;

    private static Retrofit stringRetrofit;


    private static Dispatcher dispatcher;
    private static OkHttpClient okHttpClient;
    private static HttpLoggingInterceptor loggingInterceptor;

    /**
     * @param context    上下文
     * @param resourceId 如果是https请求，证书的资源id，需放到raw文件夹下
     */
    private static void init(Context context, int resourceId) {
        if (loggingInterceptor == null) {

            loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.d("headers", "OkHttp====Message:" + message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }

        if (dispatcher == null)
            dispatcher = new Dispatcher(Executors.newFixedThreadPool(20));//线程池
        dispatcher.setMaxRequests(20);//最大的请求数量
        dispatcher.setMaxRequestsPerHost(1);//主机同一个时间，最大的请求数量

        // https请求设置
        if (context != null && resourceId > 0) {
            SSLSocketFactory sslSocketFactory = new SslContextFactory().getSslSocket(context, resourceId).getSocketFactory();
            if (okHttpClient == null) {
                okHttpClient = new OkHttpClient.Builder()
//                    .dispatcher(dispatcher)
                        .connectionPool(new ConnectionPool(100, 30, TimeUnit.SECONDS))
                        .addInterceptor(loggingInterceptor)
                        .sslSocketFactory(sslSocketFactory)
                        .build();
            }
        }
        // 普通http请求
        else {
            if (okHttpClient == null)
                okHttpClient = new OkHttpClient.Builder()
//                    .dispatcher(dispatcher)
                        .connectionPool(new ConnectionPool(100, 30, TimeUnit.SECONDS))
                        .addInterceptor(loggingInterceptor)
                        .build();
        }
    }


    public static RetrofitService getJsonService() {
        stringRetrofit = buildGsonRetrofit(null, -1, BASE_URL);
        RetrofitService service = jsonRetrofit.create(RetrofitService.class);
        return service;
    }

    public static RetrofitService getJsonService(Context context, int resourceId) {
        stringRetrofit = buildGsonRetrofit(context, resourceId, BASE_URL);
        RetrofitService service = jsonRetrofit.create(RetrofitService.class);
        return service;
    }

    public static RetrofitService getStringService() {
        stringRetrofit = buildStringRetrofit(null, -1, BASE_URL);
        RetrofitService service = stringRetrofit.create(RetrofitService.class);
        return service;
    }

    public static RetrofitService getStringService(Context context, int resourceId) {
        stringRetrofit = buildStringRetrofit(context, resourceId, BASE_URL);
        RetrofitService service = stringRetrofit.create(RetrofitService.class);
        return service;
    }

    public static RetrofitService getStringService(Context context, int resourceId, String baseUrl) {
        stringRetrofit = buildStringRetrofit(context, resourceId, baseUrl);
        RetrofitService service = stringRetrofit.create(RetrofitService.class);
        return service;
    }

    public static RetrofitService getJsonService(String baseUrl) {
        jsonRetrofit = buildGsonRetrofit(null, -1, baseUrl);
        RetrofitService service = jsonRetrofit.create(RetrofitService.class);
        return service;
    }

    public static RetrofitService getJsonService(Context context, int resourceId, String baseUrl) {
        jsonRetrofit = buildGsonRetrofit(context, resourceId, baseUrl);
        RetrofitService service = jsonRetrofit.create(RetrofitService.class);
        return service;
    }


//    private static Retrofit buildGsonRetrofit(String baseUrl) {
//        init(null, -1);
//        return new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
//                .build();
//    }
//
//    private static Retrofit buildStringRetrofit(String baseUrl) {
//        init(null, -1);
//        return new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .client(okHttpClient)
//                .build();
//    }

    private static Retrofit buildGsonRetrofit(Context context, int resourceId, String baseUrl) {
        init(context, resourceId);
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    private static Retrofit buildStringRetrofit(Context context, int resourceId, String baseUrl) {
        init(context, resourceId);
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(okHttpClient)
                .build();
    }


}
