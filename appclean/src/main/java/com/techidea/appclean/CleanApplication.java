package com.techidea.appclean;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.squareup.leakcanary.LeakCanary;
import com.techidea.data.net.HttpMethods;

import io.fabric.sdk.android.Fabric;

/**
 * Created by zchao on 2016/5/18.
 */
public class CleanApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        if (BuildConfig.DEBUG)
//            LeakCanary.install(this);
        Fabric.with(this, new Crashlytics());
        Fabric.with(this, new Answers());
//        SslSocketFactory.getInstance().init(getApplicationContext());
        getApiHost();
    }

    private void getApiHost() {
        try {
            ApplicationInfo applicationInfo =
                    this.getPackageManager()
                            .getApplicationInfo(
                                    getPackageName(), PackageManager.GET_META_DATA);
            HttpMethods.setBaseUrl(applicationInfo.metaData.getString("API_HOST"));
        } catch (PackageManager.NameNotFoundException e) {
            HttpMethods.setBaseUrl("http://cn.bing.com");
        } catch (Exception e) {
            HttpMethods.setBaseUrl("http://cn.bing.com");
        }
    }
}
