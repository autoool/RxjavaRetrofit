package com.techidea.data.repository;

import android.content.Context;

import com.techidea.data.cache.DataCache;
import com.techidea.data.cache.DataCacheImpl;
import com.techidea.data.net.HttpMethods;
import com.techidea.data.repository.datasource.local.LocalDataSource;
import com.techidea.data.repository.datasource.remote.RemoteDataSource;
import com.techidea.domain.entity.CityInfo;
import com.techidea.domain.entity.CityItem;
import com.techidea.domain.entity.LoginUser;
import com.techidea.domain.entity.MemberInfoItem;
import com.techidea.domain.entity.Product;
import com.techidea.domain.entity.ProductCategory;
import com.techidea.domain.entity.UserInfo;
import com.techidea.domain.respository.DataRepositoryDomain;

import java.util.List;

import rx.Observable;

/**
 * Created by zchao on 2016/5/19.
 * 区分 从缓存获取 从网络获取
 */
public class DataRepository implements DataRepositoryDomain {

    private static DataRepository INSTANCE = null;
    private final RemoteDataSource mRemoteDataSource;
    private final LocalDataSource mLocalDataSource;
    private final DataCache mDataCache;

    public DataRepository(Context context) {
        this.mLocalDataSource = new LocalDataSource(context);
        this.mRemoteDataSource = new RemoteDataSource(context);
        this.mDataCache = new DataCacheImpl(context);
    }

    public static DataRepository getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = new DataRepository(context);
        return INSTANCE;
    }

    @Override
    public Observable<List<ProductCategory>> initProductCategory(String deviceId, String deviceType) {
        if (this.mDataCache.isCached(DataCacheImpl.FILE_PRODUCTCATEGORYS)) {
            if (this.mDataCache.isExpired(DataCacheImpl.FILE_PRODUCTCATEGORYS)) {
                return this.mRemoteDataSource.initProductCategory(deviceId, deviceType);
            } else {
                return this.mLocalDataSource.initProductCategory();
            }
        } else {
            return this.mRemoteDataSource.initProductCategory(deviceId, deviceType);
        }
    }

    @Override
    public Observable<List<Product>> initProduct(String deviceId, String deviceType) {
        if (this.mDataCache.isCached(DataCacheImpl.FILE_PRODUCTS)) {
            if (this.mDataCache.isExpired(DataCacheImpl.FILE_PRODUCTS)) {
                return this.mRemoteDataSource.initProduct(deviceId, deviceType);
            } else {
                return this.mLocalDataSource.initProduct();
            }
        } else {
            return this.mRemoteDataSource.initProduct(deviceId, deviceType);
        }
    }

    @Override
    public Observable<List<UserInfo>> initUserInfo(String deviceId, String deviceType) {
        if (this.mDataCache.isCached(DataCacheImpl.FILE_USREINFOS)) {
            if (this.mDataCache.isExpired(DataCacheImpl.FILE_USREINFOS)) {
                return this.mRemoteDataSource.initUserInfo(deviceId, deviceType);
            } else {
                return this.mLocalDataSource.initUserInfo();
            }
        } else {
            return this.mRemoteDataSource.initUserInfo(deviceId, deviceType);
        }
    }

    @Override
    public Observable<LoginUser> login(String deviceId, String username, String password) {
        return this.mRemoteDataSource.login(deviceId, username, password);
    }

    @Override
    public Observable<MemberInfoItem> getMemberInfo(String qrcode, String type) {
        return HttpMethods.getInstance().getMemberInfo(qrcode, type);
    }

    public Observable<LoginUser> getLoginUser() {
        return null;
    }

    @Override
    public Observable<List<CityItem>> getCityList(String cityname) {
//        return HttpMethods.getInstance().getCityList(cityname);
        return this.mRemoteDataSource.getCityList(cityname);
    }

    @Override
    public Observable<List<CityInfo>> getSearchCity(String citytype, String key) {
        return this.mRemoteDataSource.getSearchCityInfo(citytype, key);
    }
}
