package com.techidea.rxjavademo.rxfunction;

import android.util.Log;

import com.techidea.rxjavademo.rx.PrintSubscriber;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by zchao on 2016/4/6.
 * 聚合 Aggregation
 */
public class RxAggregation {


    public static void rxReduce() {
//        Observable<Integer> values = Observable.range(0, 5);

//        values
//                .reduce((i1,i2) -> i1+i2)
//                .subscribe(new PrintSubscriber("Sum"));
//        values
//                .reduce((i1,i2) -> (i1>i2) ? i2 : i1)
//                .subscribe(new PrintSubscriber("Min"));
//        values.reduce(new Func2<Integer, Integer, Integer>() {
//            @Override
//            public Integer call(Integer integer, Integer integer2) {
//                return integer + integer2;
//            }
//        }).subscribe(new PrintSubscriber("sum"));
//
//        values.reduce(new Func2<Integer, Integer, Integer>() {
//            @Override
//            public Integer call(Integer integer, Integer integer2) {
//                return integer > integer2 ? integer2 : integer;
//            }
//        }).subscribe(new PrintSubscriber("min"));

        Observable<String> values = Observable.just("Rx", "is", "easy");
        values.reduce(0, new Func2<Integer, String, Integer>() {
            @Override
            public Integer call(Integer integer, String s) {
                return integer + 1;
            }
        }).subscribe(new PrintSubscriber("Count"));
//        values
//                .reduce(0, (acc,next) -> acc + 1)
//                .subscribe(new PrintSubscriber("Count"));

    }


    public static void rxFirst() {

        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);

        values.first(new Func1<Long, Boolean>() {
            @Override
            public Boolean call(Long aLong) {
                return aLong > 5;
            }
        }).subscribe(new PrintSubscriber("First"));
    }

    //    reduce 可以通过 scan 来实现：
// reduce(acc) = scan(acc).takeLast() 。
// 所以 scan 比 reduce 更加通用。
    public static void rxScan() {
//        Observable<Integer> values = Observable.range(0,5);
//
//        values
//                .scan((i1,i2) -> i1+i2)
//                .subscribe(new PrintSubscriber("Sum"));

//        Subject<Integer, Integer> values = ReplaySubject.create();
//
//        values
//                .subscribe(new PrintSubscriber("Values"));
//        values
//                .scan((i1,i2) -> (i1<i2) ? i1 : i2)
//                .distinctUntilChanged()
//                .subscribe(new PrintSubscriber("Min"));
//
//        values.onNext(2);
//        values.onNext(3);
//        values.onNext(1);
//        values.onNext(4);
//        values.onCompleted();
    }

    public static void rxCollect() {
//        Observable<Integer> values = Observable.range(10,5);
//
//        values
//                .collect(
//                        () -> new ArrayList<Integer>(),
//                        (acc, value) -> acc.add(value))
//                .subscribe(v -> System.out.println(v));
//
//        结果：
//
//        [10, 11, 12, 13, 14]
    }

    public static void rxToList() {
//        Observable<Integer> values = Observable.range(10,5);
//
//        values
//                .toList()
//                .subscribe(v -> System.out.println(v));
    }

    public static void rxToStoreList() {
        Observable<Integer> values = Observable.range(10, 5);
        values.toSortedList(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer - integer2;
            }
        }).subscribe(new PrintSubscriber(""));

    }

    public static void createOb() {

        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 4; i++)
                    subscriber.onNext(i);
                subscriber.onError(new RuntimeException());
                subscriber.onCompleted();
            }
        }).doOnCompleted(new Action0() {
            @Override
            public void call() {
                Log.d("call", "observable complete");
            }
        }).doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d("call", "error");
            }
        });
//        observable.subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.d("call", String.valueOf(integer));
//            }
//        });
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("call", String.valueOf(integer));
            }
        });
        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("call", String.valueOf(integer));
            }
        });

/*        Observable<Object> observable = Observable.create(new  Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {

            }
        });
        observable.subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        });

        observable.subscribe(new Observer<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        });*/
    }

    public static void interval() {

        Subscription stopMePlease = Observable.interval(3, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {

                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long number) {
                        Log.d("interval", String.valueOf(number));
                    }
                });

    }


}
