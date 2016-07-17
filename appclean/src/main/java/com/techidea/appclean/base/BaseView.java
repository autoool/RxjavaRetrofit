package com.techidea.appclean.base;

import android.content.Context;

/**
 * Created by zchao on 2016/5/18.
 */
public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
    void showError(String message);
    Context context();
}
