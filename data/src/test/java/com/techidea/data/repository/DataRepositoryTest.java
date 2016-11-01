package com.techidea.data.repository;

import com.techidea.data.ApplicationTestCase;
import com.techidea.data.net.HttpMethods;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zchao on 2016/5/19.
 */
public class DataRepositoryTest extends ApplicationTestCase {

    private DataRepository mDataRepository;

    @Before
    public void setUp() {
        HttpMethods.setBaseUrl("");
        mDataRepository = new DataRepository(RuntimeEnvironment.application);
    }

}
