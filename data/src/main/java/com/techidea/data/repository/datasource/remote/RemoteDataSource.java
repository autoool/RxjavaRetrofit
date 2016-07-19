package com.techidea.data.repository.datasource.remote;

import android.content.Context;

import com.techidea.data.cache.DataCache;
import com.techidea.data.cache.DataCacheImpl;
import com.techidea.domain.entity.CityItem;
import com.techidea.data.net.HttpMethods;
import com.techidea.data.repository.datasource.DataStore;
import com.techidea.domain.entity.LoginUser;
import com.techidea.domain.entity.Product;
import com.techidea.domain.entity.ProductCategory;
import com.techidea.domain.entity.UserInfo;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by zchao on 2016/5/19.
 * 服务端数据
 */
public class RemoteDataSource implements DataStore {

    private final DataCache mDataCache;

    private final Action1<List<ProductCategory>> saveToProductCategory = new Action1<List<ProductCategory>>() {
        @Override
        public void call(List<ProductCategory> productCategoryList) {
            mDataCache.putProductCategorys(productCategoryList);
        }
    };

    private final Action1<List<Product>> saveToPtoduct = new Action1<List<Product>>() {
        @Override
        public void call(List<Product> productList) {
            mDataCache.putProducts(productList);
        }
    };

    private final Action1<List<UserInfo>> saveToUserInfos = new Action1<List<UserInfo>>() {
        @Override
        public void call(List<UserInfo> userInfoList) {
            mDataCache.putLoginUsers(userInfoList);
        }
    };

    private final Action1<LoginUser> saveToLoginUser = new Action1<LoginUser>() {
        @Override
        public void call(LoginUser loginUser) {
            if (loginUser != null)
                HttpMethods.getInstance().setToken(loginUser.getToken());
            mDataCache.putLoginUser(loginUser);
        }
    };

    public RemoteDataSource(Context context) {
        this.mDataCache = new DataCacheImpl(context);
    }

    @Override
    public Observable<List<ProductCategory>> initProductCategory(String devideId, String deviceType) {
        return HttpMethods.getInstance().initProductCategory(devideId, deviceType).doOnNext(saveToProductCategory);
    }

    @Override
    public Observable<List<Product>> initProduct(String devideId, String deviceType) {
        return HttpMethods.getInstance().initProduct(devideId, deviceType).doOnNext(saveToPtoduct);
    }

    @Override
    public Observable<List<UserInfo>> initUserInfo(String deviceId, String deviceType) {
        return HttpMethods.getInstance().initLoginUsers(deviceId, deviceType).doOnNext(saveToUserInfos);
    }

    @Override
    public Observable<LoginUser> login(String deviceId, String username, String password) {
        return HttpMethods.getInstance().login(deviceId, username, password).doOnNext(saveToLoginUser);
    }

    @Override
    public Observable<List<CityItem>> getCityList(String cityname) {
        return HttpMethods.getInstance().getCityList(cityname);
    }
}
