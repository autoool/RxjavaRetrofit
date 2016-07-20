package com.techidea.appclean.baidu;

import com.techidea.appclean.base.BasePresenter;
import com.techidea.appclean.base.BaseView;
import com.techidea.domain.entity.CityItem;

import java.util.List;

/**
 * Created by zchao on 2016/7/20.
 */
public class CitySelectContract {

    public interface Presenter extends BasePresenter{
        void init();
    }

    public interface View extends BaseView<Presenter>{
        void initCitys(List<CityItem> list);

    }
}
