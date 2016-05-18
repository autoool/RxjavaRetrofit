package com.techidea.rxjavademo.itemadapter;

import android.graphics.Bitmap;
import android.text.TextUtils;


import java.io.Serializable;
import java.util.List;


/**
 * Created by zchao on 2015/12/4.
 * 商品信息
 */
public class Product implements Serializable {

    // from server
    private String id;
    private String brand;
    private String productCategoryId;
    private String description;
    private String spellCode;
    private String status;
    private String title;
    private String type;
    private String unitName;
    private String imgUrl;
    private String merchanId;
    private String itemMakeTags;
    private SalesProp salesProp1;
    private List<SalesPropValue> salesValues1;
    private SalesProp salesProp2;
    private List<SalesPropValue> salesValues2;
    private List<ProductItem> productItems;
    private List<PackageProduct> packageProducts;

    // local
    private boolean isSelected = false;

    public Product() {
    }

    public Product(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public String getSpellCode() {
        return spellCode;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getUnitName() {
        return unitName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public List<PackageProduct> getPackageProducts() {
        return packageProducts;
    }

    public boolean ImgUrlIsEmpty() {
        return TextUtils.isEmpty(imgUrl)
                || (!TextUtils.isEmpty(imgUrl) && !imgUrl.startsWith("http://"));
    }

    public String getMerchanId() {
        return merchanId;
    }

    public String getItemMakeTags() {
        return itemMakeTags;
    }

    public SalesProp getJsonSalesProp1() {
        return salesProp1;
    }

    public List<SalesPropValue> getSalesValues1() {
        return salesValues1;
    }

    public SalesProp getJsonSalesProp2() {
        return salesProp2;
    }

    public List<SalesPropValue> getSalesValues2() {
        return salesValues2;
    }

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public ProductItem getDefaultProductItem() {
        if (productItems != null && productItems.size() > 0)
            return productItems.get(0);
        else
            return null;
    }

    /**
     * 通过productItemId 获取ProductItem
     * @param id ProductItemid
     * @return null 为空productItems
     *          ProductItem 没有对应productItem,则返回默认ProductItem
     */
    public ProductItem getProductItemByProductItemId(String id){
        if (productItems != null && productItems.size() > 0){
            for (ProductItem productItem : productItems) {
                if (id.equals(productItem.getId())){
                    return productItem;
                }
            }
            return productItems.get(0);
        }
        else
            return null;
    }
}
