package com.techidea.presentation.view.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.techidea.corelibrary.util.CommonUtilAPP;
import com.techidea.presentation.R;
import com.techidea.presentation.base.BaseFragment;
import com.techidea.presentation.internal.di.components.FragmentComponent;
import com.techidea.presentation.presenter.LoginPresenter;
import com.techidea.presentation.view.LoginView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.observers.TestObserver;

/**
 * Created by zchao on 2016/5/5.
 */
public class LoginFragment extends BaseFragment implements LoginView {

    @Bind(R.id.button_login)
    Button mButtonLogin;
    @Bind(R.id.edittext_username)
    EditText mEditTextUsername;
    @Bind(R.id.edittext_password)
    EditText mEditTextPassword;

    public interface LoginListener {
        void loginSuccess();
    }

    @Inject
    LoginPresenter mLoginPresenter;

    private LoginListener mLoginListener;

    private String username;
    private String password;
    private String deviceId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(FragmentComponent.class).inject(this);

    }

    private void initialize() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof LoginListener)
            mLoginListener = (LoginListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mLoginPresenter.setLoginView(this);
        this.mLoginPresenter.setContext(getActivity().getApplicationContext());
    }

    @OnClick(R.id.button_login)
    void buttonLogin() {
        username = mEditTextUsername.getText().toString().trim();
        password = mEditTextPassword.getText().toString().trim();
        deviceId = CommonUtilAPP.getMacAddress(getActivity().getApplicationContext());
        mLoginPresenter.setUsername(username);
        mLoginPresenter.setPassword(password);
        mLoginPresenter.setDeviceId(deviceId);
        if (mLoginListener != null) {
            if (mLoginPresenter != null)
                mLoginPresenter.initialize();
        }
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
    public void loginSuccess() {
        if (mLoginListener != null)
            mLoginListener.loginSuccess();
        Toast.makeText(getActivity().getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return null;
    }
}
