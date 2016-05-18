package com.techidea.rxjavademo.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.techidea.rxjavademo.interf.HttpRequestInterface;
import com.techidea.rxjavademo.itemadapter.Product;
import com.techidea.rxjavademo.itemadapter.ProductCategory;
import com.techidea.rxjavademo.model.HttpResult;
import com.techidea.rxjavademo.model.LoginUserInfo;
import com.techidea.rxjavademo.model.Repo;
import com.techidea.rxjavademo.model.UserInfo;

import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.TlsVersion;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zchao on 2016/3/24.
 */
public class HttpMethods {

    private static final String BASE_URL = "";
    private static final String BASE_URL_HTTPS = "https://kyfw.12306.cn/";
    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private Retrofit retrofitHttps;
    private HttpRequestInterface service;
    private HttpRequestInterface serviceHttps;

    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

//        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
//                .tlsVersions(TlsVersion.TLS_1_2)
//                .cipherSuites(
//                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
//                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
//                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
//                .build();
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectionSpecs(Collections.singletonList(spec))
//                .build();


        retrofit = new Retrofit.Builder()
                .client(CustomTrust.getInstance().getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        retrofitHttps = new Retrofit.Builder()
                .client(CustomTrust.getInstance().getClientHttps())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL_HTTPS)
                .build();

        service = retrofit.create(HttpRequestInterface.class);
        serviceHttps = retrofitHttps.create(HttpRequestInterface.class);
    }

    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public Observable<Response<String>> testHttps() {
        return service.testHttps()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    //RxJava还为我们提供了subscribeOn()和observeOn()两个方法来指定Observable和Observer运行的线程。
    public void listRepos(Subscriber<List<Repo>> subscriber, String account) {
        service.listRepos(account)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public Observable<HttpResult<List<UserInfo>>> initLoginUsers(String deviceId, String deviceType) {
        return service.initLoginUsers(deviceId, deviceType)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }


    public Observable<HttpResult<List<ProductCategory>>> initProductCategories(String deviceId, String deviceType) {
        return service.initProductCategories(deviceId, deviceType)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public Observable<HttpResult<List<Product>>> initProducts(String deviceId) {
        return service.initProducts(deviceId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Response<HttpResult<LoginUserInfo>>> doLogin(String deviceId, String username, String password) {
        return service.doLogin(deviceId, username, password)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void initProducts(Subscriber<List<Product>> subscriber, String deviceId) {
        service.initProducts(deviceId)
                .map(new HttpResultFuncList<List<Product>>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
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

}
