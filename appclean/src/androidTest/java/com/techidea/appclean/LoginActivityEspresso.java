package com.techidea.appclean;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.techidea.appclean.login.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

/**
 * Created by zchao on 2016/7/18.
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityEspresso {

    //    @Rule
//    public ActivityTestRule<LoginActivity>  mActivityTestRule
//            = new ActivityTestRule<LoginActivity>(LoginActivity.class,true,false);
    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule
            = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    @Before
    public void setup() {
        Intent startIntent = new Intent();
        mActivityTestRule.launchActivity(startIntent);
    }

    @Test
    public void testLogin() {
        login();
        //登陆完成以后，主页面的控件肯定显示
        onView(withId(R.id.button_member)).check(matches(isDisplayed()));
    }

    private void login() {
        onView(withId(R.id.edittext_username))
                .perform(typeText("chao01"), closeSoftKeyboard());
        onView(withId(R.id.edittext_password))
                .perform(typeText("111111"));
        onView(withId(R.id.button_login)).perform(click());
    }
}
