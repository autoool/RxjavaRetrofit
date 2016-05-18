package com.techidea.rxjavademo.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zchao on 2016/5/5.
 */
@Module
public class ApplicationModule {
    private Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;
    }

    @Singleton
    @Provides
    public Application application() {
        return mApplication;
    }

    @Singleton
    @Provides
    public Context context() {
        return mApplication;
    }
}
