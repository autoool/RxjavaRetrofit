package com.techidea.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.techidea.data.cache.DataCache;
import com.techidea.data.repository.datasource.local.LocalDataSource;
import com.techidea.data.repository.datasource.remote.RemoteDataSource;

/**
 * Created by zchao on 2016/5/19.
 * 从缓存获取 或者从网络获取
 */
public class DataStoreFactory {

    //不知道该怎么把所有的网络数据获取写在一个里面        private final Context mContext;

    public DataStoreFactory(@NonNull Context context) {
        if (context == null )
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
    }

//    public DataStore create() {
//        DataStore dataStore;
//        if (!mDataCache.isExpired("")) {
//            dataStore = new LocalDataSource(mDataCache);
//        } else {
//            dataStore = new RemoteDataSource(mDataCache);
//        }
//        return dataStore;
//    }
}
