package com.techidea.appclean.base;

import android.content.Context;

import com.techidea.data.repository.DataRepository;
import com.techidea.domain.interactor.InitLoginUser;
import com.techidea.domain.interactor.InitProduct;
import com.techidea.domain.interactor.InitProductCategory;
import com.techidea.domain.interactor.Login;

/**
 * Created by zchao on 2016/5/18.
 */
public class Injection {

    public static DataRepository provideDataRepository(Context context) {
        return DataRepository.getInstance(context);
    }

    public static InitLoginUser provideInitLoginUser(Context context) {
        return new InitLoginUser(provideDataRepository(context));
    }

    public static InitProduct provideInitProduct(Context context) {
        return new InitProduct(provideDataRepository(context));
    }

    public static InitProductCategory provideInitProductCategory(Context context) {
        return new InitProductCategory(provideDataRepository(context));
    }

    public static Login provideLogin(Context context) {
        return new Login(provideDataRepository(context));
    }

}
