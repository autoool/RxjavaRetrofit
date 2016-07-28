package com.techidea.appclean.hefeng;

import com.techidea.appclean.base.BasePresenter;
import com.techidea.appclean.base.BaseView;
import com.techidea.domain.entity.CityInfo;
import com.techidea.domain.entity.CityItem;

import java.util.List;

/**
 * Created by zchao on 2016/7/20.
 */
public class CitySelectContract {

    public interface Presenter extends BasePresenter {
        void init();

        void getSearchCityInfo(String citytype, String key);
    }

    public interface View extends BaseView<Presenter> {
        void initCityInfos(List<CityInfo> list);
        void startRefreshing();
        void stopRefresh();
    }
}
