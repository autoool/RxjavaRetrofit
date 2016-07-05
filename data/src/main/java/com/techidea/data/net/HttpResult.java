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
//list 和 object 能不能合并成一个data 服务端返回呢？

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
