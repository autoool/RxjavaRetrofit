package com.techidea.appclean.net;

import com.techidea.appclean.common.Contast;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by zchao on 2016/11/2.
 */

public class HttpClientManagerTest {

    private HttpClientManager httpClientManager;

    @Before
    public void setup(){
        httpClientManager = new HttpClientManager();
    }

    @Test
    public void testGet(){
        httpClientManager.httpClientGet(Contast.URL_BING);
    }

}
