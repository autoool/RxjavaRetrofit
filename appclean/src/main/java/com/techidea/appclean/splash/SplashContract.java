package com.techidea.appclean.splash;

import android.content.Context;

import com.techidea.appclean.base.BasePresenter;
import com.techidea.appclean.base.BaseView;

/**
 * Created by zchao on 2016/5/18.
 */
public interface SplashContract {

    interface Presenter extends BasePresenter {
        void updateProgress(int progress);
        void loadDataSuccess();
    }

    interface View extends BaseView<Presenter> {
        void refreshProgress(int progress);
        Context getApplicationContext();
    }
}
