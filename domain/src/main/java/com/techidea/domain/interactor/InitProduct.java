package com.techidea.domain.interactor;

import com.techidea.domain.executor.PostExecutionThread;
import com.techidea.domain.executor.ThreadExecutor;
import com.techidea.domain.respository.ProductRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by zchao on 2016/5/12.
 */
public class InitProduct extends RxBaseCase {

    private String deviceId;
    private String deviceType;
    private final ProductRepository mProductRepository;

    @Inject
    public InitProduct(ProductRepository productRepository) {
        this.mProductRepository = productRepository;
    }

    @Override
    public RxBaseCase initParams(String... paras) {
        this.deviceId = paras[0];
        this.deviceType = paras[1];
        return this;
    }

    @Override
    protected Observable buildCaseObservable() {
        return this.mProductRepository.initProduct(deviceId, deviceType);
    }
}
