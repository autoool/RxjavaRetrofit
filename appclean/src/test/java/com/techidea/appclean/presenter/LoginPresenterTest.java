package com.techidea.appclean.presenter;

import android.content.Context;
import android.test.AndroidTestCase;

import com.techidea.appclean.login.LoginContract;
import com.techidea.appclean.login.LoginPresenter;
import com.techidea.domain.interactor.InitLoginUser;
import com.techidea.domain.interactor.Login;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Subscriber;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by Administrator on 2016/7/17.
 */
public class LoginPresenterTest extends AndroidTestCase {

    private LoginPresenter mLoginPresenter;
    @Mock
    private Context mContext;
    @Mock
    private LoginContract.View loginView;
    @Mock
    private Login mLogin;
    @Mock
    private InitLoginUser mInitLoginUser;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        mLoginPresenter = new LoginPresenter(loginView, mLogin, mInitLoginUser);
        loginView.setPresenter(mLoginPresenter);
    }

    public void testInitLoginUsers() {
        given(loginView.context()).willReturn(mContext);
        mLoginPresenter.init(anyString(), anyString());
        mLoginPresenter.initUserInfos(anyList());
        verify(loginView).showError(anyString());
        verify(loginView).initLoginUsers(anyList());
        verify(mInitLoginUser).initParams(anyString(), anyString());
        verify(mInitLoginUser).execute(any(Subscriber.class));
    }

    public void testLogin() {
        given(loginView.context()).willReturn(mContext);
        mLoginPresenter.login("132", "123");
        verify(mLogin).initParams(anyString(),anyString(), anyString());
        verify(mLogin).execute(any(Subscriber.class));
    }

}
