package com.techidea.data.cache;

import com.techidea.data.entity.UserInfoEntity;

import rx.Observable;

/**
 * Created by zchao on 2016/5/5.
 */
public interface UserInfoCache {
    Observable<UserInfoEntity> initUserInfo();

    void put(UserInfoEntity userInfoEntity);

    boolean isExpired();

    void evictAll();

}
