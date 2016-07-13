package com.techidea.appclean.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.techidea.appclean.R;
import com.techidea.appclean.adapter.CommonSpinnerAdapter;
import com.techidea.appclean.adapter.SpinnerItem;
import com.techidea.appclean.base.BaseFragment;
import com.techidea.appclean.main.MainActivity;
import com.techidea.corelibrary.util.CommonUtilAPP;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zchao on 2016/5/19.
 */
public class LoginFragment extends BaseFragment implements LoginContract.View {

    @Bind(R.id.edittext_username)
    EditText mEditTextUsername;
    @Bind(R.id.edittext_password)
    EditText mEditTextPassword;
    @Bind(R.id.spinner_username)
    AppCompatSpinner mAppCompatSpinner;

    private LoginContract.Precenter mPrecenter;
    private Context mContext;
    private CommonSpinnerAdapter mCommonSpinnerAdapter;
    private List<SpinnerItem> mSpinnerItems;

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSpinnerItems = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, root);
        initialize();
        return root;
    }

    private void initialize() {
        mPrecenter.init();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.button_login)
    void buttonLogin() {
        Log.d("Ser", CommonUtilAPP.getDeviceSerial());
        String username = mEditTextUsername.getText().toString().trim();
        String password = mEditTextPassword.getText().toString().trim();
        mPrecenter.login(username, password);
    }

    @Override
    public Context getApplicationContext() {
        return mContext.getApplicationContext();
    }


    @Override
    protected void showToastMessage(String message) {
        super.showToastMessage(message);
    }

    @Override
    public void setPresenter(LoginContract.Precenter presenter) {
        this.mPrecenter = presenter;
    }

    @Override
    public void showError(String message) {
        showToastMessage(message);
    }

    @Override
    public void loginSuccess() {
        mContext.startActivity(new Intent(getActivity(), MainActivity.class));
        showToastMessage("登陆成功");
    }

    @Override
    public void initLoginUsers(List<SpinnerItem> list) {
        mSpinnerItems = list;
        mCommonSpinnerAdapter = new CommonSpinnerAdapter(getApplicationContext(), mSpinnerItems);
        mAppCompatSpinner.setAdapter(mCommonSpinnerAdapter);
    }
}
