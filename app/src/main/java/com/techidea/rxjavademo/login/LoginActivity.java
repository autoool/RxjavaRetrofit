package com.techidea.rxjavademo.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.techidea.rxjavademo.R;
import com.techidea.rxjavademo.main.MainActivity;
import com.techidea.rxjavademo.rxfunction.RxTos;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zchao on 2016/4/5.
 */
public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.edittext_username)
    EditText editTextUsername;
    @Bind(R.id.edittext_password)
    EditText editTextPwd;

    private RxTos rxTos = new RxTos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        editTextUsername.setText("16800000005");
        editTextPwd.setText("555555");


    }


    @OnClick(R.id.button_login)
    void buttonLogin() {

//        HttpMethods.getInstance().testHttps().subscribe(new Subscriber<retrofit2.Response<String>>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(retrofit2.Response<String> stringResponse) {
//                String result = stringResponse.body().toString();
//                Log.d("https response", result);
//            }
//        });
//        Request request = new Request.Builder()
//                .url("https://www.baidu.com")
//                .build();
//        CustomTrust.getInstance().getClient().newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("https exception", e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String res = response.body().toString();
//                Log.d("https", res);
//            }
//        });
//        Request request = new Request.Builder()
//                .url(CommonConcast.BASE_URL_ONE + "users/octocat/repos")
//                .build();
//        OkHttpClient client = new OkHttpClient();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("https exception", e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String res = response.body().toString();
//                Log.d("https", res);
//            }
//        });


//        rxTos.strToInt();
//        rxTos.cast();

//        rxTos.flatMapIterable();
//        rxTos.dematerialize();

//        RxSubject.publish();
//        RxAggregation.interval();


//        Subject<Integer, Integer> values = ReplaySubject.create();
//        Subscription subscription = values.subscribe(new Subscriber<Integer>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                System.out.println(integer);
//            }
//        });
//        values.onNext(0);
//        values.onCompleted();
//        values.onNext(1);
//        subscription.unsubscribe();
//        values.onNext(2);


//        Observable.just("Hello!")
//                .map(input -> {
//                    throw new RuntimeException();
//                })
//                .subscribe(
//                        System.out::println,
//                        error -> System.out.println("Error!")
//                );

        //unchecked异常会自动传递给 onError
//        Observable.just("Hello!")
//                .map(new Func1<String, Object>() {
//                    @Override
//                    public Object call(String s) {
//                        throw new RuntimeException("123");
//                    }
//                })
//                .subscribe(new Subscriber<Object>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(Object o) {
//
//                    }
//                });
//
//        Observable.just("hello!")
//                .map(new Func1<String, String>() {
//                    @Override
//                    public String call(String s) {
//                        try {
//                            return "123";
//                        } catch (Throwable t) {
//                            throw Exceptions.propagate(t);
//                        }
//                    }
//                })
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String o) {
//                        System.out.println(o);
//                    }
//                });

        //注册一次重新执行一次，两次结果不同
//        Observable<Long> now = Observable.defer(new Func0<Observable<Long>>() {
//            @Override
//            public Observable<Long> call() {
//                return Observable.just(System.currentTimeMillis());
//            }
//        });

        //注册两次 两次结果相同
//        Observable<Long> now = Observable.just(System.currentTimeMillis());

//        try {
//            now.subscribe(new Subscriber<Long>() {
//                @Override
//                public void onCompleted() {
//
//                }
//
//                @Override
//                public void onError(Throwable e) {
//
//                }
//
//                @Override
//                public void onNext(Long aLong) {
//                    System.out.println(aLong);
//                }
//            });
//            Thread.sleep(1000);
//            now.subscribe(new Subscriber<Long>() {
//                @Override
//                public void onCompleted() {
//
//                }
//
//                @Override
//                public void onError(Throwable e) {
//
//                }
//
//                @Override
//                public void onNext(Long aLong) {
//                    System.out.println(aLong);
//                }
//            });
//        } catch (Exception e) {
//
//        }

//        HttpMethods.getInstance().doLogin(
//                CommonUtilAPP.getMacAddress(getApplicationContext()),
//                editTextUsername.getText().toString().trim(),
//                editTextPwd.getText().toString().trim())
//                .subscribe(new Subscriber<Response<HttpResult<LoginUserInfo>>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Response<HttpResult<LoginUserInfo>> httpResultResponse) {
//                        Response<HttpResult<LoginUserInfo>> response1 = httpResultResponse;
//                        LoginUserInfo loginUserInfo = response1.body().getObject();
//                    }
//                });
//                .subscribe(new Subscriber<Response<HttpResult<LoginUserInfo>>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Response<HttpResult<LoginUserInfo>> response) {
//                        Response<HttpResult<LoginUserInfo>> response1 = response;
//                        LoginUserInfo loginUserInfo = response1.body().getObject();
//                    }
//                });

//                .flatMap(new Func1<HttpResult<LoginUserInfo>, Observable<String>>() {
//                    @Override
//                    public Observable<String> call(HttpResult<LoginUserInfo> loginUserInfoHttpResult) {
//                        final String name = loginUserInfoHttpResult.getObject().getUsername();
//                        return Observable.create(new Observable.OnSubscribe<String>() {
//                            @Override
//                            public void call(Subscriber<? super String> subscriber) {
//                                subscriber.onNext(name);
//                                subscriber.onCompleted();
//                            }
//                        });
//                    }
//                })
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//                        goMainActivity();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(String o) {
//                        if (!TextUtils.isEmpty(o)) {
//
//                        }
//                    }
//                });
    }

    private void goMainActivity() {
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
