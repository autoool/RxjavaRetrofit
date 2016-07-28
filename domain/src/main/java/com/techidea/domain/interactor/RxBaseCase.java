package com.techidea.domain.interactor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by zchao on 2016/5/5.
 */
public abstract class RxBaseCase<T> {

    protected RxBaseCase() {
    }

    public abstract RxBaseCase initParams(String... paras);

    public abstract Observable<T> execute();

}
