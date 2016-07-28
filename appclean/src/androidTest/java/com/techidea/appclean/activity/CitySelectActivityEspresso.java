package com.techidea.appclean.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.MenuItem;

import com.techidea.appclean.R;
import com.techidea.appclean.hefeng.CitySelectActivity;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
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
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by zchao on 2016/7/27.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class CitySelectActivityEspresso {

    private CitySelectActivity mActivity;

    @Rule
    public ActivityTestRule<CitySelectActivity> mActivityTestRule
            = new ActivityTestRule<>(CitySelectActivity.class);

    @Before
    public void setup() {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void getCityInfos() {
        openActionBarOverflowOrOptionsMenu(mActivity.getApplicationContext());
        onView(withText("国内城市")).perform(click());
    }

    MenuItemTitleMatcher withTitle(String title) {
        return new MenuItemTitleMatcher(title);
    }

    class MenuItemTitleMatcher extends BaseMatcher<Object> {
        private final String title;

        public MenuItemTitleMatcher(String title) {
            this.title = title;
        }

        @Override
        public boolean matches(Object item) {
            if (item instanceof MenuItem) {
                return ((MenuItem) item).getTitle().equals(title);
            }
            return false;
        }

        @Override
        public void describeTo(Description description) {

        }
    }
}
