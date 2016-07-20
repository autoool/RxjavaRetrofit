package com.techidea.data.net;

import com.techidea.domain.entity.CityItem;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zchao on 2016/7/19.
 */
public interface BaiduApiService {

    @GET("citylist")
    Observable<BaiduResponse<List<CityItem>>> getCityList(@Query("cityname") String cityname);
}
