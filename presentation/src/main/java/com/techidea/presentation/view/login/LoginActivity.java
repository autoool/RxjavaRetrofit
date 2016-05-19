package com.techidea.presentation.view.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.Toast;

import com.techidea.corelibrary.util.CommonUtilAPP;
import com.techidea.presentation.R;
import com.techidea.presentation.base.BaseActivity;
import com.techidea.presentation.internal.di.HasComponent;
import com.techidea.presentation.internal.di.components.FragmentComponent;
import com.techidea.presentation.internal.di.modules.UserInfoModule;


/**
 * Created by zchao on 2016/5/5.
 */
public class LoginActivity extends BaseActivity
        implements HasComponent<FragmentComponent>,
        LoginFragment.LoginListener {

    private FragmentComponent mFragmentComponent;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.initializeInjector();
        if (savedInstanceState == null)
            addFragment(R.id.framelayout_login, new LoginFragment());
    }

    private void initializeInjector() {
    }

    @Override
    public FragmentComponent getComponent() {
        return this.mFragmentComponent;
    }


    @Override
    public void loginSuccess() {

    }
}
