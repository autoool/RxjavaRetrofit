package com.techidea.rxjavademo.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import com.techidea.corelibrary.util.CommonUtilAPP;
import com.techidea.rxjavademo.R;
import com.techidea.rxjavademo.itemadapter.Product;
import com.techidea.rxjavademo.itemadapter.ProductCategory;
import com.techidea.rxjavademo.login.LoginActivity;
import com.techidea.rxjavademo.model.HttpResult;
import com.techidea.rxjavademo.model.UserInfo;
import com.techidea.rxjavademo.network.HttpMethods;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by zchao on 2016/4/1.
 */
public class SplashActivity extends AppCompatActivity {

    @Bind(R.id.progressbar_init)
    ProgressBar progressBarInit;

    private static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        progressBarInit.setMax(100);
        progressBarInit.setProgress(0);
        flatmapCallback();

    }


    private void flatmapCallback() {

        Observable<HttpResult<List<Product>>> observableInit =
                HttpMethods.getInstance().initLoginUsers(
                CommonUtilAPP.getMacAddress(getApplicationContext())
                , CommonUtilAPP.getDeviceName())
                .flatMap(new Func1<HttpResult<List<UserInfo>>, Observable<HttpResult<List<ProductCategory>>>>() {
                    @Override
                    public Observable<HttpResult<List<ProductCategory>>> call(HttpResult<List<UserInfo>> listHttpResult) {
                        List<UserInfo> userInfoList = listHttpResult.getList();
                        for (UserInfo item : userInfoList)
                            System.out.println(item.getUsername());
                        updateProgress(5);
                        return HttpMethods.getInstance().initProductCategories(
                                CommonUtilAPP.getMacAddress(getApplicationContext()),
                                CommonUtilAPP.getDeviceName());
                    }
                })
                .flatMap(new Func1<HttpResult<List<ProductCategory>>, Observable<HttpResult<List<Product>>>>() {
                    @Override
                    public Observable<HttpResult<List<Product>>> call(HttpResult<List<ProductCategory>> listHttpResult) {
                        List<ProductCategory> productCategoryList = listHttpResult.getList();
                        for (ProductCategory item : productCategoryList)
                            System.out.println(item.getName());
                        updateProgress(50);
                        return HttpMethods.getInstance().initProducts(
                                CommonUtilAPP.getMacAddress(getApplicationContext()));
                    }
                });
        observableInit.subscribe(new Subscriber<HttpResult<List<Product>>>() {
            @Override
            public void onCompleted() {
                Log.d("onCompleted", String.valueOf(count++));
                updateProgress(100);
                goLoginActivity();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("onError", e.getMessage());
            }

            @Override
            public void onNext(HttpResult<List<Product>> listHttpResult) {
                List<Product> productList = listHttpResult.getList();
                for (Product item : productList)
                    System.out.println(item.getTitle());

            }
        });
    }


    //    使用RxJava把不同异步操作合并?
    private void mergeAsyncTask() {

    /*    Observable<HttpResult<List<Product>>> observableMergeInit = HttpMethods.getInstance().initLoginUsers(
                CommonUtilAPP.getMacAddress(getApplicationContext())
                , CommonUtilAPP.getDeviceName())*/

        Observable<HttpResult<List<Product>>> observableMergeInit = null;


        observableMergeInit.subscribe(new Subscriber<HttpResult<List<Product>>>() {
            @Override
            public void onCompleted() {
                Log.d("onCompleted", String.valueOf(count++));
                updateProgress(100);
                goLoginActivity();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("onError", e.getMessage());
            }

            @Override
            public void onNext(HttpResult<List<Product>> listHttpResult) {
                List<Product> productList = listHttpResult.getList();
                for (Product item : productList)
                    System.out.println(item.getTitle());
            }
        });
    }

    private void goLoginActivity() {
        Intent intent = new Intent();
        intent.setClass(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void updateProgress(int count) {
        progressBarInit.setProgress(count);
    }

//    一个接口的请求依赖另一个API请求返回的数据
/*   NetworkService.getToken("username", "password")
    .flatMap(s -> NetworkService.getMessage(s))
            .subscribe(s -> {
        System.out.println("message: " + s);
    });*/


//    private void test1() {
//        Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> observer) {
//                try {
//                    if (!observer.isUnsubscribed()) {
//                        for (int i = 0; i < 5; i++) {
//                            observer.onNext(i);
//                        }
//                        observer.onCompleted();
//                    }
//                } catch (Exception e) {
//                    observer.onError(e);
//                }
//            }
//        }).subscribe(new Subscriber<Integer>() {
//            @Override
//            public void onCompleted() {
//                System.out.println("Sequence complete.");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d("onError", String.valueOf(""));
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.d("onNext", String.valueOf(integer));
//            }
//        });
//    }
}
