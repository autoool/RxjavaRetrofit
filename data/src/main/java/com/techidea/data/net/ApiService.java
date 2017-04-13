package com.techidea.data.net;


import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * Created by zchao on 2016/5/5.
 */
public interface ApiService {


    @FormUrlEncoded
    @POST("/api/wxPayQuery.do")
    Observable<HttpResult<String>> wxPayQuery(
            @Field("posDeviceId") String posDeviceId,
            @Field("applicationId") String applicationId,
            @Field("orderNo") String orderNo,
            @Field("tradeNo") String tradeNo,
            @Field("subMchId") String subMchId
    );

    @Multipart
    @POST("upload")
    Observable<HttpResult<String>> uploadFile(
            @Part("photo") RequestBody photo,
            @Part("description") RequestBody description);

    //文件和參數一起上傳
    @Multipart
    @POST("/api/uploadFileWithParam.do")
    Observable<HttpResult<String>> uploadFileWithPartMap(
            @PartMap() Map<String, RequestBody> partMap,
            @Part MultipartBody.Part file);

    //多文件和參數一起上傳
    @Multipart
    @POST("upload")
    Observable<HttpResult<String>> uploadMultiFiles(
            @PartMap() Map<String, RequestBody> partMap,
            @Part MultipartBody.Part... file
    );

}


