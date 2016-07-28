package com.techidea.appclean.splash;

import android.util.Log;

import com.techidea.corelibrary.CommonUtilAPP;
import com.techidea.domain.entity.Product;
import com.techidea.domain.entity.ProductCategory;
import com.techidea.domain.entity.UserInfo;
import com.techidea.domain.interactor.DefaultSubscriber;
import com.techidea.domain.interactor.InitLoginUser;
import com.techidea.domain.interactor.InitProduct;
import com.techidea.domain.interactor.InitProductCategory;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by zchao on 2016/5/18.
 */
public class SplashPresenter implements SplashContract.Presenter {

    private final SplashContract.View mView;
    private final InitProduct mInitProduct;
    private final InitProductCategory mInitProductCategory;
    private final InitLoginUser mInitLoginUser;

    private final ProductSubscriber mProductSubscriber;

    public SplashPresenter(SplashContract.View view,
                           InitProduct initProduct,
                           InitProductCategory initProductCategory,
                           InitLoginUser initLoginUser
    ) {
        mView = view;
        mInitProduct = initProduct;
        mInitProductCategory = initProductCategory;
        mInitLoginUser = initLoginUser;
        mProductSubscriber = new ProductSubscriber();
        mView.setPresenter(this);
    }


    @Override
    public void start() {
        initData();
    }


    public void destory() {
        if (mProductSubscriber != null
                && !mProductSubscriber.isUnsubscribed())
            mProductSubscriber.unsubscribe();
    }

    private void initData() {
        mInitLoginUser.initParams(CommonUtilAPP.getMacAddress(mView.context()),
                CommonUtilAPP.getDeviceName());
        mInitLoginUser.execute()
                .flatMap(new Func1<List<UserInfo>, Observable<List<ProductCategory>>>() {
                    @Override
                    public Observable<List<ProductCategory>> call(List<UserInfo> userInfoList) {
                        // TODO: 2016/7/28 do something with data

                        mView.refreshProgress(40);
                        mInitProductCategory.initParams(CommonUtilAPP.getMacAddress(mView.context()),
                                CommonUtilAPP.getDeviceName());
                        return mInitProductCategory.execute();
                    }
                })
                .flatMap(new Func1<List<ProductCategory>, Observable<List<Product>>>() {
                    @Override
                    public Observable<List<Product>> call(List<ProductCategory> productCategories) {
                        // TODO: 2016/7/28 do something with data

                        mView.refreshProgress(70);
                        mInitProduct.initParams(CommonUtilAPP.getMacAddress(mView.context()),
                                CommonUtilAPP.getDeviceName());
                        return mInitProduct.execute();
                    }
                })
                .subscribe(mProductSubscriber);
    }


    private final class ProductSubscriber extends DefaultSubscriber<List<Product>> {
        @Override
        public void onCompleted() {
            mView.refreshProgress(100);
            mView.goLogin();
        }

        @Override
        public void onError(Throwable e) {
            mView.showError(e.getMessage());
        }

        @Override
        public void onNext(List<Product> products) {
            for (Product item : products) {
                Log.d("Product", item.getTitle());
            }
        }
    }
}
