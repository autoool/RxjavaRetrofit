package com.techidea.domain.interactor;

import com.techidea.domain.executor.PostExecutionThread;
import com.techidea.domain.executor.ThreadExecutor;
import com.techidea.domain.respository.UserInfoRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by zchao on 2016/5/5.
 */
public class InitLoginUser extends RxBaseCase {

    private String deviceId;
    private String deviceType;
    private final UserInfoRepository mUserInfoRepository;

    @Inject
    public InitLoginUser(UserInfoRepository userInfoRepository) {
        this.mUserInfoRepository = userInfoRepository;
    }

    @Override
    public InitLoginUser initParams(String... paras) {
        this.deviceId = paras[0];
        this.deviceType = paras[1];
        return this;
    }

    @Override
    protected Observable buildCaseObservable() {
        return this.mUserInfoRepository.initUserInfo(deviceId, deviceType);
    }
}
