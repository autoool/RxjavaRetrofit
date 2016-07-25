package com.techidea.appclean.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;

import com.techidea.appclean.R;
import com.techidea.appclean.adapter.CommonSpinnerAdapter;
import com.techidea.appclean.adapter.SpinnerItem;
import com.techidea.appclean.base.BaseFragment;
import com.techidea.appclean.main.MainActivity;
import com.techidea.corelibrary.CommonUtilAPP;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zchao on 2016/5/19.
 */
public class LoginFragment extends BaseFragment implements LoginContract.View {

    @Bind(R.id.edittext_password)
    EditText mEditTextPassword;
    @Bind(R.id.spinner_username)
    AppCompatSpinner mAppCompatSpinner;
    @Bind(R.id.coordinatorLayout_login)
    CoordinatorLayout mCoordinatorLayout;

    private LoginContract.Presenter mPrecenter;
    private Context mContext;
    private CommonSpinnerAdapter mCommonSpinnerAdapter;
    private List<SpinnerItem> mSpinnerItems;
    private String username;
    private String password;
    private Toolbar mToolbar;

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
        return root;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.login_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void initialize() {
        mPrecenter.init(CommonUtilAPP.getMacAddress(context()),
                CommonUtilAPP.getDeviceName());
        mAppCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                username = mSpinnerItems.get(position).getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = initToolbar(R.id.toolbar_login);
        initialize();
    }

    @OnClick(R.id.button_login)
    void buttonLogin() {
        String password = mEditTextPassword.getText().toString().trim();
        mPrecenter.login(username, password);
    }

    @OnClick(R.id.button_jump)
    void buttonJump() {
        startActivity(new Intent(getActivity(), MainActivity.class));
    }

    @Override
    public Context context() {
        return mContext.getApplicationContext();
    }


    @Override
    protected void showToastMessage(String message) {
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mPrecenter = presenter;
    }

    @Override
    public void showError(String message) {
        showMsg(message);
    }

    @Override
    public void loginSuccess() {
        mContext.startActivity(new Intent(getActivity(), MainActivity.class));
        showMsg("登陆成功");
    }

    @Override
    public void initLoginUsers(List<SpinnerItem> list) {
        mSpinnerItems = list;
        mCommonSpinnerAdapter = new CommonSpinnerAdapter(context(), mSpinnerItems);
        mAppCompatSpinner.setAdapter(mCommonSpinnerAdapter);
    }

    public void showMsg(String msg) {
        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, msg, Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }
}
