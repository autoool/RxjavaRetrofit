package com.techidea.data.net;

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
                "  \"code\" : \"1\",\n" +
                "  \"msg\" : \"访问成功\",\n" +
                "  \"time\" : 1467946343377,\n" +
                "  \"list\" : [ {\n" +
                "    \"username\" : \"jijianuser03\",\n" +
                "    \"storeId\" : \"5f1b410d50da4e29afc590107d840f7f\",\n" +
                "    \"type\" : \"SUPERVISOR\",\n" +
                "    \"loginQrcode\" : null,\n" +
                "    \"lastQrcodeCreateTime\" : \"Jul 7, 2016 4:52:42 PM\"\n" +
                "  }, {\n" +
                "    \"username\" : \"user01\",\n" +
                "    \"storeId\" : \"5f1b410d50da4e29afc590107d840f7f\",\n" +
                "    \"type\" : \"SUPERVISOR\",\n" +
                "    \"loginQrcode\" : null,\n" +
                "    \"lastQrcodeCreateTime\" : \"Jul 4, 2016 6:03:30 PM\"\n" +
                "  }, {\n" +
                "    \"username\" : \"111222\",\n" +
                "    \"storeId\" : \"5f1b410d50da4e29afc590107d840f7f\",\n" +
                "    \"type\" : \"SUPERVISOR\",\n" +
                "    \"loginQrcode\" : null,\n" +
                "    \"lastQrcodeCreateTime\" : \"May 26, 2016 3:42:51 PM\"\n" +
                "  }, {\n" +
                "    \"username\" : \"xian01\",\n" +
                "    \"storeId\" : \"5f1b410d50da4e29afc590107d840f7f\",\n" +
                "    \"type\" : \"SUPERVISOR\",\n" +
                "    \"loginQrcode\" : null,\n" +
                "    \"lastQrcodeCreateTime\" : \"Jun 14, 2016 10:45:15 AM\"\n" +
                "  }, {\n" +
                "    \"username\" : \"chao01\",\n" +
                "    \"storeId\" : \"5f1b410d50da4e29afc590107d840f7f\",\n" +
                "    \"type\" : \"EMPLOYEE\",\n" +
                "    \"loginQrcode\" : null,\n" +
                "    \"lastQrcodeCreateTime\" : \"Jun 28, 2016 10:57:56 AM\"\n" +
                "  }, {\n" +
                "    \"username\" : \"JiJianUser01\",\n" +
                "    \"storeId\" : \"5f1b410d50da4e29afc590107d840f7f\",\n" +
                "    \"type\" : \"EMPLOYEE\",\n" +
                "    \"loginQrcode\" : null,\n" +
                "    \"lastQrcodeCreateTime\" : \"May 9, 2016 4:38:12 PM\"\n" +
                "  }, {\n" +
                "    \"username\" : \"JiJianUser02\",\n" +
                "    \"storeId\" : \"5f1b410d50da4e29afc590107d840f7f\",\n" +
                "    \"type\" : \"EMPLOYEE\",\n" +
                "    \"loginQrcode\" : null,\n" +
                "    \"lastQrcodeCreateTime\" : \"Jul 5, 2016 4:31:33 PM\"\n" +
                "  } ]\n" +
                "}");
        TestSubscriber<HttpResult<List<UserInfo>>> testSubscriber = new TestSubscriber<>();
        mApiService.initLoginUsers("08:00:00:64:84:0C", "WIZARHAND")
                .toBlocking()
                .subscribe(testSubscriber);
        HttpResult<List<UserInfo>> result = testSubscriber.getOnNextEvents()
                .get(0);
        System.out.println(result.getMsg());
        Assert.assertEquals(result.getCode(), 1);

    }
}
