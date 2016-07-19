package com.techidea.data.net;

/**
 * Created by zchao on 2016/7/19.
 */
public class BaiduResponse<T> {

    private int errNum;
    private String errMsg;
    private T retData;

    public int getErrNum() {
        return errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public T getRetData() {
        return retData;
    }
}
