package com.techidea.data.cache.serializer;

import com.google.gson.reflect.TypeToken;
import com.techidea.data.ApplicationTestCase;
import com.techidea.domain.entity.LoginUser;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zchao on 2016/7/6.
 */
public class JsonSerializerTest extends ApplicationTestCase {

    private JsonSerializer<LoginUser> jsonSerializer;
    private JsonSerializer<List<LoginUser>> listJsonSerializer;

    @Before
    public void setUp() {
        jsonSerializer = new JsonSerializer<>();
        listJsonSerializer = new JsonSerializer<>();
    }

    @Test
    public void testSerializeCase() {

        LoginUser loginUser = new LoginUser();
        loginUser.setId("1");
        loginUser.setPosCode("1111");
        loginUser.setPosId("aaa");
        loginUser.setToken("asdfga");
        loginUser.setUserId("1234");
        loginUser.setUsername("1234");
        loginUser.setUserType("1111");
        String jsonStr = jsonSerializer.serialize(loginUser);
        System.out.println(jsonStr);
        LoginUser loginUser1 = new LoginUser();
        loginUser1 = jsonSerializer.deserialize(jsonStr, new TypeToken<LoginUser>() {
        });
        if (loginUser1 != null)
            System.out.println(loginUser1.getToken());
        else
            System.out.println("deserialize ERROR");
        List<LoginUser> loginUserList = new ArrayList<>();
        loginUserList.add(loginUser);
        loginUserList.add(loginUser1);

        String jsonList = listJsonSerializer.serialize(loginUserList, new TypeToken<List<LoginUser>>() {
        });
        System.out.println(jsonStr);
        List<LoginUser> loginUserList1 = new ArrayList<>();
        loginUserList1 = listJsonSerializer.deserialize(jsonList, new TypeToken<List<LoginUser>>() {
        });
        System.out.println("" + loginUserList1.size());
    }
}
