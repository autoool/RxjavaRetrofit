package com.techidea.data.cache;

import com.techidea.data.entity.UserInfoEntity;
import com.techidea.domain.executor.ThreadExecutor;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by zchao on 2016/5/5.
 */
public class UserInfoCacheImpl implements UserInfoCache{

    private final ThreadExecutor mThreadExecutor;

    public UserInfoCacheImpl(ThreadExecutor threadExecutor) {
        this.mThreadExecutor = threadExecutor;
    }

    @Override
    public Observable<UserInfoEntity> initUserInfo() {
        return Observable.create(new Observable.OnSubscribe<UserInfoEntity>() {
            @Override
            public void call(Subscriber<? super UserInfoEntity> subscriber) {

            }
        });
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public void put(UserInfoEntity userInfoEntity) {

    }

    @Override
    public void evictAll() {

    }

    private void executeAsynchromously(Runnable runnable){
        this.mThreadExecutor.execute(runnable);
    }


}
