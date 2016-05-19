package com.techidea.domain.respository;



import com.techidea.domain.entity.LoginUser;
import com.techidea.domain.entity.UserInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by zchao on 2016/5/5.
 */
public interface UserInfoRepository {

    Observable<List<UserInfo>> initUserInfo(String deviceId, String deviceType);

    Observable<LoginUser> login(String deviceId, String username, String password);
}
