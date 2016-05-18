package com.techidea.domain.interactor;

import com.techidea.domain.executor.PostExecutionThread;
import com.techidea.domain.executor.ThreadExecutor;
import com.techidea.domain.respository.UserInfoRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by zchao on 2016/5/13.
 */
public class Login extends RxBaseCase {

    private String deviceId;
    private String username;
    private String password;

    private UserInfoRepository mUserInfoRepository;

    @Inject
    public Login(UserInfoRepository userInfoRepository) {
        mUserInfoRepository = userInfoRepository;
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
        return this.mUserInfoRepository.login(deviceId, username, password);
    }
}
