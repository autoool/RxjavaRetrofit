package com.techidea.presentation;

import android.app.Application;

import com.techidea.data.net.SslSocketFactory;
import com.techidea.presentation.internal.di.components.ApplicationComponent;

/**
 * Created by zchao on 2016/5/5.
 */
public class AndroidApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
//        SslSocketFactory.getInstance().init(getApplicationContext());
    }

    private void initializeInjector() {
//        初始化ApplicationComponent注入器，没有真正的注入对象
    }

    public ApplicationComponent getApplicationComponent() {
        return this.mApplicationComponent;
    }
}
