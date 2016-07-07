package com.techidea.presentation.internal.di.modules;

import android.content.Context;

import com.techidea.data.executor.JobExecutor;
import com.techidea.data.repository.ProductDataRepository;
import com.techidea.data.repository.UserInfoDataRepository;
import com.techidea.domain.executor.PostExecutionThread;
import com.techidea.domain.executor.ThreadExecutor;
import com.techidea.presentation.AndroidApplication;
import com.techidea.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zchao on 2016/5/5.
 * module的作用是提供在应用的生命周期中存活的对象
 */
@Module
public class ApplicationModule {

    //提供以下对象的依赖
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    UserInfoRepository provideUserInfoRepository(UserInfoDataRepository userInfoDataRepository) {
        return userInfoDataRepository;
    }

    @Provides
    @Singleton
    ProductRepository provideProductRepository(ProductDataRepository productRepository) {
        return productRepository;
    }

}

