package com.techidea.data.repository.datasource;

import com.techidea.data.net.HttpMethods;
import com.techidea.domain.entity.LoginUser;
import com.techidea.domain.entity.Product;
import com.techidea.domain.entity.ProductCategory;
import com.techidea.domain.entity.UserInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by zchao on 2016/5/19.
 */
public class RemoteDataStore implements DataStore {

    public RemoteDataStore() {
    }

    @Override
    public Observable<List<ProductCategory>> initProductCategory(String devideId, String deviceType) {
        return HttpMethods.getInstance().initProductCategory(devideId, deviceType);
    }

    @Override
    public Observable<List<Product>> initProduct(String devideId, String deviceType) {
        return HttpMethods.getInstance().initProduct(devideId, deviceType);
    }

    @Override
    public Observable<List<UserInfo>> initUserInfo(String deviceId, String deviceType) {
        return  HttpMethods.getInstance().initLoginUsers(deviceId, deviceType);
    }

    @Override
    public Observable<LoginUser> login(String deviceId, String username, String password) {
        return HttpMethods.getInstance().login(deviceId, username, password);
    }
}
