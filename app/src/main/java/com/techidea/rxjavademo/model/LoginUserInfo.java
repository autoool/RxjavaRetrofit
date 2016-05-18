package com.techidea.rxjavademo.model;


import java.util.List;

/**
 * created by Boms at 2015/10/23 14:33
 * description 登录者相关信息
 */
public class LoginUserInfo {

    private String id;
    private String userId;
    private String username;
    private String userType;
    private String posId;
    private String posCode;
    private String storeId;
    private String storeName;
    private String merchantId;
    private String merchantName;
    private String token;
    private String loginTime;
    private List<String> storePayTypes;

    private String mallId = "";

    private static LoginUserInfo instance;

    private LoginUserInfo() {
        instance = this;
    }

    public static LoginUserInfo getInstance() {
        if (instance == null)
            instance = new LoginUserInfo();
        return instance;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPosId() {
        return posId;
    }

    public String getPosCode() {
        return posCode;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getToken() {
        return token;
    }

    public String getUserType() {
        return userType;
    }

    public List<String> getStorePayTypes() {
        return storePayTypes;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public String getMallId() {
        return mallId;
    }
}
