package com.techidea.domain.interactor;

import com.techidea.domain.respository.DataRepositoryDomain;

import rx.Observable;

/**
 * Created by zchao on 2016/5/13.
 */
public class Login extends RxBaseCase {

    private String deviceId;
    private String username;
    private String password;

    private final DataRepositoryDomain mDataRepository;

    public Login(DataRepositoryDomain dataRepository) {
        this.mDataRepository = dataRepository;
    }


    @Override
    public Login initParams(String... paras) {
        this.deviceId = paras[0];
        this.username = paras[1];
        this.password = paras[2];
        return this;
    }

    @Override
    protected Observable buildCaseObservable() {
        return this.mDataRepository.login(deviceId, username, password);
    }
}
