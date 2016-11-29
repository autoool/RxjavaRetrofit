package com.techidea.appclean.net;

import android.provider.SyncStateContract;

import com.techidea.appclean.common.Contast;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by zchao on 2016/11/2.
 */

public class OkHttpManagerTest {

    private OkHttpManager okHttpManager;

    @Before
    public void setup() {
        okHttpManager = new OkHttpManager();
    }

    @Test
    public void testHttp() {
        OkHttpManager.getInstance().runHttp(Contast.URL_BING);
    }
}
