package com.techidea.appclean.adapter;

import com.techidea.appclean.BuildConfig;
import com.techidea.appclean.helper.RxTestHelper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by zchao on 2016/8/10.
 *   http://www.jianshu.com/p/dfbc15d919be
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class UserInfoModelTest {

    @Before
    public void setup() {
        RxTestHelper.registJavaImmediate();
    }

    @Test
    public void testLoadUserInfos() {
        UserInfoModel userInfoModel = new UserInfoModel();
//        final List<UserInfo> list = new ArrayList<>();
//        userInfoModel.loadUserInfos().subscribe(new Action1<List<UserInfo>>() {
//            @Override
//            public void call(List<UserInfo> userInfoList) {
//                list.addAll(userInfoList);
//            }
//        });
//        Assert.assertEquals(3, list.size());
    }

}
