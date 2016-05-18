package com.techidea.presentation.view;

import com.techidea.presentation.model.ProductCategoryModel;
import com.techidea.presentation.model.ProductModel;

import java.util.Collection;

/**
 * Created by zchao on 2016/5/12.
 */
public interface ProductView extends LoadDataView {

    void renderProductCategory(Collection<ProductCategoryModel> productCategoryModels);

    void renderProduct(Collection<ProductModel> productModels);

    void updateProgress(int progress);

    void initProductCategorySuccess();

    void initProductSuccess();
}
