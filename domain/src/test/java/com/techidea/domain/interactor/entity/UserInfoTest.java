package com.techidea.domain.interactor.entity;

import com.techidea.domain.entity.UserInfo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by zchao on 2016/7/15.
 */
public class UserInfoTest {

    private static final String usertype = "supper";
    private static final String username = "caho01";

    private UserInfo mUserInfo;

    @Before
    public void setUp() {
        mUserInfo = new UserInfo(usertype, username);
    }

    @Test
    public void testUserInfoCase() {
        String type = mUserInfo.getType();
        String name = mUserInfo.getUsername();
        Assert.assertEquals(usertype, type);
        Assert.assertEquals(username, name);
    }
}
