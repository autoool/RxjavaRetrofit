package com.techidea.data.entity.mapper;

import com.techidea.data.entity.ProductCategoryEntity;
import com.techidea.data.entity.ProductEntity;
import com.techidea.domain.Product;
import com.techidea.domain.ProductCategory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by zchao on 2016/5/12.
 */
@Singleton
public class ProductMapper {

    @Inject
    public ProductMapper() {
    }

    public ProductCategory transform(ProductCategoryEntity productCategoryEntity) {
        ProductCategory productCategory = new ProductCategory();
        if (productCategoryEntity != null) {
            productCategory.setId(productCategoryEntity.getId());
            productCategory.setName(productCategoryEntity.getName());
        }
        return productCategory;
    }

    public List<ProductCategory> transform(Collection<ProductCategoryEntity>
                                                   productCategoryEntityCollection) {
        List<ProductCategory> productCategories = new ArrayList<>();
        ProductCategory productCategory;
        for (ProductCategoryEntity entity : productCategoryEntityCollection) {
            productCategory = transform(entity);
            if (productCategory != null)
                productCategories.add(productCategory);
        }
        return productCategories;
    }

    public Product transformProduct(ProductEntity productEntity) {
        Product product = new Product();
        if (productEntity != null) {
            product.setId(productEntity.getId());
            product.setDescription(productEntity.getDescription());
            product.setSpellCode(productEntity.getSpellCode());
            product.setTitle(productEntity.getTitle());
            product.setType(productEntity.getType());
        }
        return product;
    }

    public List<Product> transformProducts(Collection<ProductEntity> productEntities) {
        List<Product> products = new ArrayList<>();
        Product product;
        if (productEntities != null && !productEntities.isEmpty()) {
            for (ProductEntity entity : productEntities) {
                product = transformProduct(entity);
                products.add(product);
            }
        } else {
            products = Collections.emptyList();
        }
        return products;
    }


}
