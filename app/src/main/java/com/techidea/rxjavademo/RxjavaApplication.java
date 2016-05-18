package com.techidea.rxjavademo;

import android.app.Application;
import android.content.Context;

import com.techidea.rxjavademo.component.ApplicationComponent;
import com.techidea.rxjavademo.network.CustomTrust;

/**
 * Created by zchao on 2016/3/25.
 */
public class RxjavaApplication extends Application {

    private static ApplicationComponent mComponent;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        CustomTrust.getInstance().init(context);


    }

    public static Context getContext() {
        return context;
    }

}
