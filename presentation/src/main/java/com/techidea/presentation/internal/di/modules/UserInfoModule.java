package com.techidea.presentation.internal.di.modules;

import com.techidea.domain.executor.PostExecutionThread;
import com.techidea.domain.executor.ThreadExecutor;
import com.techidea.domain.interactor.InitLoginUser;
import com.techidea.domain.interactor.Login;
import com.techidea.domain.interactor.RxBaseCase;
import com.techidea.domain.respository.UserInfoRepository;
import com.techidea.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zchao on 2016/5/6.
 * 提供跟用户相关的实例。基于我们的例子，它可以提供用户用例
 */
@Module
public class UserInfoModule {

    private String deviceId;
    private String deviceType;

    private String username;
    private String password;

    public UserInfoModule() {
    }

    public UserInfoModule(String deviceId, String deviceType) {
        this.deviceId = deviceId;
        this.deviceType = deviceType;
    }

    public UserInfoModule(String deviceId, String username, String password) {
        this.deviceId = deviceId;
        this.username = username;
        this.password = password;
    }

    @Provides
    @PerActivity
    @Named("userInfoList")
    RxBaseCase provideGetUserInfoListCase(UserInfoRepository userInfoRepository,
                                          ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
//        return new InitLoginUser(userInfoRepository);
        return null;
    }

    @Provides
    @PerActivity
    @Named("login")
    RxBaseCase provideLoginCase(UserInfoRepository userInfoRepository,
                                ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
//        return new Login(userInfoRepository);
        return null;
    }


}
