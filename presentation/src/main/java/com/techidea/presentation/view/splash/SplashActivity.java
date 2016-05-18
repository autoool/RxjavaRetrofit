package com.techidea.presentation.view.splash;

import android.os.Bundle;

import com.techidea.presentation.R;
import com.techidea.presentation.base.BaseActivity;
import com.techidea.presentation.internal.di.HasComponent;
import com.techidea.presentation.internal.di.components.DaggerFragmentComponent;
import com.techidea.presentation.internal.di.components.FragmentComponent;
import com.techidea.presentation.internal.di.modules.ProductModule;
import com.techidea.presentation.internal.di.modules.UserInfoModule;

public class SplashActivity extends BaseActivity
        implements HasComponent<FragmentComponent>,
        SplashFragment.SplashListener {

    private FragmentComponent mFragmentComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.framelayout_main, new SplashFragment());
        }

    }

    private void initializeInjector() {
        //初始化当前activity注入器
        this.mFragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .productModule(new ProductModule())
                .userInfoModule(new UserInfoModule())
                .build();
    }

    @Override
    public FragmentComponent getComponent() {
        return this.mFragmentComponent;
    }

    @Override
    public void jumpLogin() {
        this.mNavigator.navigateToLogin(this);
    }
}
