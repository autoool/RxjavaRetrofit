package com.techidea.domain.respository;

import com.techidea.domain.entity.CityInfo;
import com.techidea.domain.entity.CityItem;

import java.io.File;
import java.util.List;

import rx.Observable;

/**
 * Created by zchao on 2016/5/19.
 */
public interface DataRepositoryDomain {

    Observable<List<CityItem>> getCityList(String cityname);

    Observable<List<CityInfo>> getSearchCity(String citytype, String key);

    Observable<String> uploadFileWidthParam(String key, File file);
}
