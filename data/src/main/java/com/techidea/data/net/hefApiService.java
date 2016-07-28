package com.techidea.data.net;

import com.google.gson.JsonObject;
import com.techidea.domain.entity.CityInfo;
import com.techidea.domain.entity.CityItem;
import com.techidea.domain.entity.HFCityInfo;

import java.util.List;

import okhttp3.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zchao on 2016/7/19.
 */
public interface HefApiService {

    @GET("citylist")
    Observable<BaiduResponse<List<CityItem>>> getCityList(@Query("cityname") String cityname);

    @GET("citylist")
    Observable<HFCityInfo> getSearchCityInfo(@Query("search") String citytype, @Query("key") String key);
}
