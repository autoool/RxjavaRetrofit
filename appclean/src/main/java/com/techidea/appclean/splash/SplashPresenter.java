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

/**
 * Created by zchao on 2016/5/18.
 */
public class SplashPresenter implements SplashContract.Presenter {

    private final SplashContract.View mView;
    private final InitProduct mInitProduct;
    private final InitProductCategory mInitProductCategory;
    private final InitLoginUser mInitLoginUser;

    private final UserInfosSubscriber mUserInfosSubscriber;
    private final ProductCategorySubscriber mProductCategorySubscriber;
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
        mUserInfosSubscriber = new UserInfosSubscriber();
        mProductCategorySubscriber = new ProductCategorySubscriber();
        mProductSubscriber = new ProductSubscriber();
        mView.setPresenter(this);
    }


    @Override
    public void start() {
        initData();
    }


    public void destory() {
        if (mInitLoginUser != null)
            mInitLoginUser.unsubscribe();
        if (mInitProduct != null)
            mInitProduct.unsubscribe();
        if (mInitProductCategory != null)
            mInitProductCategory.unsubscribe();
    }

    private void initData() {
        mInitLoginUser
                .initParams(CommonUtilAPP.getMacAddress(mView.getApplicationContext()),
                        CommonUtilAPP.getDeviceName())
                .execute(new UserInfosSubscriber());
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

    private final class UserInfosSubscriber extends DefaultSubscriber<List<UserInfo>> {

        @Override
        public void onCompleted() {
            mView.refreshProgress(10);
            mInitProductCategory
                    .initParams(CommonUtilAPP.getMacAddress(mView.getApplicationContext()),
                            CommonUtilAPP.getDeviceName())
                    .execute(new ProductCategorySubscriber());
        }

        @Override
        public void onError(Throwable e) {
            mView.showError(e.getMessage());
        }

        @Override
        public void onNext(List<UserInfo> userInfos) {
            for (UserInfo item : userInfos) {
                Log.d("UserInfo", item.getUsername());
            }
        }
    }


    private final class ProductCategorySubscriber extends DefaultSubscriber<List<ProductCategory>> {
        @Override
        public void onCompleted() {
            mView.refreshProgress(50);
            mInitProduct
                    .initParams(CommonUtilAPP.getMacAddress(mView.getApplicationContext()),
                            CommonUtilAPP.getDeviceName())
                    .execute(new ProductSubscriber());
        }

        @Override
        public void onError(Throwable e) {
            mView.showError(e.getMessage());
        }

        @Override
        public void onNext(List<ProductCategory> productCategories) {
            for (ProductCategory item : productCategories) {
                Log.d("ProductCategory", item.getName());
            }
        }
    }
}
