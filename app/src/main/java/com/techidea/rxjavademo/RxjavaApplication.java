package com.techidea.rxjavademo;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.techidea.rxjavademo.component.ApplicationComponent;
import com.techidea.rxjavademo.network.CustomTrust;

/**
 * Created by zchao on 2016/3/25.
 */
public class RxjavaApplication extends Application {

    private static ApplicationComponent mComponent;
    public static Context context;
    public static String cacheDir = "";

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        CustomTrust.getInstance().init(context);

        if (getApplicationContext().getExternalCacheDir() != null
                && existSDCard()) {
            cacheDir = getExternalCacheDir().toString();
        } else {
            cacheDir = getCacheDir().toString();
        }
    }

    public static Context getContext() {
        return context;
    }

    private boolean existSDCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
