package com.techidea.data.net;

import com.techidea.data.entity.ProductCategoryEntity;
import com.techidea.data.entity.ProductEntity;
import com.techidea.data.entity.UserInfoEntity;
import com.techidea.domain.LoginUser;
import com.techidea.domain.Product;
import com.techidea.domain.ProductCategory;
import com.techidea.domain.UserInfo;
import com.techidea.domain.interactor.Login;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by zchao on 2016/5/5.
 */
public interface HttpApi {

    @FormUrlEncoded
    @POST("initLoginUsers.do")
    Observable<HttpResult<List<UserInfo>>> initLoginUsers(
            @Field("deviceId") String deviceId,
            @Field("deviceType") String deviceType);

    @FormUrlEncoded
    @POST("getProductCategories.do")
    Observable<HttpResult<List<ProductCategory>>> initProductCategory(
            @Field("deviceId") String deviceId,
            @Field("deviceType") String deviceType);

    @FormUrlEncoded
    @POST("getProducts.do")
    Observable<HttpResult<List<Product>>> initProduct(
            @Field("deviceId") String deviceId,
            @Field("deviceType") String deviceType);

    @FormUrlEncoded
    @POST("login.do")
    Observable<HttpResult<LoginUser>> login(
        @Field("deviceId") String deviceId,
        @Field("username") String username,
        @Field("password") String password
    );

}
