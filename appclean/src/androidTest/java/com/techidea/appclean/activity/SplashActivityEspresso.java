package com.techidea.appclean.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.AppCompatActivity;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.ProgressBar;

import com.techidea.appclean.R;
import com.techidea.appclean.splash.SplashActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by zchao on 2016/7/22.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class SplashActivityEspresso {

    private AppCompatActivity mAppCompatActivity;
    private ProgressBar mProgressBar;

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule
            = new ActivityTestRule<SplashActivity>(SplashActivity.class);


    @Before
    public void setup(){
        mAppCompatActivity = mActivityTestRule.getActivity();
        mProgressBar = (ProgressBar) mAppCompatActivity.findViewById(R.id.progressbar_load);
    }

    @Test
    public void initDataSuccess() {
        onView(withId(R.id.textview_splash)).check(matches(isDisplayed()));
//        onView(withId(R.id.progressbar_load)).check(matches(isDisplayed()));
//        onView(withId(R.id.button_login)).check(matches(isDisplayed()));

    }
}
