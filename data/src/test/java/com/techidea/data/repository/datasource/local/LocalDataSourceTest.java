package com.techidea.data.repository.datasource.local;

import com.techidea.data.ApplicationTestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;

/**
 * Created by zchao on 2016/7/15.
 */
public class LocalDataSourceTest extends ApplicationTestCase{

    private LocalDataSource mLocalDataSource;

    @Before
    public void setUp(){
        mLocalDataSource = new LocalDataSource(RuntimeEnvironment.application);
    }

    @Test
    public void testInitLoginUsers(){
        mLocalDataSource.initUserInfo();
    }

    @Test
    public void testInitProduct(){
        mLocalDataSource.initProduct();
    }

    @Test
    public void testinitProductCategory(){
        mLocalDataSource.initProductCategory();
    }

    @Test
    public void testlogin(){
        mLocalDataSource.login();
    }
}
