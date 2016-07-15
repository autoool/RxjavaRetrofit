package com.techidea.corelibrary;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by zchao on 2016/7/15.
 */
public class CalculatorTest {

    private Calculator mCalculator;

    @Before
    public void setup() {
        mCalculator = new Calculator();
    }

    //    这个方法应该抛出IllegalArgumentException异常
    @Test(expected = IllegalArgumentException.class)
    public void testDivide() {
        mCalculator.divide(5, 0);
    }

    @Test
    @Ignore("not test")
    public void testSub() {
        mCalculator.sub(6, 5);
    }

    @After
    public void release() {

    }
}
