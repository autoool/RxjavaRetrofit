package com.techidea.domain.interactor;

import com.techidea.domain.respository.DataRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by zchao on 2016/5/13.
 */
public class InitProductCategory extends RxBaseCase {

    private String deviceId;
    private String deviceType;
    private final DataRepository mDataRepository;

    @Inject
    public InitProductCategory(DataRepository dataRepository) {
        this.mDataRepository = dataRepository;
    }

    @Override
    public RxBaseCase initParams(String... paras) {
        deviceId = paras[0];
        deviceType = paras[1];
        return this;
    }

    @Override
    protected Observable buildCaseObservable() {
        return this.mDataRepository.initProductCategory(this.deviceId, this.deviceType);
    }

}
