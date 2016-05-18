package com.techidea.domain.respository;

import com.techidea.domain.Product;
import com.techidea.domain.ProductCategory;

import java.util.List;

import rx.Observable;

/**
 * Created by zchao on 2016/5/12.
 */
public interface ProductRepository {

    Observable<List<ProductCategory>> initProductCategory(String devideId, String deviceType);

    Observable<List<Product>> initProduct(String devideId, String deviceType);
}
