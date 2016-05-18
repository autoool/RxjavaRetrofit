package com.techidea.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.techidea.presentation.view.login.LoginActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by zchao on 2016/5/5.
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {
    }

    public void navigateToLogin(Context context) {
        if (context != null) {
            Intent intent = LoginActivity.getCallingIntent(context);
            context.startActivity(intent);
        }
    }
}
