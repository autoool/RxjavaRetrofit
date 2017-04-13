package com.techidea.appclean.base;

import android.content.Context;

import com.techidea.data.repository.DataRepository;
import com.techidea.domain.interactor.GetCityList;
import com.techidea.domain.interactor.GetSearchCityInfo;
import com.techidea.domain.interactor.UploadFileWithPartMap;

/**
 * Created by zchao on 2016/5/18.
 */
public class Injection {

    public static DataRepository provideDataRepository(Context context) {
        return DataRepository.getInstance(context);
    }

    public static UploadFileWithPartMap provideUploadFileWithPartMap(Context context) {
        return new UploadFileWithPartMap(provideDataRepository(context));
    }
}
