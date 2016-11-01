package com.techidea.data.cache;

import com.techidea.data.ApplicationTestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by zchao on 2016/7/6.
 */
public class DataCacheImplTest extends ApplicationTestCase {

    private DataCacheImpl mDataCache;

    @Before
    public void setUp() {
        mDataCache = new DataCacheImpl();
    }

    @Test
    public void testUserinfos() {

//        mDataCache.putLoginUsers(list);
//        mDataCache.getLoginUsers().doOnNext(new Action1<List<UserInfo>>() {
//            @Override
//            public void call(List<UserInfo> userInfoList) {
//                assertThat(userInfoList.size(), is(equalTo(3)));
//            }
//        });
    }

}
