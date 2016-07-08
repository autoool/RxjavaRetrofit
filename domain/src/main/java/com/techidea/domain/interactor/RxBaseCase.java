package com.techidea.domain.interactor;

import com.techidea.domain.executor.PostExecutionThread;
import com.techidea.domain.executor.ThreadExecutor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by zchao on 2016/5/5.
 */
public abstract class RxBaseCase {

    //这样的写法感觉非常不好
    private Subscription mSubscription = Subscriptions.empty();

    protected RxBaseCase() {
    }

    protected abstract Observable buildCaseObservable();

    protected abstract RxBaseCase initParams(String... paras);

    public void execute(Subscriber subscriber) {
        this.mSubscription = this.buildCaseObservable()
                .subscribe(subscriber);
    }

    public void unsubscribe() {
        if (!mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
    }
}
