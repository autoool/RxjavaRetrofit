package com.techidea.domain.interactor;

import com.techidea.domain.respository.DataRepository;

import rx.Observable;

/**
 * Created by zchao on 2016/5/5.
 */
public class InitLoginUser extends RxBaseCase {

    private String deviceId;
    private String deviceType;
    private final DataRepository mDataRepository;

    public InitLoginUser(DataRepository dataRepository) {
        this.mDataRepository = dataRepository;
    }

    @Override
    public InitLoginUser initParams(String... paras) {
        this.deviceId = paras[0];
        this.deviceType = paras[1];
        return this;
    }

    @Override
    protected Observable buildCaseObservable() {
        return this.mDataRepository.initUserInfo(deviceId, deviceType);
    }
}
