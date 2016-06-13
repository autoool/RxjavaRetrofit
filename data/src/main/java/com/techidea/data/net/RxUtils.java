package com.techidea.data.net;


import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
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

   /* public static <T> Observable.Transformer<HttpResult<T>, T> handleResult() {
        return new Observable.Transformer<HttpResult<T>, T>() {
            @Override
            public Observable<T> call(Observable<HttpResult<T>> httpResultObservable) {
                return httpResultObservable.flatMap(new Func1<HttpResult<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(HttpResult<T> tHttpResult) {
                        if (tHttpResult.getCode() == 1) {
                            if (tHttpResult.getObject() != null) {
                                return null;
                            } else if (tHttpResult.getList() != null) {
                                return null;
                            }
                        } else {
                            return Observable.error(new HttpErrorException(tHttpResult.getCode(), tHttpResult.getMsg()))
                        }
                    }
                });
            }
        };
    }*/

}
