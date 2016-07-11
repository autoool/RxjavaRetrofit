package com.techidea.domain.interactor;

import com.techidea.domain.respository.DataRepositoryDomain;

import rx.Observable;

/**
 * Created by zchao on 2016/5/5.
 */
public class InitLoginUser extends RxBaseCase {

    private String deviceId;
    private String deviceType;
    private final DataRepositoryDomain mDataRepository;

    public InitLoginUser(DataRepositoryDomain dataRepository) {
        this.mDataRepository = dataRepository;
    }

    @Override
    public InitLoginUser initParams(String... paras) {
        this.deviceId = paras[0];
        this.deviceType = paras[1];
        return this;
    }

    @Override
    public Observable buildCaseObservable() {
        return this.mDataRepository.initUserInfo(deviceId, deviceType);
    }
}
