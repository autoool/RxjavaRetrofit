package com.techidea.appclean.base;

import com.techidea.data.repository.ProductDataRepository;
import com.techidea.data.repository.UserInfoDataRepository;
import com.techidea.domain.interactor.InitLoginUser;
import com.techidea.domain.interactor.InitProduct;
import com.techidea.domain.interactor.InitProductCategory;
import com.techidea.domain.interactor.Login;

/**
 * Created by zchao on 2016/5/18.
 */
public class Injection {

    public static ProductDataRepository provideProductDataRepository(){
        return ProductDataRepository.getInstance();
    }

    public static UserInfoDataRepository provideUserInfoDataRepository(){
        return UserInfoDataRepository.getInstance();
    }

    public static InitLoginUser provideInitLoginUser(){
        return new InitLoginUser(provideUserInfoDataRepository());
    }

    public static InitProduct provideInitProduct(){
        return new InitProduct(provideProductDataRepository());
    }

    public static InitProductCategory provideInitProductCategory(){
        return new InitProductCategory(provideProductDataRepository());
    }

    public static Login provideLogin(){
        return new Login(provideUserInfoDataRepository());
    }

}
