package com.techidea.data.repository;

import com.techidea.data.ApplicationTestCase;
import com.techidea.data.net.HttpMethods;
import com.techidea.domain.entity.UserInfo;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zchao on 2016/5/19.
 */
public class DataRepositoryTest extends ApplicationTestCase {

    private DataRepository mDataRepository;

    @Before
    public void setUp() {
        HttpMethods.setBaseUrl("");
        mDataRepository = new DataRepository(RuntimeEnvironment.application);
    }

    @Test
    public void testInitLoginUserCase() {
        mDataRepository.initUserInfo("08:00:00:64:84:0C", "WIZARHAND")
                .subscribe(new Subscriber<List<UserInfo>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onNext(List<UserInfo> userInfoList) {
                        if (userInfoList != null)
                            for (UserInfo item : userInfoList) {
                                System.out.println(item.getUsername());
                            }

                    }
                });
    }

    @Test
    public void testInitProductCategoryCase() {
        mDataRepository.initProductCategory("11:11:11:11:11:11", "KOOLPOS");
    }

    @Test
    public void testInitProductCase() {
        mDataRepository.initProduct("11:11:11:11:11:11", "KOOLPOS");
    }


}
