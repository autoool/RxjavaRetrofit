package com.techidea.rxjavademo.rxfunction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zchao on 2016/4/7.
 */
public class RxMap {

    private List<String> uppers = new ArrayList<>();

    public RxMap() {
        init();
    }

    private  void init(){
        uppers.add("AAAA");
        uppers.add("BBBB");
    }

    public static void map(){
//        .map(new Func1<AppInfo,AppInfo>(){
    }

}
