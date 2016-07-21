package com.techidea.appclean.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by zchao on 2016/5/18.
 * 有toolbar 继承，没有不继承
 */
public abstract class BaseFragment extends Fragment {

    AppCompatActivity mAppCompatActivity;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAppCompatActivity = (AppCompatActivity) getActivity();
    }

    public Toolbar initToolbar(int toolbarId) {
        setHasOptionsMenu(true);
        Toolbar toolbar = (Toolbar) mAppCompatActivity.findViewById(toolbarId);
        mAppCompatActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        return toolbar;
    }

    protected void showToastMessage(String message) {

    }
}
