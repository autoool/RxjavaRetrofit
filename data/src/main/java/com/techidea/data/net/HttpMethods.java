package com.techidea.data.net;

import android.text.TextUtils;

import com.techidea.domain.entity.LoginUser;
import com.techidea.domain.entity.MemberInfoItem;
import com.techidea.domain.entity.Product;
import com.techidea.domain.entity.ProductCategory;
import com.techidea.domain.entity.UserInfo;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by zchao on 2016/5/5.
 */
public class HttpMethods {

    private static String BASE_URL = "https://kyfw.12306.cn";
    private static String BASE_URL_HTTPS = "https://kyfw.12306.cn/";
    private static final int DEFAULT_TIMEOUT = 5;
    private String token = "";

    private Retrofit retrofit;
    private Retrofit retrofitHttps;
    private ApiService service;
    private ApiService serviceHttps;

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

       /* retrofitHttps = new Retrofit.Builder()
                .client(getHttpsClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL_HTTPS)
                .build();*/

        service = retrofit.create(ApiService.class);
//        serviceHttps = retrofitHttps.create(HttpApi.class);
    }

    public Observable<List<UserInfo>> initLoginUsers(String deviceId, String deviceType) {
        return service.initLoginUsers(deviceId, deviceType)
                .compose(RxUtils.<HttpResult<List<UserInfo>>>rxSchedulerHelper())
                .map(new HttpResultFunc<List<UserInfo>>());
    }

    public Observable<List<ProductCategory>> initProductCategory(String deviceId, String deviceType) {
        return service.initProductCategory(deviceId, deviceType)
                .compose(RxUtils.<HttpResult<List<ProductCategory>>>rxSchedulerHelper())
                .map(new HttpResultFuncList<List<ProductCategory>>());
    }

    public Observable<List<Product>> initProduct(String deviceId, String deviceType) {
        return service.initProduct(deviceId, deviceType)
                .compose(RxUtils.<HttpResult<List<Product>>>rxSchedulerHelper())
                .map(new HttpResultFuncList<List<Product>>());
    }

    public Observable<LoginUser> login(String deviceId, String username, String password) {
        return service.login(deviceId, username, password)
                .compose(RxUtils.<HttpResult<LoginUser>>rxSchedulerHelper())
                .map(new HttpResultFunc<LoginUser>());
    }

    public Observable<MemberInfoItem> getMemberInfo(String qrcode, String type) {
        return service.getMemberInfo(qrcode, type)
                .compose(RxUtils.<HttpResult<MemberInfoItem>>rxSchedulerHelper())
                .map(new HttpResultFuncObject<MemberInfoItem>());
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
                throw new HttpErrorException(httpResult.getCode(), httpResult.getMsg());
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
                throw new HttpErrorException(httpResult.getCode(), httpResult.getMsg());
            }
            return httpResult.getObject();
        }
    }

    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getCode() != 1) {
                throw new HttpErrorException(httpResult.getCode(), httpResult.getMsg());
            }
            return httpResult.getData();
        }
    }

    /* 增加token  在form表单添加*/
    Interceptor mTokenInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request.Builder requestBuilder = originalRequest.newBuilder();
            boolean hasToken = false;
            if (originalRequest.body() instanceof FormBody) {
                FormBody.Builder newFormBody = new FormBody.Builder();
                FormBody oldFormBody = (FormBody) originalRequest.body();
                for (int i = 0; i < oldFormBody.size(); i++) {
                    newFormBody.addEncoded(oldFormBody.encodedName(i), oldFormBody.encodedValue(i));
                    if (oldFormBody.encodedName(i).equals("token"))
                        hasToken = true;
                }
                if (!hasToken && !TextUtils.isEmpty(token)) {
                    newFormBody.add("token", token);
                }
                requestBuilder.method(originalRequest.method(), newFormBody.build());
            }
            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    };

/*如果你需要在遇到诸如 401 Not Authorised 的时候进行刷新 token，
可以使用 Authenticator，这是一个专门设计用于当验证出现错误的时候，
进行询问获取处理的拦截器：在http头部增加内容*/
/*Authenticator mAuthenticator = new Authenticator() {
        @Override public VoiceInteractor.Request authenticate(Route route, Response response)
                throws IOException {
            Your.sToken = service.refreshToken();
            return response.request().newBuilder()
                    .addHeader("Authorization", Your.sToken)
                    .build();
        }
    };*/

    private OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .retryOnConnectionFailure(false)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(mTokenInterceptor)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

    }

    public void setToken(String token) {
        this.token = token;
    }


    //    private OkHttpClient getHttpsClient() {
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        if (BuildConfig.DEBUG) {
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        } else {
//            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
//        }
//        return new OkHttpClient.Builder()
//                .addInterceptor(logging)
//                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                .sslSocketFactory(SslSocketFactory.getInstance().getSSLSocketFactory())
//                .build();
//    }
}
