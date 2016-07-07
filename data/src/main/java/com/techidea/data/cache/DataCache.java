package com.techidea.data.cache;

import com.techidea.domain.entity.LoginUser;
import com.techidea.domain.entity.Product;
import com.techidea.domain.entity.ProductCategory;
import com.techidea.domain.entity.UserInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by zchao on 2016/7/6.
 */
public interface DataCache {

    Observable<List<UserInfo>> getLoginUsers();

    void putLoginUsers(List<UserInfo> userInfoList);

    Observable<List<Product>> getProducts();

    void putProducts(List<Product> productList);

    Observable<List<ProductCategory>> getProductCategorys();

    void putProductCategorys(List<ProductCategory> productCategoryList);

    Observable<LoginUser> getLoginUser();

    void putLoginUser(LoginUser loginUser);

    boolean isCached(String filename);

    boolean isExpired(String filename);

    void evictAll();
}
