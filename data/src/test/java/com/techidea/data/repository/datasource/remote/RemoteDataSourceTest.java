package com.techidea.data.repository.datasource.remote;

import com.techidea.data.ApplicationTestCase;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

/**
 * Created by zchao on 2016/7/15.
 */
public class RemoteDataSourceTest extends ApplicationTestCase {

    private RemoteDataSource mRemoteDataSource;

    @Before
    public void setUp() {
        mRemoteDataSource = new RemoteDataSource(RuntimeEnvironment.application);
    }

    @Test
    public void testinitUserInfo() {
        mRemoteDataSource.initUserInfo("08:00:00:64:84:0C", "WIZARHAND");
    }

    @Test
    public void testinitProduct() {
        mRemoteDataSource.initProduct("08:00:00:64:84:0C", "WIZARHAND");
    }

    @Test
    public void testinitProductCategory() {
        mRemoteDataSource.initProductCategory("08:00:00:64:84:0C", "WIZARHAND");
    }

    @Test
    public void testlogin() {
        mRemoteDataSource.login("08:00:00:64:84:0C", "user01", "111111");
    }


}
