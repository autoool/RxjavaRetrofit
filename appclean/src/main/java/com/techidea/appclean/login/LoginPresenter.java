package com.techidea.appclean.login;

import com.techidea.corelibrary.util.CommonUtilAPP;
import com.techidea.data.net.HttpMethods;
import com.techidea.domain.entity.LoginUser;
import com.techidea.domain.interactor.DefaultSubscriber;
import com.techidea.domain.interactor.Login;

/**
 * Created by zchao on 2016/5/20.
 */
public class LoginPresenter implements LoginContract.Precenter {

    private LoginContract.View mView;
    private final Login mLogin;
    private LoginUser mLoginUser;

    public LoginPresenter(LoginContract.View view, Login login) {
        mView = view;
        mLogin = login;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        //获取用户名
    }

    @Override
    public void login(String username, String password) {
        if (checkUserInput(username, password)) {
            mLogin.initParams(CommonUtilAPP.getMacAddress(mView.getApplicationContext()),
                    username, password)
                    .execute(new LoginSubscriber());
        } else {
            mView.showError("用户名或密码输入有误");
        }
    }

    private boolean checkUserInput(String username, String password) {
        return true;
    }

    private final class LoginSubscriber extends DefaultSubscriber<LoginUser> {
        @Override
        public void onCompleted() {
            if (mLoginUser != null)
                HttpMethods.getInstance().setToken(mLoginUser.getToken());
            mView.loginSuccess();

        }

        @Override
        public void onError(Throwable e) {
            mView.showError(e.getMessage());
        }

        @Override
        public void onNext(LoginUser loginUser) {
            //保存当前登录用户
            mLoginUser = loginUser;
        }
    }

    ;
}
