package com.techidea.data.repository;

import android.content.Context;
import android.net.ConnectivityManager;

import com.techidea.data.net.HttpMethods;
import com.techidea.data.repository.datasource.local.LocalDataSource;
import com.techidea.data.repository.datasource.remote.RemoteDataSource;
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

    public DataRepository(Context context) {
        this.mLocalDataSource = new LocalDataSource(context);
        this.mRemoteDataSource = new RemoteDataSource(context);
    }

    public static DataRepository getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = new DataRepository(context);
        return INSTANCE;
    }

    @Override
    public Observable<List<ProductCategory>> initProductCategory(String devideId, String deviceType) {
        return this.mRemoteDataSource.initProductCategory(devideId, deviceType);
    }

    @Override
    public Observable<List<Product>> initProduct(String devideId, String deviceType) {
        return this.mRemoteDataSource.initProduct(devideId, deviceType);
    }

    @Override
    public Observable<List<UserInfo>> initUserInfo(String deviceId, String deviceType) {
        return this.mRemoteDataSource.initUserInfo(deviceId, deviceType);
    }

    @Override
    public Observable<LoginUser> login(String deviceId, String username, String password) {
        return this.mRemoteDataSource.login(deviceId, username, password);
    }

    @Override
    public Observable<MemberInfoItem> getMemberInfo(String qrcode, String type) {
        return HttpMethods.getInstance().getMemberInfo(qrcode, type);
    }
}
