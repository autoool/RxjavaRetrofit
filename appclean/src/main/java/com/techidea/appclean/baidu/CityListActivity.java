package com.techidea.appclean.baidu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.techidea.appclean.R;
import com.techidea.appclean.base.Injection;
import com.techidea.domain.entity.CityItem;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by zchao on 2016/7/19.
 */
public class CityListActivity extends AppCompatActivity
        implements CityListContract.View {

    private CityListPresenter mCityListPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citylist);
        ButterKnife.bind(this);
        mCityListPresenter = new CityListPresenter(
                this,
                Injection.provideGetCityList(getApplicationContext())
        );

        mCityListPresenter.init();
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void initCityList(List<CityItem> list) {

    }

    @Override
    public void setPresenter(CityListContract.Presenter presenter) {

    }

    @Override
    public Context context() {
        return null;
    }
}
