package com.techidea.presentation;

import android.app.Application;

import com.techidea.data.net.CustomTrust;
import com.techidea.presentation.internal.di.components.ApplicationComponent;
import com.techidea.presentation.internal.di.components.DaggerApplicationComponent;
import com.techidea.presentation.internal.di.modules.ApplicationModule;

/**
 * Created by zchao on 2016/5/5.
 */
public class AndroidApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        CustomTrust.getInstance().init(getApplicationContext());
    }

    private void initializeInjector() {
//        初始化ApplicationComponent注入器，没有真正的注入对象
        this.mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.mApplicationComponent;
    }
}
