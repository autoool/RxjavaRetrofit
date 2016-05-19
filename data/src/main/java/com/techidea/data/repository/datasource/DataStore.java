package com.techidea.data.repository.datasource;

import com.techidea.domain.entity.LoginUser;
import com.techidea.domain.entity.Product;
import com.techidea.domain.entity.ProductCategory;
import com.techidea.domain.entity.UserInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by zchao on 2016/5/19.
 */
public interface DataStore {

    Observable<List<ProductCategory>> initProductCategory(String devideId, String deviceType);

    Observable<List<Product>> initProduct(String devideId, String deviceType);

    Observable<List<UserInfo>> initUserInfo(String deviceId, String deviceType);

    Observable<LoginUser> login(String deviceId, String username, String password);
}
