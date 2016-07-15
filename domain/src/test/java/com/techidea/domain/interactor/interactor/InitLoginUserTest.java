package com.techidea.domain.interactor.interactor;

import com.techidea.domain.interactor.InitLoginUser;
import com.techidea.domain.respository.DataRepositoryDomain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by zchao on 2016/7/15.
 */
public class InitLoginUserTest {

    private InitLoginUser mInitLoginUser;

    @Mock
    private DataRepositoryDomain mDataRepositoryDomain;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mInitLoginUser = new InitLoginUser(mDataRepositoryDomain);
    }

    @Test
    public void testInitLogUserCase() {
        mInitLoginUser.initParams("08:00:00:64:84:0C", "WIZARHAND").buildCaseObservable();
        //验证一个对象的某个method得到调用的方法
        //验证 mDataRepositoryDomain 的 initUserInfo 被调用了
        verify(mDataRepositoryDomain).initUserInfo("08:00:00:64:84:0C", "WIZARHAND");
        verifyNoMoreInteractions(mDataRepositoryDomain);
    }
}
