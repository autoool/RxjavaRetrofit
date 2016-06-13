package com.techidea.data.net;

/**
 * Created by zchao on 2016/3/25.
 */
public class HttpErrorException extends RuntimeException{

    private int code;
    private String message;

    public HttpErrorException(int code,String detailMessage) {
        super(detailMessage);
        this.code = code;
        this.message = detailMessage;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
