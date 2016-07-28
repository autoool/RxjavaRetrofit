package com.techidea.domain.interactor;

import com.techidea.domain.entity.UserInfo;
import com.techidea.domain.respository.DataRepositoryDomain;

import java.util.List;

import rx.Observable;

/**
 * Created by zchao on 2016/5/5.
 */
public class InitLoginUser extends RxBaseCase<List<UserInfo>> {

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
    public Observable<List<UserInfo>> execute() {
        return this.mDataRepository.initUserInfo(deviceId, deviceType);
    }
}
