package com.techidea.data.repository;

import com.techidea.data.net.HttpMethods;

import com.techidea.domain.entity.LoginUser;
import com.techidea.domain.entity.UserInfo;
import com.techidea.domain.respository.UserInfoRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by zchao on 2016/5/5.
 */
@Singleton
public class UserInfoDataRepository implements UserInfoRepository {

    private static UserInfoDataRepository INSTANCE = null;

    @Inject
    public UserInfoDataRepository() {
    }

    public static UserInfoDataRepository getInstance() {
        if (INSTANCE == null)
            INSTANCE = new UserInfoDataRepository();
        return INSTANCE;
    }

    public static void destoryInstance() {
        INSTANCE = null;
    }

    @Override
    public Observable<List<UserInfo>> initUserInfo(String deviceId, String deviceType) {
        return HttpMethods.getInstance().initLoginUsers(deviceId, deviceType);
    }

    @Override
    public Observable<LoginUser> login(String deviceId, String username, String password) {
        return HttpMethods.getInstance().login(deviceId, username, password);
    }
}
