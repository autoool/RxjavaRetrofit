package com.techidea.data.net;

import android.text.TextUtils;

import com.techidea.data.BuildConfig;
import com.techidea.domain.entity.CityInfo;
import com.techidea.domain.entity.CityItem;
import com.techidea.domain.entity.HFCityInfo;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
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
    private Retrofit retrofitBaidu;
    private Retrofit retrofitHF;
    private Retrofit retrofitHttps;
    private ApiService service;
    private ApiService serviceHttps;
    private HefApiService mHefApiService;

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

        retrofitHF = new Retrofit.Builder()
                .client(getClientHF())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        mHefApiService = retrofitHF.create(HefApiService.class);

       /* retrofitHttps = new Retrofit.Builder()
                .client(getHttpsClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL_HTTPS)
                .build();*/

        service = retrofit.create(ApiService.class);
//        serviceHttps = retrofitHttps.create(HttpApi.class);

    }

    public Observable<List<CityItem>> getCityList(String cityname) {
        return null;
    }


    public Observable<List<CityInfo>> getSearchCityInfo(String citytype, String key) {
        return mHefApiService.getSearchCityInfo(citytype, key)
                .compose(RxUtils.<HFCityInfo>rxSchedulerHelper())
                .map(new Func1<HFCityInfo, List<CityInfo>>() {
                         @Override
                         public List<CityInfo> call(HFCityInfo result) {
                             if (result != null) {
                                 return result.getCity_info();
                             }
                             return null;
                         }
                     }
                );
    }


    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     * @return list
     */
//    private class HttpResultFuncList<T> implements Func1<HttpResult<T>, T> {
//
//        @Override
//        public T call(HttpResult<T> httpResult) {
//            if (httpResult.getCode() != 1)
//                throw new HttpErrorException(httpResult.getCode(), httpResult.getMsg());
//            return httpResult.getList();
//        }
//    }

    /**
     * @param <T>
     * @return object
     */
//    private class HttpResultFuncObject<T> implements Func1<HttpResult<T>, T> {
//
//        @Override
//        public T call(HttpResult<T> httpResult) {
//            if (httpResult.getCode() != 1) {
//                throw new HttpErrorException(httpResult.getCode(), httpResult.getMsg());
//            }
//            return httpResult.getObject();
//        }
//    }

    private class BaiduapiFunction<T> implements Func1<BaiduResponse<T>, T> {
        @Override
        public T call(BaiduResponse<T> tBaiduResponse) {
            if (tBaiduResponse.getErrNum() != 0) {
                throw new HttpErrorException(tBaiduResponse.getErrNum(), tBaiduResponse.getErrMsg());
            }
            return tBaiduResponse.getRetData();
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

    Interceptor apikeyInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request newRequest;
            newRequest = request.newBuilder()
                    .addHeader("apikey", "")
                    .build();
            return chain.proceed(newRequest);

        }
    };

    /*可以使用 Authenticator，这是一个专门设计用于当验证出现错误的时候，
    进行询问获取处理的拦截器：在http头部增加内容*/
    Authenticator mAuthenticator = new Authenticator() {
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
            return response.request().newBuilder()
                    .header("apikey", "")
                    .build();
        }
    };

    private OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .retryOnConnectionFailure(false)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(mTokenInterceptor)
                .build();

    }

    private OkHttpClient getClientBaidu() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .retryOnConnectionFailure(false)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(apikeyInterceptor)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    private OkHttpClient getClientHF() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .retryOnConnectionFailure(false)
                .connectTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    public void setToken(String token) {
        this.token = token;
    }


        private OkHttpClient getHttpsClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .sslSocketFactory(SslSocketFactory.getInstance().getSSLSocketFactory(),null)
                .build();
    }
}
