package com.techidea.robolectrictest;

import android.content.Intent;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by zchao on 2016/9/20.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, application = TestApplication.class)
public class FirstActivityTest {

    @Test
    public void testToSecond() {

        FirstActivity activity = Robolectric.setupActivity(FirstActivity.class);
        activity.findViewById(R.id.textview_second).performClick();

        Intent expectedIntent = new Intent(activity, SecondActivity.class);
        assertThat(shadowOf(activity).getNextStartedActivity()).isEqualTo(expectedIntent);

        FirstActivity firstActivity = Robolectric.setupActivity(FirstActivity.class);
        firstActivity.findViewById(R.id.textview_second).performClick();
        Intent actualIntent = shadowOf(firstActivity).getNextStartedActivity();
//        assertThat(shadowOf(firstActivity).getNextStartedActivity()).isEqualTo(expextIntent);
        Assert.assertEquals(SecondActivity.class.getCanonicalName(), actualIntent.getComponent().getClassName());
    }
}
