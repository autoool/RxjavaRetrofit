package com.techidea.data.net;

import com.techidea.domain.entity.LoginUser;
import com.techidea.domain.entity.UserInfo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.observers.TestSubscriber;

/**
 * Created by zchao on 2016/7/8.
 */
public class ApiServiceTest {

    ApiService mApiService;
    MockRetrofitHelper mRetrofitHelper;

    @Before
    public void setUp() throws Exception {
        mRetrofitHelper = new MockRetrofitHelper();
        mApiService = mRetrofitHelper.create(ApiService.class);
    }

    @Test
    public void testinitLoginUsers() throws Exception {
        mRetrofitHelper.setPath("");
        mRetrofitHelper.setContent("{\n" +

                "}");
        TestSubscriber<HttpResult<List<UserInfo>>> testSubscriber = new TestSubscriber<>();
        mApiService.initLoginUsers("08:00:00:64:84:0C", "WIZARHAND")
                .toBlocking()
                .subscribe(testSubscriber);
        HttpResult<List<UserInfo>> result = testSubscriber.getOnNextEvents()
                .get(0);
        List<UserInfo> userInfoList = result.getData();
        System.out.println(result.getMsg());
        Assert.assertEquals(result.getCode(), 1);
    }

    @Test
    public void testLogin() {
        mRetrofitHelper.setPath("");
        mRetrofitHelper.setContent("{\n" +

                "}");

        TestSubscriber<HttpResult<LoginUser>> testSubscriber = new TestSubscriber<>();
        mApiService.login("08:00:00:64:84:0C", "chao01", "111111")
                .toBlocking()
                .subscribe(testSubscriber);
        HttpResult<LoginUser> result = testSubscriber.getOnNextEvents()
                .get(0);
        LoginUser loginUser = result.getData();
        System.out.println(result.getMsg());
        Assert.assertEquals(result.getCode(), 1);

    }

}
