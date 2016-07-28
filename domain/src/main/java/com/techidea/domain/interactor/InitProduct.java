package com.techidea.domain.interactor;

import com.techidea.domain.entity.Product;
import com.techidea.domain.respository.DataRepositoryDomain;

import java.util.List;

import rx.Observable;

/**
 * Created by zchao on 2016/5/12.
 */
public class InitProduct extends RxBaseCase<List<Product>> {

    private String deviceId;
    private String deviceType;
    private DataRepositoryDomain mDataRepository;

    public InitProduct(DataRepositoryDomain dataRepository) {
        this.mDataRepository = dataRepository;
    }

    @Override
    public RxBaseCase initParams(String... paras) {
        this.deviceId = paras[0];
        this.deviceType = paras[1];
        return this;
    }


    @Override
    public Observable<List<Product>> execute() {
        return this.mDataRepository.initProduct(deviceId, deviceType);
    }
}
