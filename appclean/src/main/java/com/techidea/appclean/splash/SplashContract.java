package com.techidea.appclean.splash;

import android.content.Context;

import com.techidea.appclean.base.BasePresenter;
import com.techidea.appclean.base.BaseView;

/**
 * Created by zchao on 2016/5/18.
 */
public class SplashContract {

    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {
        void refreshProgress(int progress);
        void goLogin();
        Context getApplicationContext();
    }
}
