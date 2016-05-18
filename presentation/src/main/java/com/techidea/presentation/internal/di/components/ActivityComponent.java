package com.techidea.presentation.internal.di.components;

import android.app.Activity;
import com.techidea.presentation.internal.di.PerActivity;
import com.techidea.presentation.internal.di.modules.ActivityModule;

import dagger.Component;

/**
 * Created by zchao on 2016/5/5.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}
