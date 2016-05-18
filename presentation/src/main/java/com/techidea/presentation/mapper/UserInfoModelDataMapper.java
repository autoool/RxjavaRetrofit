package com.techidea.presentation.mapper;

import com.techidea.domain.UserInfo;
import com.techidea.presentation.internal.di.PerActivity;
import com.techidea.presentation.model.UserInfoModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Created by zchao on 2016/5/5.
 */
@PerActivity
public class UserInfoModelDataMapper {

    @Inject
    public UserInfoModelDataMapper() {
    }

    public UserInfoModel transform(UserInfo userInfo) {
        if (userInfo == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        UserInfoModel userInfoModel = new UserInfoModel();
        userInfoModel.setType(userInfo.getType());
        userInfoModel.setUsername(userInfo.getUsername());
        return userInfoModel;
    }

    public Collection<UserInfoModel> transform(Collection<UserInfo> userInfos) {
        Collection<UserInfoModel> userInfoModelCollection;
        if (userInfos != null && !userInfos.isEmpty()) {
            userInfoModelCollection = new ArrayList<>();
            for (UserInfo userInfo : userInfos) {
                userInfoModelCollection.add(transform(userInfo));
            }
        } else {
            userInfoModelCollection = Collections.emptyList();
        }
        return userInfoModelCollection;
    }
}
