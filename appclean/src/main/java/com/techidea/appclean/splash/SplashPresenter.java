package com.techidea.appclean.splash;

import android.util.Log;

import com.techidea.corelibrary.util.CommonUtilAPP;
import com.techidea.domain.Product;
import com.techidea.domain.ProductCategory;
import com.techidea.domain.UserInfo;
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

    public SplashPresenter(SplashContract.View view,
                           InitProduct initProduct,
                           InitProductCategory initProductCategory,
                           InitLoginUser initLoginUser
    ) {
        mView = view;
        mInitProduct = initProduct;
        mInitProductCategory = initProductCategory;
        mInitLoginUser = initLoginUser;

        mView.setPresenter(this);
    }

    @Override
    public void updateProgress(int progress) {

    }

    @Override
    public void loadDataSuccess() {

    }

    @Override
    public void start() {
        initData();
    }

    private void initData() {
        mInitLoginUser
                .initParams(CommonUtilAPP.getMacAddress(mView.getApplicationContext()),
                        CommonUtilAPP.getDeviceName())
                .execute(new UserInfosSubscriber());
    }

    private final class ProductSubscriber extends
            DefaultSubscriber<List<Product>> {
        @Override
        public void onCompleted() {

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
