package com.techidea.appclean.login;

import android.content.Context;

import com.techidea.appclean.base.BasePresenter;
import com.techidea.appclean.base.BaseView;

/**
 * Created by zchao on 2016/5/20.
 * presenter 导致 view 变化
 */
public class LoginContract {

    //界面调用precenter 方法
    interface Precenter extends BasePresenter{
        void login(String username,String password);
    }

    //precenter 通知 view
    interface View extends BaseView<Precenter>{
        void loginSuccess();
        Context getApplicationContext();
    }
}
