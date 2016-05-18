package com.techidea.domain.executor;

import rx.Scheduler;

/**
 * Created by zchao on 2016/5/5.
 */
public interface PostExecutionThread {
    Scheduler getScheduler();
}
