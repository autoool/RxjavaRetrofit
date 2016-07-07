package com.techidea.appclean.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

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

        mLoginPresenter = new LoginPresenter(
                loginFragment,
                Injection.provideLogin(getApplicationContext())
        );
    }
}
