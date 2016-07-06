package com.techidea.data.net;

/**
 * Created by zchao on 2016/7/6.
 */
public class CacheException extends Exception {

    public CacheException() {
        super();
    }

    public CacheException(Throwable throwable) {
        super(throwable);
    }

    public CacheException(String detailMessage) {
        super(detailMessage);
    }

    public CacheException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
