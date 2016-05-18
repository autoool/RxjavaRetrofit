package com.techidea.rxjavademo.rxfunction;

import com.techidea.rxjavademo.rx.PrintSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by zchao on 2016/4/8.
 * Transformation of sequences
 * 事件流基础之 转换数据流
 */
public class RxTos {

    public RxTos() {
    }

    public void strToInt() {
        Observable<Integer> values = Observable.just("1", "2", "3")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return Integer.valueOf(s);
                    }
                });
        values.subscribe(new PrintSubscriber("map"));
    }

    public void cast() {
        Observable<Integer> values = Observable.just(1, 2, 3, 4);

        values.cast(Integer.class).subscribe(new PrintSubscriber("map"));
    }

    public void castError() {
//        Observable<Object> values = Observable.just(1, 2, 3, "4");
//        values.cast(Integer.class).subscribe(new PrintSubscriber("map"));

//        Observable<Object> values = Observable.just(0, 1, 2, "3").cast(Integer.class)
//                .subscribe(new PrintSubscriber("Map"));
    }

    public void ofType() {
//        Observable<Object> values = Observable.just(1, 2, 3, "4");
//        values.cast(Integer.class).subscribe(new PrintSubscriber("map"));
    }

    //给数据流中的数据添加额外的时间相关的信息
    public void timestamp() {
        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);

        values.take(3)
                .timestamp()
                .subscribe(new PrintSubscriber("Timestamp"));
    }

    //前一个数据和当前数据发射直接的时间间隔
    public void interval() {
        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);

        values.take(3)
                .timeInterval()
                .subscribe(new PrintSubscriber("TimeInterval"));
    }

    public void materialize() {
        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);

        values.take(3)
                .materialize()
                .dematerialize()
                .subscribe(new PrintSubscriber("Materialize"));
    }

    public void dematerialize() {
        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);

        values.take(3)
                .dematerialize()
                .subscribe(new PrintSubscriber("Materialize"));
    }

    public void flatMap() {
        Observable<Integer> values = Observable.range(1, 3);

        values
                .flatMap(new Func1<Integer, Observable<?>>() {
                    @Override
                    public Observable<?> call(Integer integer) {
                        return Observable.range(0, integer);
                    }
                })
                .subscribe(new PrintSubscriber("flatMap"));

//        Observable<Integer> values = Observable.just(1);

 /*       values
                .flatMap(i ->
                        Observable.just(
                                Character.valueOf((char)(i+64))
                        ))
                .subscribe(new PrintSubscriber("flatMap"));*/
    }

    //    该函数会等第一个新的 Observable 完成后再发射下一个 新的 Observable 中的数据。
    public void concatMap() {
        Observable.just(100, 150)
                .concatMap(new Func1<Integer, Observable<?>>() {
                    @Override
                    public Observable<?> call(final Integer integer) {
                        return Observable.interval(integer, TimeUnit.MILLISECONDS)
                                .map(new Func1<Long, Integer>() {
                                    @Override
                                    public Integer call(Long aLong) {
                                        return integer;
                                    }
                                }).take(3);
                    }
                }).subscribe(new PrintSubscriber("concatmap"));
        Observable.just(100, 200)
                .subscribe(new PrintSubscriber("just"));


    }

    public void flatMapIterable() {
        //        flatMapIterable 还有另外一个重载函数可以用源 Observable 发射的数据来处理新的 iterable 中的每个数据
        Observable.range(1, 3)
                .flatMapIterable(new Func1<Integer, Iterable<?>>() {
                    @Override
                    public Iterable<?> call(Integer integer) {
                        return range(1, integer);
                    }
                }).subscribe(new PrintSubscriber("iterable"));

//        flatMapIterable 还有另外一个重载函数可以用源 Observable 发射的数据来处理新的 iterable 中的每个数据：
/*        Observable.range(1, 3)
                .flatMapIterable(
                        i -> range(1, i),
                        (ori, rv) -> ori * (Integer) rv)
                .subscribe(System.out::println);*/


    }

    public Iterable<Integer> range(int start, int count) {
        List<Integer> list = new ArrayList<>();
        for (int i = start; i < start + count; i++) {
            list.add(i);
        }
        return list;
    }
}
