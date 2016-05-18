package com.techidea.presentation.internal.di.components;

import com.techidea.presentation.internal.di.PerActivity;
import com.techidea.presentation.internal.di.modules.ActivityModule;
import com.techidea.presentation.internal.di.modules.ProductModule;
import com.techidea.presentation.internal.di.modules.UserInfoModule;
import com.techidea.presentation.view.login.LoginFragment;
import com.techidea.presentation.view.splash.SplashFragment;

import dagger.Component;

/**
 * Created by zchao on 2016/5/6.
 * 注入方法
 * User Component: 继承于ActivityComponent的组件，
 * 并用@PerActivity注解。我通常会在注入用户相关的fragment中使用。
 * 因为 ActivityModule把activity暴露给图了，
 * 所以在任何需要一个activity的context的时候，
 * Dagger都可以提供注入， 没必要再在子modules中定义了。
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = {ActivityModule.class, UserInfoModule.class, ProductModule.class})
public interface FragmentComponent extends ActivityComponent {

    void inject(SplashFragment mainFragment);

    void inject(LoginFragment loginFragment);

    void plus(UserInfoModule userInfoModule);
}
