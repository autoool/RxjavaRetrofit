package com.techidea.presentation.mapper;

import com.techidea.domain.Product;
import com.techidea.domain.ProductCategory;
import com.techidea.presentation.internal.di.PerActivity;
import com.techidea.presentation.model.ProductCategoryModel;
import com.techidea.presentation.model.ProductModel;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

import dagger.Provides;

/**
 * Created by zchao on 2016/5/12.
 */
@PerActivity
public class ProductModelMapper {

    @Inject
    public ProductModelMapper() {
    }

    public ProductCategoryModel transform(ProductCategory productCategory) {
        if (productCategory == null)
            throw new IllegalArgumentException("cannot transform");
        ProductCategoryModel productCategoryModel = new ProductCategoryModel();
        productCategory.setId(productCategory.getId());
        productCategoryModel.setName(productCategory.getName());

        return productCategoryModel;
    }

    public Collection<ProductCategoryModel> transform(Collection<ProductCategory> productCategories) {
        Collection<ProductCategoryModel> productCategoryModels;
        if (productCategories != null && !productCategories.isEmpty()) {
            productCategoryModels = new ArrayList<>();
            for (ProductCategory productCategory : productCategories) {
                productCategoryModels.add(transform(productCategory));
            }
        } else {
            productCategoryModels = Collections.emptyList();
        }
        return productCategoryModels;

    }

    public ProductModel transformProduct(Product product) {
        if (product == null)
            throw new IllegalArgumentException("cannot transform");
        ProductModel productModel = new ProductModel();
        productModel.setId(product.getId());
        productModel.setType(product.getType());
        productModel.setDescription(product.getDescription());
        productModel.setTitle(product.getTitle());
        productModel.setSpellCode(product.getSpellCode());
        return productModel;
    }

    public Collection<ProductModel> transformProducts(Collection<Product> products) {
        Collection<ProductModel> productModels;
        if (products != null && !products.isEmpty()) {
            productModels = new ArrayList<>();
            for (Product product : products) {
                productModels.add(transformProduct(product));
            }
        } else {
            productModels = Collections.emptyList();
        }
        return productModels;

    }
}
