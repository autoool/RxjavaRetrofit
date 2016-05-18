package com.techidea.presentation.view.splash;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.techidea.presentation.R;
import com.techidea.presentation.base.BaseFragment;
import com.techidea.presentation.internal.di.components.FragmentComponent;
import com.techidea.presentation.model.ProductCategoryModel;
import com.techidea.presentation.model.ProductModel;
import com.techidea.presentation.model.UserInfoModel;
import com.techidea.presentation.presenter.ProductCategoryPresenter;
import com.techidea.presentation.presenter.ProductPresenter;
import com.techidea.presentation.presenter.UserInfoPresenter;
import com.techidea.presentation.view.ProductView;
import com.techidea.presentation.view.UserInfosView;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by zchao on 2016/5/6.
 */
public class SplashFragment extends BaseFragment implements UserInfosView, ProductView {

    @Bind(R.id.button_test)
    Button mButtonTest;
    @Bind(R.id.button_jump)
    Button mButtonJump;
    @Bind(R.id.progressbar_load)
    ProgressBar mProgressBarLoad;
    @Bind(R.id.textview_log)
    TextView mTextViewLog;

    public interface SplashListener {
        void jumpLogin();
    }

    @Inject
    UserInfoPresenter mUserInfoPresenter;
    @Inject
    ProductPresenter mProductPresenter;
    @Inject
    ProductCategoryPresenter mProductCategoryPresenter;

    private SplashListener mSplashListener;

    public SplashFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof SplashListener)
            this.mSplashListener = (SplashListener) context;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//       为fragment注入对象
        this.getComponent(FragmentComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.bind(this, fragmentView);
        mProgressBarLoad.setProgress(0);
        mProgressBarLoad.setMax(100);
        RxView.clicks(mButtonTest).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                buttonTestClick();
            }
        });
        RxView.clicks(mButtonJump).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                if (mSplashListener != null)
                    mSplashListener.jumpLogin();
            }
        });
        return fragmentView;
    }

    @OnClick(R.id.button_productcategory)
    void initProductCategory() {
        if (this.mProductCategoryPresenter != null)
            this.mProductCategoryPresenter.initialize();
    }

    @OnClick(R.id.button_product)
    void initProduct() {
        if (mProductPresenter != null)
            this.mProductPresenter.initiallize();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.mUserInfoPresenter.setView(this);
        this.mUserInfoPresenter.setContext(getActivity().getApplicationContext());

        this.mProductPresenter.setView(this);
        this.mProductPresenter.setContext(getActivity().getApplicationContext());

        this.mProductCategoryPresenter.setView(this);
        this.mProductCategoryPresenter.setContext(getActivity().getApplicationContext());

        if (savedInstanceState == null)
            this.initData();
    }


    private void buttonTestClick() {
        SplashFragment.this.initData();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void renderUserInfo(Collection<UserInfoModel> userInfoModels) {
        for (UserInfoModel item : userInfoModels) {
            Log.d("userinfomodel", item.getUsername());
            mTextViewLog.append(item.getUsername() + "\n");
        }
    }

    @Override
    public void renderProductCategory(Collection<ProductCategoryModel> productCategoryModel) {
        for (ProductCategoryModel item : productCategoryModel) {
            Log.d("ProductCategoryModel", item.getName());
            mTextViewLog.append("ProductCategory " + item.getName() + "\n");
        }
    }

    @Override
    public void renderProduct(Collection<ProductModel> ProductModel) {
        for (ProductModel item : ProductModel) {
            Log.d("ProductModel", item.getId() + " " + item.getTitle() + " " + item.getType());
            mTextViewLog.append("ProductModel " + item.getId() + " " + item.getTitle() + " " + item.getType() + "\n");
        }
    }

    @Override
    public void updateProgress(int progress) {
        mProgressBarLoad.setProgress(progress);
    }

    @Override
    public void initUserSuccess() {
        if (mProductCategoryPresenter != null)
            mProductCategoryPresenter.initialize();
    }

    @Override
    public void initProductCategorySuccess() {
        if (mProductPresenter != null)
            mProductPresenter.initiallize();
    }

    @Override
    public void initProductSuccess() {
        if (mSplashListener != null)
            mSplashListener.jumpLogin();
    }

    @Override
    public void viewUser(UserInfoModel userInfoModel) {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }

    @Override
    public void showRetry() {

    }


    @Override
    public void hideRetry() {

    }

    private void initData() {
        if (mUserInfoPresenter != null)
            mUserInfoPresenter.initialize();
    }


}
