package com.techidea.data.net;

/**
 * Created by zchao on 2016/3/25.
 */
public class HttpResult<T> {


    private int code;
    private String msg;
    private String time;
    private T list;
    private T object;


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getTime() {
        return time;
    }

    public T getList() {
        return list;
    }

    public T getObject() {
        return object;
    }

}
