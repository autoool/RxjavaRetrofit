package com.techidea.presentation.internal.di.modules;

import android.app.Activity;

import com.techidea.presentation.internal.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zchao on 2016/5/5.
 * 注解类 返回对象
 * 把activity暴露给相关联的类。比如在fragment中使用activity的context。
 */
@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    Activity activity() {
        return this.mActivity;
    }
}
