package com.techidea.data.repository.datasource.local;

import android.content.Context;

import com.techidea.data.cache.DataCache;
import com.techidea.data.cache.DataCacheImpl;
import com.techidea.data.repository.datasource.DataStore;
import com.techidea.domain.entity.LoginUser;
import com.techidea.domain.entity.Product;
import com.techidea.domain.entity.ProductCategory;
import com.techidea.domain.entity.UserInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by zchao on 2016/7/6.
 * //本地数据源
 */
public class LocalDataSource implements DataStore {

    private final DataCache mDataCache;

    public LocalDataSource(Context context) {

        this.mDataCache = new DataCacheImpl(context);
    }

    @Override
    public Observable<List<ProductCategory>> initProductCategory(String devideId, String deviceType) {
        return this.mDataCache.getProductCategorys();
    }

    @Override
    public Observable<List<Product>> initProduct(String devideId, String deviceType) {
        return this.mDataCache.getProducts();
    }

    @Override
    public Observable<List<UserInfo>> initUserInfo(String deviceId, String deviceType) {
        return this.mDataCache.getLoginUsers();
    }

    @Override
    public Observable<LoginUser> login(String deviceId, String username, String password) {
        return this.mDataCache.getLoginUser();
    }
}
