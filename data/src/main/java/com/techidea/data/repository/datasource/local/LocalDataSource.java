package com.techidea.data.repository.datasource.local;

import android.content.Context;

import com.techidea.data.cache.DataCache;
import com.techidea.data.cache.DataCacheImpl;
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
public class LocalDataSource {

    private final DataCache mDataCache;

    public LocalDataSource(Context context) {
        this.mDataCache = new DataCacheImpl(context);
    }

    public Observable<List<ProductCategory>> initProductCategory() {
        return this.mDataCache.getProductCategorys();
    }


    public Observable<List<Product>> initProduct() {
        return this.mDataCache.getProducts();
    }


    public Observable<List<UserInfo>> initUserInfo() {
        return this.mDataCache.getLoginUsers();
    }


    public Observable<LoginUser> login() {
        return this.mDataCache.getLoginUser();
    }


}
