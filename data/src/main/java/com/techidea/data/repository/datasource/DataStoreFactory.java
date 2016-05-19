package com.techidea.data.repository.datasource;

/**
 * Created by zchao on 2016/5/19.
 * 从缓存获取 或者从网络获取
 */
public class DataStoreFactory {

    public DataStoreFactory() {
    }
    //参数

    public DataStore createDataStore() {
        return new RemoteDataStore();
    }
}
