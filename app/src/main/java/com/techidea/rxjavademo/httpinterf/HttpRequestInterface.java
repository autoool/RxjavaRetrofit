package com.techidea.rxjavademo.httpinterf;

import com.techidea.rxjavademo.itemadapter.Product;
import com.techidea.rxjavademo.itemadapter.ProductCategory;
import com.techidea.rxjavademo.model.HttpResult;
import com.techidea.rxjavademo.model.LoginUserInfo;
import com.techidea.rxjavademo.model.Repo;
import com.techidea.rxjavademo.model.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zchao on 2016/3/24.
 */
public interface HttpRequestInterface {

    @GET("otn/")
    Observable<Response<String>> testHttps();

    @GET("users/octocat/repos")
    Call<List<Repo>> getUserInfos();

    @FormUrlEncoded
    @POST("initLoginUsers.do")
    Call<HttpResult<List<UserInfo>>> callinitLoginUsers(
            @Field("deviceId") String deviceId,
            @Field("deviceType") String deviceType);


    @GET("users/{user}/repos")
    Observable<List<Repo>> listRepos(@Path("user") String user);

    @FormUrlEncoded
    @POST("initLoginUsers.do")
    Observable<HttpResult<List<UserInfo>>> initLoginUsers(
            @Field("deviceId") String deviceId,
            @Field("deviceType") String deviceType);


    @FormUrlEncoded
    @POST("getProductCategories.do")
    Observable<HttpResult<List<ProductCategory>>> initProductCategories(
            @Field("deviceId") String deviceId,
            @Field("deviceType") String deviceType
    );

    @FormUrlEncoded
    @POST("getProducts.do")
    Observable<HttpResult<List<Product>>> initProducts(
            @Field("deviceId") String deviceId
    );


    @FormUrlEncoded
    @POST("login.do")
    Observable<Response<HttpResult<LoginUserInfo>>> doLogin(
            @Field("deviceId") String deviceId,
            @Field("username") String username,
            @Field("password") String password);

}
