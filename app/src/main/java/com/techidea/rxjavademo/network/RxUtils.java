package com.techidea.rxjavademo.network;

import com.techidea.rxjavademo.common.PosLog;
import com.techidea.rxjavademo.model.HttpResult;

import retrofit2.adapter.rxjava.Result;
import rx.Observable;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;
import rx.schedulers.Schedulers;

/**
 * Created by zchao on 2016/6/12.
 */
public class RxUtils {

    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> Observable.Transformer<HttpResult<T>, T> handleResult() {
        return new Observable.Transformer<HttpResult<T>, T>() {
            @Override
            public Observable<T> call(Observable<HttpResult<T>> httpResultObservable) {
                return httpResultObservable.flatMap(new Func1<HttpResult<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(HttpResult<T> tHttpResult) {
                        if (tHttpResult.getCode() == 1) {
                            return null;
                        } else {
                            return Observable.error(new HttpErrorException(tHttpResult.getMsg()));
                        }
                    }
                });
            }
        };
    }

    public static void unifiedErrorHandler() {
        RxJavaPlugins.getInstance().registerErrorHandler(new RxJavaErrorHandler() {
            @Override
            public void handleError(Throwable e) {
                PosLog.e(e.toString());
            }
        });
    }
}
