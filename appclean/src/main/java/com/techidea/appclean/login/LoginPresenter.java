package com.techidea.appclean.login;

import com.techidea.appclean.adapter.SpinnerItem;
import com.techidea.corelibrary.util.CommonUtilAPP;
import com.techidea.domain.entity.LoginUser;
import com.techidea.domain.entity.UserInfo;
import com.techidea.domain.interactor.DefaultSubscriber;
import com.techidea.domain.interactor.InitLoginUser;
import com.techidea.domain.interactor.Login;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by zchao on 2016/5/20.
 */
public class LoginPresenter implements LoginContract.Precenter {

    private LoginContract.View mView;
    private final Login mLogin;
    private final InitLoginUser mInitLoginUser;
    private LoginUser mLoginUser;

    public LoginPresenter(LoginContract.View view, Login login, InitLoginUser initLoginUser) {
        mView = view;
        mLogin = login;
        this.mInitLoginUser = initLoginUser;
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

    @Override
    public void init() {
        mInitLoginUser.initParams(CommonUtilAPP.getMacAddress(mView.getApplicationContext()),
                CommonUtilAPP.getDeviceName()).execute(new InitLoginUserSubscriber());
    }

    private boolean checkUserInput(String username, String password) {
        return true;
    }

    private final class LoginSubscriber extends DefaultSubscriber<LoginUser> {
        @Override
        public void onCompleted() {
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

    private final class InitLoginUserSubscriber extends DefaultSubscriber<List<UserInfo>> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            mView.showError(e.getMessage());
        }

        @Override
        public void onNext(List<UserInfo> userInfoList) {
            List<SpinnerItem> list = new ArrayList<>();
            for (int i = 0; i < userInfoList.size(); i++) {
                list.add(new SpinnerItem(userInfoList.get(i).getUsername(), String.valueOf(i)));
            }
            mView.initLoginUsers(list);
        }
    }
}
