package com.techidea.data.net;

/**
 * Created by zchao on 2016/3/25.
 */
public class HttpResult<T> {

    private int code;
    private String msg;
    private String time;
    private T data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getTime() {
        return time;
    }

    public T getData() {
        return data;
    }

}
