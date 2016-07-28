package com.techidea.appclean.presenter;

import android.content.Context;
import android.test.AndroidTestCase;

import com.google.common.eventbus.Subscribe;
import com.techidea.appclean.login.LoginContract;
import com.techidea.appclean.login.LoginPresenter;
import com.techidea.data.net.HttpResult;
import com.techidea.domain.entity.UserInfo;
import com.techidea.domain.interactor.InitLoginUser;
import com.techidea.domain.interactor.Login;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import rx.Observer;
import rx.Subscriber;
import rx.observers.TestObserver;
import rx.observers.TestSubscriber;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by Administrator on 2016/7/17.
 * 保证presenter 一定触发 view的事件
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
        verify(loginView).initLoginUsers(anyList());
        verify(mInitLoginUser).initParams(anyString(), anyString());
        verify(mInitLoginUser).execute();
    }

    public void testLogin() {
        given(loginView.context()).willReturn(mContext);
        mLoginPresenter.login("132", "123");
        verify(mLogin).initParams(anyString(), anyString(), anyString());
        verify(mLogin).execute();
    }

    public void testInitUsers() {
        TestObserver<HttpResult<List<UserInfo>>> testObserver = new TestObserver<>();
        given(loginView.context()).willReturn(mContext);
        mInitLoginUser.initParams("08:00:00:64:84:0C", "WIZARHAND");
        mInitLoginUser.execute().subscribe(new Observer<List<UserInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<UserInfo> userInfoList) {
                assertNotSame(userInfoList.size(), 0);
            }
        });

    }

}
