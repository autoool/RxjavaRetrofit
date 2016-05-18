package com.techidea.appclean;

import android.app.Application;

import com.techidea.data.net.CustomTrust;

/**
 * Created by zchao on 2016/5/18.
 */
public class CleanApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        CustomTrust.getInstance().init(getApplicationContext());
    }
}
