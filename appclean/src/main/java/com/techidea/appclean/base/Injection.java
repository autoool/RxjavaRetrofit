package com.techidea.appclean.base;

import com.techidea.data.repository.DataRepository;
import com.techidea.domain.interactor.InitLoginUser;
import com.techidea.domain.interactor.InitProduct;
import com.techidea.domain.interactor.InitProductCategory;
import com.techidea.domain.interactor.Login;

/**
 * Created by zchao on 2016/5/18.
 */
public class Injection {

    public static DataRepository provideDataRepository() {
        return DataRepository.getInstance();
    }

    public static InitLoginUser provideInitLoginUser() {
        return new InitLoginUser(provideDataRepository());
    }

    public static InitProduct provideInitProduct() {
        return new InitProduct(provideDataRepository());
    }

    public static InitProductCategory provideInitProductCategory() {
        return new InitProductCategory(provideDataRepository());
    }

    public static Login provideLogin() {
        return new Login(provideDataRepository());
    }

}
