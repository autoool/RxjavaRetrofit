package com.techidea.appclean.login;

import android.content.Context;

import com.techidea.appclean.adapter.SpinnerItem;
import com.techidea.appclean.base.BasePresenter;
import com.techidea.appclean.base.BaseView;

import java.util.List;

/**
 * Created by zchao on 2016/5/20.
 * presenter 导致 view 变化
 */
public class LoginContract {

    //界面调用precenter 方法
    interface Precenter extends BasePresenter{
        void login(String username,String password);
        void init();
    }

    //precenter 通知 view
    interface View extends BaseView<Precenter>{
        void initLoginUsers(List<SpinnerItem> list);
        void loginSuccess();
        Context getApplicationContext();
    }
}
