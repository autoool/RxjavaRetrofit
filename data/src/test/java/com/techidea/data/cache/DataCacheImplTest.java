package com.techidea.data.cache;

import com.techidea.data.ApplicationTestCase;
import com.techidea.domain.entity.UserInfo;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by zchao on 2016/7/6.
 */
public class DataCacheImplTest extends ApplicationTestCase {

    private DataCacheImpl mDataCache;

    @Before
    public void setUp() {
        mDataCache = new DataCacheImpl(RuntimeEnvironment.application);
    }

    @Test
    public void testUserinfos() {
        List<UserInfo> list = new ArrayList<>();
        list.add(new UserInfo("123", "123"));
        list.add(new UserInfo("123", "123"));
        list.add(new UserInfo("123", "123"));
        mDataCache.putLoginUsers(list);
        mDataCache.getLoginUsers().doOnNext(new Action1<List<UserInfo>>() {
            @Override
            public void call(List<UserInfo> userInfoList) {
                assertThat(userInfoList.size(), is(equalTo(3)));
            }
        });
    }

}
