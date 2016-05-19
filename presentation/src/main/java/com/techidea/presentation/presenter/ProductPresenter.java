package com.techidea.presentation.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.techidea.corelibrary.util.CommonUtilAPP;
import com.techidea.domain.entity.Product;
import com.techidea.domain.interactor.DefaultSubscriber;
import com.techidea.domain.interactor.RxBaseCase;
import com.techidea.presentation.internal.di.PerActivity;
import com.techidea.presentation.mapper.ProductModelMapper;
import com.techidea.presentation.view.ProductView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by zchao on 2016/5/12.
 */
@PerActivity
public class ProductPresenter implements Presenter {


    private Context mContext;
    private ProductView mProductView;

    private final RxBaseCase mInitProductCase;
    private final ProductModelMapper mProductModelMapper;

    @Inject
    public ProductPresenter(@Named("initProduct") RxBaseCase initProductCase,
                            ProductModelMapper productModelMapper) {
        this.mInitProductCase = initProductCase;
        this.mProductModelMapper = productModelMapper;
    }

    public void setView(@NonNull ProductView productView) {
        this.mProductView = productView;
    }

    public void setContext(@NonNull Context context) {
        this.mContext = context;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destory() {
        this.mInitProductCase.unsubscribe();
        this.mProductView = null;
    }

    public void initiallize() {
        this.initProduct();
    }


    private void initProduct() {
        this.mInitProductCase
                .initParams(CommonUtilAPP.getMacAddress(mContext.getApplicationContext()),
                        CommonUtilAPP.getDeviceName())
                .execute(new ProductSubscriber());
    }

    private final class ProductSubscriber extends
            DefaultSubscriber<List<Product>> {
        @Override
        public void onCompleted() {
            mProductView.initProductSuccess();
        }

        @Override
        public void onError(Throwable e) {
            mProductView.showError(e.getMessage());
        }

        @Override
        public void onNext(List<Product> products) {
            mProductView.renderProduct(
                    mProductModelMapper.transformProducts(products));
            mProductView.updateProgress(100);
        }
    }

}
