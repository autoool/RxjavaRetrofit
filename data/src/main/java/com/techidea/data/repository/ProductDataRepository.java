package com.techidea.data.repository;


import com.techidea.data.net.HttpMethods;
import com.techidea.domain.entity.Product;
import com.techidea.domain.entity.ProductCategory;
import com.techidea.domain.respository.ProductRepository;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;


/**
 * Created by zchao on 2016/5/12.
 */
@Singleton
public class ProductDataRepository implements ProductRepository {

    private static ProductDataRepository INSTANCE = null;

    @Inject
    public ProductDataRepository() {
    }

    public static ProductDataRepository getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ProductDataRepository();
        return INSTANCE;
    }

    public static void destoryInstance(){
        INSTANCE = null;
    }

    @Override
    public Observable<List<ProductCategory>> initProductCategory(String devideId, String deviceType) {
        return HttpMethods.getInstance().initProductCategory(devideId, deviceType);
    }

    @Override
    public Observable<List<Product>> initProduct(String devideId, String deviceType) {
        return HttpMethods.getInstance().initProduct(devideId, deviceType);
    }
}
