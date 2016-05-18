package com.techidea.data.entity.mapper;

import com.techidea.data.entity.UserInfoEntity;
import com.techidea.domain.UserInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserInfoEntityMapper {

    @Inject
    public UserInfoEntityMapper() {
    }

    public UserInfo transform(UserInfoEntity userInfoEntity) {
        UserInfo userInfo = new UserInfo();
        if (userInfoEntity != null) {
            userInfo.setType(userInfoEntity.getType());
            userInfo.setUsername(userInfoEntity.getUsername());
        }
        return userInfo;
    }

    public List<UserInfo> transform(Collection<UserInfoEntity> userInfoEntityCollection) {
        List<UserInfo> userInfoList = new ArrayList<>();
        UserInfo userInfo;
        for (UserInfoEntity userInfoEntity : userInfoEntityCollection) {
            userInfo = transform(userInfoEntity);
            if (userInfo != null)
                userInfoList.add(userInfo);
        }
        return userInfoList;
    }
}
