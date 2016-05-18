package com.techidea.rxjavademo.rxfunction;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subjects.PublishSubject;

/**
 * Created by zchao on 2016/4/7.
 */
public class RxSubject {

    public static void publish(){
//        PublishSubject<String> stringPublishSubject = PublishSubject.create();
//        Subscription subscriptionPrint = stringPublishSubject.subscribe(new Observer<String>() {
//            @Override
//            public void onCompleted() {
//                System.out.println("Observable completed");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("Oh,no!Something wrong happened!");
//            }
//
//            @Override
//            public void onNext(String message) {
//                System.out.println(message);
//            }
//        });
//        stringPublishSubject.onNext("Hello World");

        final PublishSubject<Boolean> subject = PublishSubject.create();

        subject.subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                System.out.println("Observable Completed");
            }
        });

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).doOnCompleted(new Action0() {
            @Override
            public void call() {
                subject.onNext(true);
            }
        }).subscribe();
    }
}
