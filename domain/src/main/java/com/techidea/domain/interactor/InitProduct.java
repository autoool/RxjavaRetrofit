package com.techidea.domain.interactor;

import com.techidea.domain.respository.DataRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by zchao on 2016/5/12.
 */
public class InitProduct extends RxBaseCase {

    private String deviceId;
    private String deviceType;
    private DataRepository mDataRepository;

    @Inject
    public InitProduct(DataRepository dataRepository) {
        this.mDataRepository = dataRepository;
    }

    @Override
    public RxBaseCase initParams(String... paras) {
        this.deviceId = paras[0];
        this.deviceType = paras[1];
        return this;
    }

    @Override
    protected Observable buildCaseObservable() {
        return this.mDataRepository.initProduct(deviceId, deviceType);
    }
}
