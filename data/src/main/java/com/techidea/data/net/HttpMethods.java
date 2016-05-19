package com.techidea.data.net;

import android.text.TextUtils;
import android.util.Log;

import com.techidea.data.BuildConfig;
import com.techidea.domain.entity.LoginUser;
import com.techidea.domain.entity.Product;
import com.techidea.domain.entity.ProductCategory;
import com.techidea.domain.entity.UserInfo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zchao on 2016/5/5.
 */
public class HttpMethods {

    private static String BASE_URL = "";
    private static String BASE_URL_HTTPS = "https://kyfw.12306.cn/";
    private static final int DEFAULT_TIMEOUT = 5;


    private Retrofit retrofit;
    private Retrofit retrofitHttps;
    private HttpApi service;
    private HttpApi serviceHttps;

    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }

    public static void setBaseUrlHttps(String baseUrlHttps) {
        BASE_URL_HTTPS = baseUrlHttps;
    }

    private HttpMethods() {

        retrofit = new Retrofit.Builder()
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

//        retrofitHttps = new Retrofit.Builder()
//                .client(CustomTrust.getInstance().getClientHttps())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .baseUrl(BASE_URL_HTTPS)
//                .build();

        service = retrofit.create(HttpApi.class);
//        serviceHttps = retrofitHttps.create(HttpApi.class);
    }

    public Observable<List<UserInfo>> initLoginUsers(String deviceId, String deviceType) {
        return service.initLoginUsers(deviceId, deviceType)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new HttpResultFuncList<List<UserInfo>>());
    }

    public Observable<List<ProductCategory>> initProductCategory(String deviceId, String deviceType) {
        return service.initProductCategory(deviceId, deviceType)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new HttpResultFuncList<List<ProductCategory>>());
    }

    public Observable<List<Product>> initProduct(String deviceId, String deviceType) {
        return service.initProduct(deviceId, deviceType)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new HttpResultFuncList<List<Product>>());
    }

    public Observable<LoginUser> login(String deviceId, String username, String password) {
        return service.login(deviceId, username, password)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new HttpResultFuncObject<LoginUser>());
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     * @return list
     */
    private class HttpResultFuncList<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getCode() != 1)
                if (!TextUtils.isEmpty(httpResult.getMsg())) {
                    throw new HttpErrorException(httpResult.getMsg());
                } else {
                    throw new HttpErrorException(httpResult.getMsg());
                }
            return httpResult.getList();
        }
    }

    /**
     * @param <T>
     * @return object
     */
    private class HttpResultFuncObject<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getCode() != 1) {
                if (!TextUtils.isEmpty(httpResult.getMsg())) {
                    throw new HttpErrorException(httpResult.getMsg());
                } else {
                    throw new HttpErrorException(httpResult.getMsg());
                }
            }
            return httpResult.getObject();
        }
    }

    private OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

    }
}
