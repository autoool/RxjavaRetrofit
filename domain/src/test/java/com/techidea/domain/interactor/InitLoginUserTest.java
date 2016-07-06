package com.techidea.domain.interactor;

import com.techidea.domain.entity.UserInfo;
import com.techidea.domain.respository.DataRepositoryDomain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

/**
 * Created by zchao on 2016/5/19.
 */
public class InitLoginUserTest {

    private InitLoginUser mInitLoginUser;

    @Mock
    private DataRepositoryDomain mockDataRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
//        mInitLoginUser = new InitLoginUser(DataRepository);
    }

    @Test
    public void testInitLoginUser() {
        mInitLoginUser.initParams("11:11:22:22:22:22","KOOLPOS").execute(new UserInfosSubscriber());
    }

    private final class UserInfosSubscriber extends DefaultSubscriber<List<UserInfo>> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<UserInfo> userInfos) {
        }
    }

}
