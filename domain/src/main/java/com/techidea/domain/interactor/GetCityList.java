package com.techidea.domain.interactor;

import com.techidea.domain.respository.DataRepositoryDomain;

import rx.Observable;

/**
 * Created by zchao on 2016/7/19.
 */
public class GetCityList extends RxBaseCase {

    private String cityname;
    private DataRepositoryDomain mDataRepositoryDomain;

    public GetCityList(DataRepositoryDomain dataRepositoryDomain) {
        this.mDataRepositoryDomain = dataRepositoryDomain;
    }

    @Override
    public RxBaseCase initParams(String... paras) {
        this.cityname = paras[0];
        return this;
    }

    @Override
    public Observable buildCaseObservable() {
        return mDataRepositoryDomain.getCityList(cityname);
    }
}
