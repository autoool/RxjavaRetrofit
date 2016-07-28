package com.techidea.appclean.activity;

import android.content.Intent;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.text.TextUtils;

import com.techidea.appclean.R;
import com.techidea.appclean.adapter.SpinnerItem;
import com.techidea.appclean.login.LoginActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
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

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule
            = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setup() {
        mActivityTestRule.getActivity();
    }

    @Test
    public void testEditPassword() {
        onView(withId(R.id.edittext_password)).check(matches(isDisplayed()));
        onView(withId(R.id.edittext_password)).perform(typeText("111111"), closeSoftKeyboard());
        onView(withId(R.id.edittext_password)).check(matches(withText("111111")));
    }

    @Test
    public void testLogin() {
        //测试spinner
        onView(ViewMatchers.withId(R.id.spinner_name)).check(matches(isDisplayed()));
        onView(withId(R.id.spinner_name)).perform(click());
        onData(allOf(is(instanceOf(SpinnerItem.class)), loginSpinnerMatcher("chao01"))).perform(click());
        onView(withId(R.id.edittext_password)).perform(typeText("111111"), closeSoftKeyboard());
        onView(withId(R.id.edittext_password)).check(matches(withText("111111")));
        onView(withId(R.id.button_login)).perform(click());
        onView(withId(R.id.toolbar_main)).check(matches(isDisplayed()));
    }


    public static Matcher<Object> loginSpinnerMatcher(final String name) {
        return new BoundedMatcher<Object, SpinnerItem>(SpinnerItem.class) {
            @Override
            protected boolean matchesSafely(SpinnerItem item) {
                return item != null
                        && !TextUtils.isEmpty(item.getName())
                        && item.getName().equals(name);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("SpinnerItem has Name: " + name);
            }
        };
    }

}
