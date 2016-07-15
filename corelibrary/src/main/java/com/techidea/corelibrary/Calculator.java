package com.techidea.corelibrary;

/**
 * Created by zchao on 2016/7/15.
 */
public class Calculator {

    public Calculator() {
    }

    public int add(int one, int another) {
        // 为了简单起见，暂不考虑溢出等情况。
        return one + another;
    }

    public int multiply(int one, int another) {
        // 为了简单起见，暂不考虑溢出等情况。
        return one * another;
    }

    public int divide(int one, int another) {
        if (another == 0)
            throw new IllegalArgumentException("Dividor cannot be 0");
        return one / another;
    }

    public int sub(int one, int another) {
        return 0;
    }
}
