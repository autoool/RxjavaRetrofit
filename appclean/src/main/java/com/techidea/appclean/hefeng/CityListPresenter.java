package com.techidea.appclean.hefeng;

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
        mView.showLoading();
    }

    @Override
    public void init(String cityname) {
        mGetCityList.initParams(cityname);
        mGetCityList.execute(new GetCityListSub());
    }

    public void initCityList(List<CityItem> list) {
        mView.initCityList(list);
    }

    private final class GetCityListSub extends DefaultSubscriber<List<CityItem>> {
        @Override
        public void onCompleted() {
            mView.hideLoading();
            mView.stoprefresh();
        }

        @Override
        public void onError(Throwable e) {
            mView.hideLoading();
            mView.showError(e.getMessage());
        }

        @Override
        public void onNext(List<CityItem> cityItems) {
            mCityItemList = cityItems;
            initCityList(cityItems);
        }
    }
}
