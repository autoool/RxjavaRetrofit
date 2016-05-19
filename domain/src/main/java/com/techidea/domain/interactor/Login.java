package com.techidea.domain.interactor;

import com.techidea.domain.respository.DataRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by zchao on 2016/5/13.
 */
public class Login extends RxBaseCase {

    private String deviceId;
    private String username;
    private String password;

    private final DataRepository mDataRepository;

    @Inject
    public Login(DataRepository dataRepository) {
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
