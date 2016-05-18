package com.techidea.rxjavademo.component;

import com.techidea.rxjavademo.main.MainActivity;
import com.techidea.rxjavademo.modules.ApplicationModule;

import dagger.Component;

/**
 * Created by zchao on 2016/5/5.
 */
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(MainActivity activity);
}
