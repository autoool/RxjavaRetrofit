package com.techidea.appclean.base;

/**
 * Created by zchao on 2016/5/18.
 */
public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
    void showError(String message);
}
