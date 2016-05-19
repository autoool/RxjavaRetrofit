package com.techidea.appclean;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.techidea.data.net.CustomTrust;
import com.techidea.data.net.HttpMethods;

/**
 * Created by zchao on 2016/5/18.
 */
public class CleanApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CustomTrust.getInstance().init(getApplicationContext());
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
        }
    }
}
