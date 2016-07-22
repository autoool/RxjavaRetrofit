package com.techidea.appclean.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.techidea.appclean.BuildConfig;
import com.techidea.appclean.R;
import com.techidea.appclean.splash.SplashActivity;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by zchao on 2016/7/21.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class SpalshActivityRobolectric {

    private AppCompatActivity mAppCompatActivity;
    private ProgressBar mProgressBar;

    @Before
    public void setup() {
        mAppCompatActivity = Robolectric.setupActivity(SplashActivity.class);
        mProgressBar = (ProgressBar) mAppCompatActivity.findViewById(R.id.progressbar_load);
    }

    @Test
    public void initSuccess() {
        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertThat("Next activity has start", application.getNextStartedActivity(), is(notNullValue()));
    }
}
