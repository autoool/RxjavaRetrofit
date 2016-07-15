package com.techidea.appclean.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.techidea.appclean.base.Injection;
import com.techidea.appclean.R;
import com.techidea.appclean.base.BaseActivity;
import com.techidea.corelibrary.CommonUtilAPP;

/**
 * Created by zchao on 2016/5/18.
 */
public class SplashActivity extends BaseActivity {

    private SplashPresenter mCSplashPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SplashFragment splashFragment =
                (SplashFragment) getSupportFragmentManager().findFragmentById(R.id.framelayout_main);
        if (splashFragment == null) {
            splashFragment = SplashFragment.newInstance();
            addFragment(R.id.framelayout_main, splashFragment);
        }

        mCSplashPresenter = new SplashPresenter(
                splashFragment,
                Injection.provideInitProduct(getApplicationContext()),
                Injection.provideInitProductCategory(getApplicationContext()),
                Injection.provideInitLoginUser(getApplicationContext())
        );
        Log.d("Ser", CommonUtilAPP.getDeviceSerial());
    }
}
