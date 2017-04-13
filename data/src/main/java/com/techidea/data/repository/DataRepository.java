package com.techidea.data.repository;

import android.content.Context;
import android.content.Intent;

import com.techidea.data.repository.datasource.remote.RemoteDataSource;
import com.techidea.domain.entity.CityInfo;
import com.techidea.domain.entity.CityItem;
import com.techidea.domain.respository.DataRepositoryDomain;

import java.io.File;
import java.util.List;

import rx.Observable;

/**
 * Created by zchao on 2016/5/19.
 * 区分 从缓存获取 从网络获取
 */
public class DataRepository implements DataRepositoryDomain {

    private static DataRepository Instance = null;

    private final RemoteDataSource remoteDataSource;


    private DataRepository(Context context) {
        this.remoteDataSource = new RemoteDataSource();
    }

    public static DataRepository getInstance(Context context) {
        if (Instance == null) {
            Instance = new DataRepository(context);
        }
        return Instance;
    }

    @Override
    public Observable<List<CityItem>> getCityList(String cityname) {
        return null;
    }

    @Override
    public Observable<List<CityInfo>> getSearchCity(String citytype, String key) {
        return null;
    }

    @Override
    public Observable<String> uploadFileWidthParam(String key, File file) {
        return this.remoteDataSource.uploadFileWidthParam(key, file);
    }
}
