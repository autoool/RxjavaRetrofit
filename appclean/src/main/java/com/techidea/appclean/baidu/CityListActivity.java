package com.techidea.appclean.baidu;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techidea.appclean.R;
import com.techidea.appclean.adapter.CityListAdapter;
import com.techidea.appclean.base.Injection;
import com.techidea.domain.entity.CityItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zchao on 2016/7/19.
 */
public class CityListActivity extends AppCompatActivity
        implements CityListContract.View {

    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout mCoordinatorLayout;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.recyclerview_citylist)
    RecyclerView mRecyclerView;
    @Bind(R.id.progressbar_load)
    ProgressBar mProgressBar;
    @Bind(R.id.textview_nodata)
    TextView mTextViewNodata;

    private CityListAdapter mCityListAdapter;

    private CityListPresenter mCityListPresenter;
    private List<CityItem> mCityItemList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citylist);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mCityListPresenter = new CityListPresenter(
                this,
                Injection.provideGetCityList(getApplicationContext())
        );
        mCityListPresenter.init("朝阳");
        mCityItemList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mCityListPresenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.citylist_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_action:
                break;
            case R.id.refresh_action:
                mCityListPresenter.init("北京");
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showError(String msg) {
        showMsg(msg);
    }

    @Override
    public void initCityList(List<CityItem> list) {
        mCityItemList = list;
        mCityListAdapter = new CityListAdapter(mRecyclerView, mCityItemList, R.layout.view_citylist_item);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mCityListAdapter);
    }

    @Override
    public void setPresenter(CityListContract.Presenter presenter) {

    }

    @Override
    public Context context() {
        return getApplicationContext();
    }

    private void showMsg(String msg) {
        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, msg, Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
        setProgressBarIndeterminate(true);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
        setProgressBarIndeterminate(false);
    }

    public void showProgressLoad(boolean show) {
        if (show) {

        } else {

        }
    }
}
