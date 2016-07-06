package com.techidea.data.repository.datasource;

import com.techidea.domain.entity.LoginUser;
import com.techidea.domain.entity.Product;
import com.techidea.domain.entity.ProductCategory;
import com.techidea.domain.entity.UserInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by zchao on 2016/7/6.
 */
public class DiskDataStore implements DataStore{

    @Override
    public Observable<List<ProductCategory>> initProductCategory(String devideId, String deviceType) {
        return null;
    }

    @Override
    public Observable<List<Product>> initProduct(String devideId, String deviceType) {
        return null;
    }

    @Override
    public Observable<List<UserInfo>> initUserInfo(String deviceId, String deviceType) {
        return null;
    }

    @Override
    public Observable<LoginUser> login(String deviceId, String username, String password) {
        return null;
    }
}
