package com.techidea.domain.interactor;

import com.techidea.domain.entity.CityInfo;
import com.techidea.domain.respository.DataRepositoryDomain;

import java.util.List;

import rx.Observable;

/**
 * Created by zchao on 2016/7/27.
 */
public class GetSearchCityInfo extends RxBaseCase<List<CityInfo>> {

    private String citytype;
    private String key;
    private DataRepositoryDomain mDataRepositoryDomain;

    public GetSearchCityInfo(DataRepositoryDomain dataRepositoryDomain) {
        this.mDataRepositoryDomain = dataRepositoryDomain;
    }

    @Override
    public RxBaseCase initParams(String... paras) {
        this.citytype = paras[0];
        this.key = paras[1];
        return this;
    }

    @Override
    public Observable<List<CityInfo>> execute() {
        return mDataRepositoryDomain.getSearchCity(citytype, key);
    }
}
