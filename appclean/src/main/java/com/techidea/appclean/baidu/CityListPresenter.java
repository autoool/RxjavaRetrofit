package com.techidea.appclean.baidu;

import com.techidea.appclean.login.LoginContract;
import com.techidea.domain.entity.CityItem;
import com.techidea.domain.interactor.DefaultSubscriber;
import com.techidea.domain.interactor.GetCityList;

import java.util.List;

/**
 * Created by zchao on 2016/7/19.
 */
public class CityListPresenter implements CityListContract.Presenter {

    private CityListContract.View mView;
    private final GetCityList mGetCityList;
    private List<CityItem> mCityItemList;

    public CityListPresenter(CityListContract.View view, GetCityList getCityList) {
        this.mView = view;
        this.mGetCityList = getCityList;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void init() {
        mGetCityList.initParams("朝阳").execute(new GetCityListSub());
    }

    private final class GetCityListSub extends DefaultSubscriber<List<CityItem>> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            mView.showError(e.getMessage());
        }

        @Override
        public void onNext(List<CityItem> cityItems) {

        }
    }
}
