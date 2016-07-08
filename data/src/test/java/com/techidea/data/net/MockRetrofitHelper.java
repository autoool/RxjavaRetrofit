package com.techidea.data.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zchao on 2016/7/8.
 */
public class MockRetrofitHelper {

    private String path;
    private String content;

    public <T> T create(Class<T> clazz) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new MockInterceptor())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.api")
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private class MockInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            ResponseBody body = ResponseBody.create(MediaType.parse("application/x-www-form-urlencoded"), content);
            Response response = new Response.Builder().request(chain.request())
                    .protocol(Protocol.HTTP_1_1)
                    .code(200)
                    .body(body)
                    .build();
            return response;
        }
    }
}
