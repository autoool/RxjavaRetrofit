package com.techidea.appclean.activity;

import android.widget.Button;
import android.widget.TextView;

import com.techidea.appclean.BuildConfig;
import com.techidea.appclean.R;
import com.techidea.appclean.main.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by zchao on 2016/7/22.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityRobolectric {

    private MainActivity mMainActivity;
    private Button mButtonCitylist;
    private TextView mTextViewSource;
    private TextView mTextViewDest;

    @Before
    public void setup() {
        mMainActivity = Robolectric.buildActivity(MainActivity.class).get();
        mButtonCitylist = (Button) mMainActivity.findViewById(R.id.button_citylist);
        mTextViewSource = (TextView) mMainActivity.findViewById(R.id.textview_source);
        mTextViewDest = (TextView) mMainActivity.findViewById(R.id.textview_dest);
    }

    @Test
    public void InfoTestCase() {
        assertThat(mTextViewSource.getText(), is(notNullValue()));
    }

    @Test
    public void destValue() {
        mButtonCitylist.performClick();
        assertEquals(mTextViewSource.getText(), mTextViewDest.getText());
    }
}
