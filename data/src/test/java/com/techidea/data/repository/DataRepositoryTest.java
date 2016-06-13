package com.techidea.data.repository;

import com.techidea.data.ApplicationTestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.MockitoAnnotations;

/**
 * Created by zchao on 2016/5/19.
 */
public class DataRepositoryTest extends ApplicationTestCase {

    private DataRepository mDataRepository;

    @Rule
    public ExpectedException mExpectedException = ExpectedException.none();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mDataRepository = new DataRepository();
    }

    @Test
    public void testInitLoginUserCase() {
        mDataRepository.initUserInfo("11:11:11:11:11:11", "KOOLPOS");
    }

    @Test
    public void testInitProductCategoryCase() {
        mDataRepository.initProductCategory("11:11:11:11:11:11", "KOOLPOS");
    }

    @Test
    public void testInitProductCase() {
        mDataRepository.initProduct("11:11:11:11:11:11", "KOOLPOS");
    }


}
