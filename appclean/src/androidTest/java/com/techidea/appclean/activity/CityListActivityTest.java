package com.techidea.appclean.activity;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.techidea.appclean.R;
import com.techidea.appclean.hefeng.CityListActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

/**
 * Created by zchao on 2016/7/20.
 * 测试 listview data adapter 之类的ui
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class CityListActivityTest {

    @Rule
    public ActivityTestRule<CityListActivity> mActivityTestRule
            = new ActivityTestRule<>(CityListActivity.class);

    @Before
    public void setup(){
        Intent startIntent = new Intent();
        mActivityTestRule.launchActivity(startIntent);
    }

    @Test
    public void testListDisplay(){
        onView(withId(R.id.recyclerview_citylist)).check(matches(isDisplayed()));

    }
}
