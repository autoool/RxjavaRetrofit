package com.techidea.appclean.hefeng;

import com.techidea.domain.entity.CityInfo;
import com.techidea.domain.interactor.DefaultSubscriber;
import com.techidea.domain.interactor.GetSearchCityInfo;

import java.util.List;

import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by zchao on 2016/7/20.
 */
public class CitySelectPresenter implements CitySelectContract.Presenter {

    private CitySelectContract.View mView;
    private List<CityInfo> mCityInfoList;
    private GetSearchCityInfo mGetSearchCityInfo;

    public CitySelectPresenter(CitySelectContract.View view, GetSearchCityInfo getSearchCityInfo) {
        this.mView = view;
        this.mGetSearchCityInfo = getSearchCityInfo;
    }

    @Override
    public void init() {

    }

    @Override
    public void getSearchCityInfo(String citytype, String key) {
        mView.startRefreshing();
        this.mGetSearchCityInfo.initParams(citytype, key);
        this.mGetSearchCityInfo.execute().doOnNext(new Action1<List<CityInfo>>() {
            @Override
            public void call(List<CityInfo> cityInfos) {

            }
        });
        this.mGetSearchCityInfo.execute().subscribe(new Subscriber<List<CityInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<CityInfo> cityInfos) {

            }
        });
    }

    @Override
    public void start() {

    }
}
