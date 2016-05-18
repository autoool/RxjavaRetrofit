package com.techidea.presentation.internal.di.modules;

import com.techidea.domain.executor.PostExecutionThread;
import com.techidea.domain.executor.ThreadExecutor;
import com.techidea.domain.interactor.InitProduct;
import com.techidea.domain.interactor.InitProductCategory;
import com.techidea.domain.interactor.RxBaseCase;
import com.techidea.domain.respository.ProductRepository;
import com.techidea.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zchao on 2016/5/12.
 */
@Module
public class ProductModule {

    private String deviceId;
    private String deviceType;

    public ProductModule() {
    }

    public ProductModule(String deviceId, String deviceType) {
        this.deviceId = deviceId;
        this.deviceType = deviceType;
    }

    @Provides
    @PerActivity
    @Named("initProductCategory")
    RxBaseCase provideInitProductCategory(
            ProductRepository productRepository,
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread
    ) {
        return new InitProductCategory( productRepository);
    }

    @Provides
    @PerActivity
    @Named("initProduct")
    RxBaseCase provideInitProduct(ProductRepository productRepository,
                                  ThreadExecutor threadExecutor,
                                  PostExecutionThread postExecutionThread) {
        return new InitProduct(productRepository);
    }
}
