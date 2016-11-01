package com.techidea.appclean.hefeng;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.techidea.appclean.R;
import com.techidea.appclean.adapter.CityInfoAdapter;
import com.techidea.appclean.base.Injection;
import com.techidea.commonlibrary.adapter.DividerListItemDecoration;
import com.techidea.domain.entity.CityInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zchao on 2016/7/20.
 */
public class CitySelectActivity extends AppCompatActivity implements CitySelectContract.View {

    @Bind(R.id.coordinatorLayout_select)
    CoordinatorLayout mCoordinatorLayoutSelect;
    @Bind(R.id.toolbar_select)
    Toolbar mToolbarSelect;
    @Bind(R.id.swiperefresh_layout_select)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recyclerview_selectcity)
    RecyclerView mRecyclerViewCity;


    private CitySelectContract.Presenter mPresenter;
    private CityInfoAdapter mCityInfoAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cityselect);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbarSelect);
        mToolbarSelect.setNavigationIcon(R.mipmap.ic_launcher);
        mToolbarSelect.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击两次，但是第二次就不会提示成功
                mPresenter.getSearchCityInfo("allchina", "e6ab010decc6489e8b2a325230fef519");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cityselect_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:

                break;
            case R.id.action_allchina:
                mSwipeRefreshLayout.setRefreshing(true);
                mPresenter.getSearchCityInfo("allchina", "e6ab010decc6489e8b2a325230fef519");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initCityInfos(List<CityInfo> list) {
        mCityInfoAdapter = new CityInfoAdapter(mRecyclerViewCity, list, R.layout.view_cityinfo_item);
        mRecyclerViewCity.addItemDecoration(new DividerListItemDecoration(this, DividerListItemDecoration.VERTICAL_LIST));
        mRecyclerViewCity.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewCity.setAdapter(mCityInfoAdapter);
    }

    @Override
    public void setPresenter(CitySelectContract.Presenter presenter) {

    }

    @Override
    public void showError(String message) {
        showMsg(message);
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void startRefreshing() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void stopRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void showMsg(String msg) {
        Snackbar snackbar = Snackbar.make(mCoordinatorLayoutSelect, msg, Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }
}
