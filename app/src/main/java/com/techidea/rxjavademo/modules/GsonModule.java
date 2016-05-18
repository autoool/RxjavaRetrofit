package com.techidea.rxjavademo.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zchao on 2016/5/5.
 */
@Module
public class GsonModule {

    @Provides
    public Gson provideGson(){
        return new GsonBuilder().serializeNulls().create();
    }
}
