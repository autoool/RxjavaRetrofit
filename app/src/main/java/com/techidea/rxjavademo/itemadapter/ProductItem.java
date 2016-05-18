package com.techidea.rxjavademo.itemadapter;

import com.google.gson.annotations.Expose;

import java.io.Serializable;


/**
 * created by Boms at 2015/12/31 11:50
 * description 单品信息
 */
public class ProductItem implements Serializable {

    /**
     * id : mockItem1
     * barCodeString : 000001
     * sortOrder : 0
     * title : 卡布奇诺
     * productId : mockProduct1
     * soldQty : 0
     * standardPrice : 20.0
     * priceType : MERCHANT
     * price : 15.0
     */
    @Expose
    private String id;
    private String barCodeString;
    private String title;
    private String productId;
    private int soldQty;
    private String standardPrice; //市场价格
    private String priceType;
    private String price;        //零售价
    private SalesProp salesProp1;
    private SalesPropValue salesValue1;
    private SalesProp salesProp2;
    private SalesPropValue salesValue2;
    private String unitName;
    private String itemMakeTags;


    public String getId() {
        return id;
    }

    public String getBarCodeString() {
        return barCodeString;
    }

    public String getTitle() {
        return title;
    }

    public String getProductId() {
        return productId;
    }

    public int getSoldQty() {
        return soldQty;
    }

    public String getStandardPrice() {
        return standardPrice;
    }

    public String getPriceType() {
        return priceType;
    }

    public String getPrice() {
        return price;
    }

    public SalesProp getSalesProp1() {
        return salesProp1;
    }

    public SalesPropValue getSalesValue1() {
        return salesValue1;
    }

    public SalesProp getSalesProp2() {
        return salesProp2;
    }

    public SalesPropValue getSalesValue2() {
        return salesValue2;
    }

    public String getMoneyFromatPrice() {
        return price;
    }

    public String getItemMakeTags() {
        return itemMakeTags;
    }

    public String getUnitName() {
        return unitName;
    }

    /**
     * 用于通过销售属性ID判断是否为当前ProductItem   <br/>
     *
     * @param valueid1 salvalue1 id
     * @param valueid2 salvalue2 id
     */
    public boolean compareBySalesValueId(String valueid1, String valueid2) {
        if (valueid1 != null && this.salesValue1 != null) {
            //values1 都不为空 进行参数比较
            if (valueid1.equals(salesValue1.getId())) {
                //values1 比较完全相同 进行values2比较
                if (valueid2 != null && this.salesValue2 != null) {
                    //values2都不为空 进行参数比较
                    if (valueid2.equals(salesValue2.getId())) {
                        //values2 相同
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
