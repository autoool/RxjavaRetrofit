package com.techidea.data;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by zchao on 2016/5/19.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,application = ApplicationStub.class)
public abstract class ApplicationTestCase {

}
