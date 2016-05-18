package com.techidea.presentation.internal.di.components;

import android.content.Context;

import com.techidea.domain.executor.PostExecutionThread;
import com.techidea.domain.executor.ThreadExecutor;
import com.techidea.domain.respository.ProductRepository;
import com.techidea.domain.respository.UserInfoRepository;
import com.techidea.presentation.base.BaseActivity;
import com.techidea.presentation.internal.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zchao on 2016/5/5.
 * 需要初始化的依赖 ApplicationModule
 * //注入器  制定注入器可以注入的模块
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    UserInfoRepository userInfoRepository();

    ProductRepository productRepository();
}
