package com.techidea.presentation.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.techidea.corelibrary.util.CommonUtilAPP;
import com.techidea.domain.UserInfo;
import com.techidea.domain.interactor.DefaultSubscriber;
import com.techidea.domain.interactor.RxBaseCase;
import com.techidea.presentation.internal.di.PerActivity;
import com.techidea.presentation.mapper.UserInfoModelDataMapper;
import com.techidea.presentation.view.UserInfosView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by zchao on 2016/5/6.
 */
@PerActivity
public class UserInfoPresenter implements Presenter {

    private Context mContext;
    private UserInfosView mUserInfosView;

    private final RxBaseCase initUserInfosCase;
    private final UserInfoModelDataMapper mUserInfoModelDataMapper;

    @Inject
    public UserInfoPresenter(@Named("userInfoList") RxBaseCase initUserInfos,
                             UserInfoModelDataMapper userInfoModelDataMapper) {
        this.initUserInfosCase = initUserInfos;
        this.mUserInfoModelDataMapper = userInfoModelDataMapper;
    }

    public void setView(@NonNull UserInfosView view) {
        this.mUserInfosView = view;
    }

    public void setContext(@NonNull Context context) {
        this.mContext = context;
    }

    private void showErrorMessage(String message) {
        this.mUserInfosView.showError(message);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destory() {
        this.initUserInfosCase.unsubscribe();
        this.mUserInfosView = null;
    }

    public void initialize() {
        this.initUserInfos();
    }

    private void initUserInfos() {
        this.initUserInfosCase
                .initParams(CommonUtilAPP.getMacAddress(mContext.getApplicationContext()),CommonUtilAPP.getDeviceName())
                .execute(new UserInfosSubscriber());
    }

    private final class UserInfosSubscriber extends DefaultSubscriber<List<UserInfo>> {

        @Override
        public void onCompleted() {
            mUserInfosView.initUserSuccess();
        }

        @Override
        public void onError(Throwable e) {
            UserInfoPresenter.this.showErrorMessage(e.getMessage());
        }

        @Override
        public void onNext(List<UserInfo> userInfos) {
            mUserInfosView.renderUserInfo(mUserInfoModelDataMapper.transform(userInfos));
            mUserInfosView.updateProgress(20);
        }
    }

}
