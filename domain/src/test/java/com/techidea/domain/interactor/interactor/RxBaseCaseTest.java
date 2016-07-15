package com.techidea.domain.interactor.interactor;

import com.techidea.domain.interactor.RxBaseCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.Subscriber;
import rx.observers.TestSubscriber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by zchao on 2016/5/19.
 */
public class RxBaseCaseTest {

    private RxBaseCaseTestClass mRxBaseCase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mRxBaseCase = new RxBaseCaseTestClass();
    }


    @Test
    public void testbuildCaseObservable() {
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        mRxBaseCase.execute(testSubscriber);
        assertThat(testSubscriber.getOnNextEvents().size(), is(0));
    }

    @Test
    public void testExecuteCase() {
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        mRxBaseCase.execute(testSubscriber);
        mRxBaseCase.unsubscribe();
        Assert.assertEquals(testSubscriber.isUnsubscribed(),true);
    }

    private static class RxBaseCaseTestClass extends RxBaseCase {

        public RxBaseCaseTestClass() {
        }

        @Override
        public Observable buildCaseObservable() {
            return Observable.empty();
        }

        @Override
        public RxBaseCase initParams(String... paras) {
            return this;
        }

        @Override
        public void execute(Subscriber subscriber) {
            super.execute(subscriber);
        }
    }

}
