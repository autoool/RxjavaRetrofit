package com.techidea.data.net;

import com.techidea.domain.entity.CityEntity;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by zchao on 2016/7/19.
 */
public interface BaiduApiService {

    @FormUrlEncoded
    @GET("citylist?")
    Observable<BaiduResponse<List<CityItem>>> getCityList(@Field("cityname") String cityname);
}
