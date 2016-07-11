package com.techidea.domain.interactor;

import com.techidea.domain.respository.DataRepositoryDomain;

import rx.Observable;

/**
 * Created by zchao on 2016/5/13.
 */
public class InitProductCategory extends RxBaseCase {

    private String deviceId;
    private String deviceType;
    private final DataRepositoryDomain mDataRepository;

    public InitProductCategory(DataRepositoryDomain dataRepository) {
        this.mDataRepository = dataRepository;
    }

    @Override
    public RxBaseCase initParams(String... paras) {
        deviceId = paras[0];
        deviceType = paras[1];
        return this;
    }

    @Override
    public Observable buildCaseObservable() {
        return this.mDataRepository.initProductCategory(this.deviceId, this.deviceType);
    }

}
