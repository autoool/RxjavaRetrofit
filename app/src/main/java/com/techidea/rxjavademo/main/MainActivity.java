package com.techidea.rxjavademo.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.techidea.rxjavademo.R;
import com.techidea.rxjavademo.model.UserInfo;


import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.textview_log)
    TextView textViewLog;

    @Inject
    UserInfo mUserInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }



    private void doLogin() {
        Toast.makeText(getApplicationContext(),
                "login", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button_login)
    void buttonLogin() {
        doLogin();
//        textViewLog.setText("");
//
//        HttpMethods.getInstance().doLogin(subscriberLogin,
// CommonUtilAPP.getMacAddress(getApplicationContext()),
//                "16800000005", "5555555");

   /*     subscriberuser = new Subscriber<List<UserInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<UserInfo> response) {
                for (UserInfo item : response) {
                    textViewLog.append(item.getUsername() + "\n");
                    Log.d("number: ", String.valueOf(item.getUsername()));
                }
            }
        };*/
//        HttpMethods.getInstance().listRepos(subscriber, "octocat");
/*        HttpMethods.getInstance().initLoginUsers(subscriberuser,
                CommonUtilAPP.getMacAddress(getApplicationContext()),
                CommonUtilAPP.getDeviceName());*/

//        Call<HttpResult<List<UserInfo>>> callusers = service.callinitLoginUsers(CommonUtilAPP.getMacAddress(getApplicationContext()),
//                CommonUtilAPP.getDeviceName());
//        callusers.enqueue(new Callback<HttpResult<List<UserInfo>>>() {
//            @Override
//            public void onResponse(Call<HttpResult<List<UserInfo>>> call, Response<HttpResult<List<UserInfo>>> response) {
//                for (UserInfo userInfo : response.body().getList()) {
//                    textViewLog.append(userInfo.getUsername() + "\n");
//                    Log.d("number: ", response.body().toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<HttpResult<List<UserInfo>>> call, Throwable t) {
//
//            }
//        });

//        service.listRepos("octocat")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<List<Repo>>() {
//                               @Override
//                               public void onCompleted() {
//
//                               }
//
//                               @Override
//                               public void onError(Throwable e) {
//
//                               }
//
//                               @Override
//                               public void onNext(List<Repo> repos) {
//                                   for (Repo item : repos) {
//                                       Log.d("number: ", String.valueOf(item.getName()));
//                                   }
//                               }
//                           }
//
//                );

//        Call<List<Repo>> call = service.getUserInfos();
//        call.enqueue(new Callback<List<Repo>>() {
//            @Override
//            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
//                for (Repo item : response.body()) {
//                    Log.d("number: ", String.valueOf(item.getName()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Repo>> call, Throwable t) {
//
//            }
//        });


//      Observable.just(1, 2, 3, 4)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        Log.d("number: ", String.valueOf(integer));
//                    }
//                });

//        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("Hello");
//                subscriber.onNext("Hi");
//                subscriber.onNext("Aloha");
//                subscriber.onCompleted();
//            }
//        });
//        observable.subscribe(new Action1<String>() {
//            @Override
//            public void call(String o) {
//                Log.d("number: ", String.valueOf(o));
//            }
//        });

//        Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("Hello");
//                subscriber.onNext("Hi");
//                subscriber.onNext("Aloha");
//                subscriber.onCompleted();
//            }
//        }).subscribe(new Observer<String>() {
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
//            public void onNext(String s) {
//                Log.d("number: ", String.valueOf(s));
//            }
//        });
    }

    @OnClick(R.id.button_getlist)
    void buttonGetList() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
