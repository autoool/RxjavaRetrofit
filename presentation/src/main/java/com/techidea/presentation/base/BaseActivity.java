package com.techidea.presentation.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.techidea.presentation.AndroidApplication;
import com.techidea.presentation.internal.di.components.ApplicationComponent;
import com.techidea.presentation.internal.di.modules.ActivityModule;
import com.techidea.presentation.navigation.Navigator;

import javax.inject.Inject;

/**
 * Created by zchao on 2016/5/5.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    protected Navigator mNavigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注入器注入对象到activity
        this.getApplicationComponent().inject(this);

    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

}
