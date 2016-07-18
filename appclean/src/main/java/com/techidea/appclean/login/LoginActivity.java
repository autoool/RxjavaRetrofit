package com.techidea.appclean.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.techidea.appclean.R;
import com.techidea.appclean.base.BaseActivity;
import com.techidea.appclean.base.Injection;

/**
 * Created by zchao on 2016/5/19.
 */
public class LoginActivity extends BaseActivity {

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager()
                .findFragmentById(R.id.framelayout_login);
        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();
            addFragment(R.id.framelayout_login, loginFragment);
        }

        //为什么这里的 presenter 可以在 fragment里面使用
        mLoginPresenter = new LoginPresenter(
                loginFragment,
                Injection.provideLogin(getApplicationContext()),
                Injection.provideInitLoginUser(getApplicationContext())
        );
    }

}
