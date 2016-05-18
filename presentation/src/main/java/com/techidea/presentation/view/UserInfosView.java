package com.techidea.presentation.view;

import com.techidea.presentation.model.UserInfoModel;

import java.util.Collection;

/**
 * Created by zchao on 2016/5/6.
 */
public interface UserInfosView extends LoadDataView {

    void renderUserInfo(Collection<UserInfoModel> userInfoModels);

    void viewUser(UserInfoModel userInfoModel);

    void updateProgress(int progress);

    void initUserSuccess();
}
