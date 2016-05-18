package com.techidea.rxjavademo.itemadapter;

/**
 * created by Boms at 2015/12/2 15:19
 * description 商品分类
 */
public class ProductCategory {
    String id;
    String name;

    public ProductCategory(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
