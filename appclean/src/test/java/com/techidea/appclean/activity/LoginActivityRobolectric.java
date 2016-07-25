package com.techidea.appclean.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.techidea.appclean.BuildConfig;
import com.techidea.appclean.R;
import com.techidea.appclean.adapter.CommonSpinnerAdapter;
import com.techidea.appclean.adapter.SpinnerItem;
import com.techidea.appclean.login.LoginActivity;
import com.techidea.appclean.login.LoginFragment;
import com.techidea.appclean.main.MainActivity;

import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAbsSpinner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowTextView;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by zchao on 2016/7/18.
 * ui 流程
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoginActivityRobolectric {

    private Context mContext;
    private LoginActivity mLoginActivity;
    private EditText mEditTextUser;
    private EditText mEditTextPwd;
    private Button mButton;
    private Button mButtonJump;
    private AppCompatSpinner mAppCompatSpinner;
    private LoginFragment mFragment;
    private List<SpinnerItem> mSpinnerItems;
    private CommonSpinnerAdapter mCommonSpinnerAdapter;
    private ShadowAbsSpinner mAbsSpinner;


    @Before
    public void setup() {
        mLoginActivity = Robolectric.buildActivity(LoginActivity.class).create().resume().visible().get();
        mContext = RuntimeEnvironment.application;
        mEditTextPwd = (EditText) mLoginActivity.findViewById(R.id.edittext_password);
        mButton = (Button) mLoginActivity.findViewById(R.id.button_login);
        mButtonJump = (Button) mLoginActivity.findViewById(R.id.button_jump);
        mAppCompatSpinner = (AppCompatSpinner) mLoginActivity.findViewById(R.id.spinner_username);
        mFragment = (LoginFragment) mLoginActivity.getSupportFragmentManager().findFragmentById(R.id.framelayout_login);
    }


    @Test
    public void shouldNotBeNull() {
        assertNotNull(mLoginActivity);
    }

    @Test
    public void spinnerNotBenull() {
        assertThat(mAppCompatSpinner, is(notNullValue()));
    }

    @Test
    public void loginTest() {
        mAppCompatSpinner.setSelection(4, true);
        mEditTextPwd.setText("111111");
        mButton.performClick();

        ShadowActivity shadowActivity = Shadows.shadowOf(mLoginActivity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertEquals(MainActivity.class.getCanonicalName(),
                actualIntent.getComponent().getClassName());
    }

    @Test
    public void jumpMain() {
        mButtonJump.performClick();
        ShadowActivity shadowActivity = Shadows.shadowOf(mLoginActivity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        Assert.assertEquals(MainActivity.class.getCanonicalName(), actualIntent.getComponent().getClassName());
    }
}
