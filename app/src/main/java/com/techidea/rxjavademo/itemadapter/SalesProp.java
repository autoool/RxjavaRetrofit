package com.techidea.rxjavademo.itemadapter;

import java.io.Serializable;

/**
 * 销售属性类型实体
 * Created by YY on 2015/12/31.
 */
public class SalesProp implements Serializable{
    private String id;
    private String alias;
    private String name;
    private String merchantId;

    public String getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public String getName() {
        return name;
    }

    public String getMerchantId() {
        return merchantId;
    }
}
