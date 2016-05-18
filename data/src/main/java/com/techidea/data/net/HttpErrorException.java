package com.techidea.data.net;

/**
 * Created by zchao on 2016/3/25.
 */
public class HttpErrorException extends RuntimeException{

    public HttpErrorException(String detailMessage) {
        super(detailMessage);
    }
}
