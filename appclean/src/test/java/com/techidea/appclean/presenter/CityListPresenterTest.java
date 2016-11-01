package com.techidea.appclean.presenter;

import android.content.Context;
import android.test.AndroidTestCase;

import com.techidea.appclean.hefeng.CityListContract;
import com.techidea.appclean.hefeng.CityListPresenter;
import com.techidea.appclean.helper.RxTestHelper;
import com.techidea.domain.interactor.GetCityList;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

/**
 * Created by zchao on 2016/7/20.
 */
public class CityListPresenterTest extends AndroidTestCase {

    private CityListPresenter mCityListPresenter;

    @Mock
    private Context mContext;
    @Mock
    private CityListContract.View mView;
    @Mock
    private GetCityList mGetCityList;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        mCityListPresenter = new CityListPresenter(mView, mGetCityList);
        mView.setPresenter(mCityListPresenter);
    }

    public void testGetCityList() {
        RxTestHelper.registJavaImmediate();
        given(mView.context()).willReturn(mContext);
        mCityListPresenter.init(anyString());
        verify(mView).hideLoading();
    }


}
