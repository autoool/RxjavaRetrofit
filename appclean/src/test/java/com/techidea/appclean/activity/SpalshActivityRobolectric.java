package com.techidea.appclean.activity;

import android.support.v7.app.AppCompatActivity;

import com.techidea.appclean.BuildConfig;
import com.techidea.appclean.splash.SplashActivity;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by zchao on 2016/7/21.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class SpalshActivityRobolectric {

    private AppCompatActivity mAppCompatActivity;

    @Before
    public void setup(){
        mAppCompatActivity = Robolectric.setupActivity(SplashActivity.class)
    }

}
