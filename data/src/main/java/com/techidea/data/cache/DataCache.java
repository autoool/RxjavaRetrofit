package com.techidea.data.cache;

/**
 * Created by zchao on 2016/7/6.
 */
public interface DataCache {


//    Observable<List<CityItem>> getCityList();
//
//    void putCityList(List<CityItem> list);

    boolean isCached(String filename);

    boolean isExpired(String filename);

    void evictAll();
}
