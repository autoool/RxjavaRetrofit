package com.techidea.rxjavademo.itemadapter;

import java.util.List;

/**
 * created by Boms at 2016/1/8 10:00
 * description 套餐商品单项
 */
public class PackageProduct {
    private String id;
    private String name;
    private int qty;
    private int sortOrder;
    private String itemMakeTags;
    private SalesProp salesProp1;
    private List<SalesPropValue> salesValues1;
    private SalesProp salesProp2;
    private List<SalesPropValue> salesValues2;
    private List<ProductItem> packageItems;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public SalesProp getSalesProp1() {
        return salesProp1;
    }

    public void setSalesProp1(SalesProp salesProp1) {
        this.salesProp1 = salesProp1;
    }

    public List<SalesPropValue> getSalesValues1() {
        return salesValues1;
    }

    public void setSalesValues1(List<SalesPropValue> salesValues1) {
        this.salesValues1 = salesValues1;
    }

    public SalesProp getSalesProp2() {
        return salesProp2;
    }

    public void setSalesProp2(SalesProp salesProp2) {
        this.salesProp2 = salesProp2;
    }

    public List<SalesPropValue> getSalesValues2() {
        return salesValues2;
    }

    public void setSalesValues2(List<SalesPropValue> salesValues2) {
        this.salesValues2 = salesValues2;
    }

    public String getItemMakeTags() {
        return itemMakeTags;
    }

    public void setItemMakeTags(String itemMakeTags) {
        this.itemMakeTags = itemMakeTags;
    }

    public List<ProductItem> getPackageItems() {
        return packageItems;
    }

    public void setPackageItems(List<ProductItem> packageItems) {
        this.packageItems = packageItems;
    }

    public ProductItem getDefaultProductItem() {
        if (packageItems == null)
            return null;
        return packageItems.get(0);
    }

    /**
     * @param id ProductId
     * @return -1 没有对应参数
     */
    public int getPostionFromPackageItemsByProductId(String id) {
        int index = -1;
        if (packageItems!=null){
            for (ProductItem item : packageItems) {
                index++;
                if (item.getProductId().equals(id))
                    return index;
            }
        }
        return index;
    }

}
