package com.techidea.data.repository.datasource;

import com.techidea.domain.entity.CityInfo;
import com.techidea.domain.entity.CityItem;

import java.util.List;

import rx.Observable;

/**
 * Created by zchao on 2016/5/19.
 */
public interface DataStore {


    Observable<List<CityItem>> getCityList(String cityname);

    Observable<List<CityInfo>> getSearchCityInfo(String citytype,String key);
}
