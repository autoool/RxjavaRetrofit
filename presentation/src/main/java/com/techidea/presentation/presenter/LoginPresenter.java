package com.techidea.presentation.presenter;

import android.content.Context;
import android.util.Log;

import com.techidea.domain.entity.LoginUser;
import com.techidea.domain.interactor.DefaultSubscriber;
import com.techidea.domain.interactor.RxBaseCase;
import com.techidea.presentation.internal.di.PerActivity;
import com.techidea.presentation.view.LoginView;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by zchao on 2016/5/13.
 */
@PerActivity
public class LoginPresenter implements Presenter {

    private Context mContext;
    private RxBaseCase loginCase;
    private LoginView mLoginView;

    private String deviceId;
    private String username;
    private String password;

    @Inject
    public LoginPresenter(@Named("login") RxBaseCase loginCase) {
        this.loginCase = loginCase;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public void setLoginView(LoginView loginView) {
        this.mLoginView = loginView;
    }

    public void initialize() {
        this.login();
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void login() {
        if (loginCase != null)
            loginCase.initParams(deviceId, username, username)
                    .execute(new LoginSubscriber());
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destory() {

    }

    private final class LoginSubscriber extends DefaultSubscriber<LoginUser> {
        @Override
        public void onCompleted() {
            mLoginView.loginSuccess();
        }

        @Override
        public void onError(Throwable e) {
            mLoginView.showError(e.getMessage());
        }

        @Override
        public void onNext(LoginUser loginUser) {
            //保存数据到本地
            if (loginUser != null) {
                Log.d("loginuser ", loginUser.getUsername());
            }
        }
    }
}
