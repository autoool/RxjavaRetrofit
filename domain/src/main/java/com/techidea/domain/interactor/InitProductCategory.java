package com.techidea.domain.interactor;

import com.techidea.domain.executor.PostExecutionThread;
import com.techidea.domain.executor.ThreadExecutor;
import com.techidea.domain.respository.ProductRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by zchao on 2016/5/13.
 */
public class InitProductCategory extends RxBaseCase {

    private String deviceId;
    private String deviceType;
    private final ProductRepository mProductRepository;

    @Inject
    public InitProductCategory(ProductRepository productRepository) {
        this.mProductRepository = productRepository;
    }

    @Override
    public RxBaseCase initParams(String... paras) {
        deviceId = paras[0];
        deviceType = paras[1];
        return this;
    }

    @Override
    protected Observable buildCaseObservable() {
        return this.mProductRepository.initProductCategory(this.deviceId, this.deviceType);
    }

}
