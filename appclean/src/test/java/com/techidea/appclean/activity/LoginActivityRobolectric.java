package com.techidea.appclean.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.techidea.appclean.BuildConfig;
import com.techidea.appclean.R;
import com.techidea.appclean.login.LoginActivity;
import com.techidea.appclean.main.MainActivity;

import org.junit.Assert;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowTextView;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by zchao on 2016/7/18.
 * ui 流程
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoginActivityRobolectric {

    private LoginActivity mLoginActivity;
    private EditText mEditTextUser;
    private EditText mEditTextPwd;
    private Button mButton;

    @Before
    public void setup() {
        mLoginActivity = Robolectric.buildActivity(LoginActivity.class).create().resume().visible().get();
        mEditTextPwd = (EditText) mLoginActivity.findViewById(R.id.edittext_password);
        mEditTextUser = (EditText) mLoginActivity.findViewById(R.id.edittext_username);
        mButton = (Button) mLoginActivity.findViewById(R.id.button_jump);
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull(mLoginActivity);
    }

    @Test
    public void spinnerInitLoginUsers() {

    }

    @Test
    public void testContainsLoginFragement() throws Exception{
        // Fragment 需要保持一致
        Fragment loginFragment = mLoginActivity.getSupportFragmentManager().findFragmentById(R.id.framelayout_login);
        assertThat(loginFragment, is(notNullValue()));
    }


    @Test
    public void checkUserPwd() {
        mEditTextUser.setText("chao01");
        mEditTextPwd.setText("111111");
        mLoginActivity.findViewById(R.id.button_login).performClick();
    }

    @Test
    public void jumpMain() {
        mButton.performClick();
        ShadowActivity shadowActivity = Shadows.shadowOf(mLoginActivity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        Assert.assertEquals(MainActivity.class.getCanonicalName(), actualIntent.getComponent().getClassName());
    }
}
