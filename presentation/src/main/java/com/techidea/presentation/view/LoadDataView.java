package com.techidea.presentation.view;

import android.content.Context;

/**
 * Created by zchao on 2016/5/6.
 */
public interface LoadDataView {

    void showLoading();

    void hideLoading();

    void showRetry();

    void hideRetry();

    void showError(String message);

    Context context();
}
