package com.techidea.presentation.clean.splash;

import android.content.Context;

import com.techidea.presentation.clean.base.BasePresenter;
import com.techidea.presentation.clean.base.BaseView;

/**
 * Created by zchao on 2016/5/18.
 */
public interface CSplashContract {

    interface Presenter extends BasePresenter {
        void updateProgress(int progress);
        void loadDataSuccess();
    }

    interface View extends BaseView<Presenter> {
        void refreshProgress(int progress);
        Context getApplicationContext();
    }
}
