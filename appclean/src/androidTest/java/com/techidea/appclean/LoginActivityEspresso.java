package com.techidea.appclean;

import android.content.Intent;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.techidea.appclean.login.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by zchao on 2016/7/18.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityEspresso {

    private String pwd;

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule
            = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setup() {
        Intent intent = new Intent();
        mActivityTestRule.launchActivity(intent);
        pwd = "111111";
    }

    @Test
    public void testLogin() {
        onView(withId(R.id.spinner_name)).check(matches(isDisplayed()));
//        onView(withId(R.id.spinner_name)).perform(click());
//        onData(allOf(is(instanceOf(String.class)), is("chao01"))).perform(click());
//        onView(withId(R.id.spinner_name))
//                .check(matches(withText(containsString("chao01"))));
//        onView(withId(R.id.edittext_password)).check(matches(isDisplayed()));
//        onView(withId(R.id.edittext_password)).perform(typeText("111111"),closeSoftKeyboard());
//        onView(withId(R.id.edittext_password)).check(matches(withText("111111")));
//        onView(withId(R.id.button_login)).perform(click());
//
    }

}
