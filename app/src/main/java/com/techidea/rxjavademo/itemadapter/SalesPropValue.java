package com.techidea.rxjavademo.itemadapter;

import java.io.Serializable;

/**
 * 销售参数实体
 * Created by YY on 2015/12/31.
 */
public class SalesPropValue implements Serializable {
    private String id;
    private String propId;
    private String value;
    private String merchantId;

    public SalesPropValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getPropId() {
        return propId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getValue() {
        return value;
    }

    public boolean equalsBySalesPropValue(SalesPropValue salesPropValue) {
        if (salesPropValue == null)
            return false;
        if (!this.getId().equals(salesPropValue.getId()))
            return false;
        return true;
    }
}
