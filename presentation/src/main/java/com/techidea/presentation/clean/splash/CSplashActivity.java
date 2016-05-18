package com.techidea.presentation.clean.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.techidea.presentation.R;
import com.techidea.presentation.clean.Injection;
import com.techidea.presentation.clean.base.CBaseActivity;

/**
 * Created by zchao on 2016/5/18.
 */
public class CSplashActivity extends CBaseActivity {

    private CSplashPresenter mCSplashPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        CSplashFragment cSplashFragment =
                (CSplashFragment) getSupportFragmentManager().findFragmentById(R.id.framelayout_main);
        if (cSplashFragment == null) {
            cSplashFragment = CSplashFragment.newInstance();
            addFragment(R.id.framelayout_main, cSplashFragment);
        }

        mCSplashPresenter = new CSplashPresenter(
                cSplashFragment,
                Injection.provideInitProduct(),
                Injection.provideInitProductCategory(),
                Injection.provideInitLoginUser()
        );

    }
}
