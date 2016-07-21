package com.techidea.data.net;

import com.techidea.domain.entity.CityItem;
import com.techidea.domain.entity.LoginUser;
import com.techidea.domain.entity.UserInfo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.observers.TestSubscriber;

/**
 * Created by zchao on 2016/7/8.
 * //mock模拟网络返回
 */
public class ApiServiceTest {

    ApiService mApiService;
    HefApiService mBaiduApiService;
    MockRetrofitHelper mRetrofitHelper;

    @Before
    public void setUp() throws Exception {
        mRetrofitHelper = new MockRetrofitHelper();
        mApiService = mRetrofitHelper.create(ApiService.class);
        mBaiduApiService = mRetrofitHelper.create(HefApiService.class);
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

    @Test
    public void getCityList() {
        mRetrofitHelper.setContent("{\n" +
                "    \"errNum\": 0,\n" +
                "    \"errMsg\": \"success\",\n" +
                "    \"retData\": [\n" +
                "        {\n" +
                "            \"province_cn\": \"北京\",\n" +
                "            \"district_cn\": \"北京\",\n" +
                "            \"name_cn\": \"朝阳\",\n" +
                "            \"name_en\": \"chaoyang\",\n" +
                "            \"area_id\": \"101010300\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"province_cn\": \"辽宁\",\n" +
                "            \"district_cn\": \"朝阳\",\n" +
                "            \"name_cn\": \"朝阳\",\n" +
                "            \"name_en\": \"chaoyang\",\n" +
                "            \"area_id\": \"101071201\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"province_cn\": \"辽宁\",\n" +
                "            \"district_cn\": \"朝阳\",\n" +
                "            \"name_cn\": \"凌源\",\n" +
                "            \"name_en\": \"lingyuan\",\n" +
                "            \"area_id\": \"101071203\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"province_cn\": \"辽宁\",\n" +
                "            \"district_cn\": \"朝阳\",\n" +
                "            \"name_cn\": \"喀左\",\n" +
                "            \"name_en\": \"kazuo\",\n" +
                "            \"area_id\": \"101071204\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"province_cn\": \"辽宁\",\n" +
                "            \"district_cn\": \"朝阳\",\n" +
                "            \"name_cn\": \"北票\",\n" +
                "            \"name_en\": \"beipiao\",\n" +
                "            \"area_id\": \"101071205\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"province_cn\": \"辽宁\",\n" +
                "            \"district_cn\": \"朝阳\",\n" +
                "            \"name_cn\": \"建平县\",\n" +
                "            \"name_en\": \"jianpingxian\",\n" +
                "            \"area_id\": \"101071207\"\n" +
                "        }\n" +
                "    ]\n" +
                "}");
//        TestSubscriber<HttpResult<LoginUser>> testSubscriber = new TestSubscriber<>();
//        mApiService.login("08:00:00:64:84:0C", "chao01", "111111")
//                .toBlocking()
//                .subscribe(testSubscriber);
//        HttpResult<LoginUser> result = testSubscriber.getOnNextEvents()
//                .get(0);
//        LoginUser loginUser = result.getData();
//        System.out.println(result.getMsg());
//        Assert.assertEquals(result.getCode(), 1);

        TestSubscriber<BaiduResponse<List<CityItem>>> testSubscriber = new TestSubscriber<>();
        mBaiduApiService.getCityList("朝阳")
                .toBlocking()
                .subscribe(testSubscriber);
        BaiduResponse<List<CityItem>> response = testSubscriber.getOnNextEvents().get(0);
        if (response.getErrNum() == 0) {
            List<CityItem> cityItems = response.getRetData();
            System.out.println(cityItems.size());
        }
    }


}
