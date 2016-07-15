package com.techidea.corelibrary;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.io.IOException;
import java.util.Locale;

public class CommonUtilAPP {

    public static String getAppPackageName(Context context) {
        return context.getApplicationInfo().packageName;
    }

    /**
     * 获取应用版本号
     *
     * @param context current context
     * @return appversionName
     */
    public static String getAppVersionName(Context context) {
        PackageInfo packageInfo;
        if (context == null)
            return "";
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取应用Int型版本
     *
     * @param context current context
     * @return appversioncode
     */
    public static String getAppVersionCode(Context context) {
        PackageInfo packageInfo;
        if (context == null)
            return "";
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return Integer.toString(packageInfo.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getMetaString(ApplicationInfo appInfo, String key) {
        String value = "";

        if (appInfo == null)
            return value;

        value = String.valueOf(appInfo.metaData.get(key));
        if (TextUtils.isEmpty(value))
            value = Integer.toString(appInfo.metaData.getInt(key));

        return value;
    }

    public static String getMacAddress(Context context) {

        // TODO: 2016/1/19 模拟器运行使用 ,正式运行需要删除
        if (Build.MANUFACTURER.equals("Genymotion")) {
            return "CH:AO:FF:FF:FF:01";
        }

        String macAddress;

        /*
        由于业务原因，采用蓝牙地址代替wifi macaddress，如果获取不到，依次获取Wifi、Lan的macaddress
        需要在AndroidManifest里添加：
            <uses-permission android:name="android.permission.BLUETOOTH"/>
         */
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        macAddress = bluetoothAdapter.getAddress();

        if (TextUtils.isEmpty(macAddress) && context != null) {
            //wifi macaddress
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            macAddress = info.getMacAddress();
        }

        if (TextUtils.isEmpty(macAddress)) {
            // lan macaddress
            try {
                macAddress = CommonUtilFile.loadFileAsString("/sys/class/net/eth0/address").toUpperCase(Locale.ENGLISH).substring(0, 17);
            } catch (IOException e) {
                macAddress = "";
            }
        }
        return macAddress;
    }

    public static final String DEVICE_NAME_KOOLPOS = "KOOLPOS";
    public static final String DEVICE_NAME_DONGHUA_POS = "DONGHUA_POS";
    public static final String DEVICE_NAME_COMMON_PAD = "COMMON_PAD";
    public static final String DEVICE_NAME_WIZAR_POS = "WIZARPOS";
    public static final String DEVICE_NAME_WIZARHAND = "WIZARHAND"; //手持设备(目前支持设备为WIZARHAND_Q1)

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;

        manufacturer = manufacturer.toUpperCase();
        model = model.toUpperCase();

        String deviceName;
        if (!TextUtils.isEmpty(manufacturer) && model.startsWith(manufacturer)) {
            deviceName = manufacturer;
        } else if (!TextUtils.isEmpty(model)) {
            deviceName = model;
        } else
            return DEVICE_NAME_COMMON_PAD;

        if (deviceName.contains(DEVICE_NAME_KOOLPOS))
            return DEVICE_NAME_KOOLPOS;
        else if (deviceName.contains(DEVICE_NAME_DONGHUA_POS))
            return DEVICE_NAME_DONGHUA_POS;
        else if (deviceName.contains(DEVICE_NAME_WIZAR_POS))
            return DEVICE_NAME_WIZAR_POS;
        else if (deviceName.contains(DEVICE_NAME_WIZARHAND))
            return DEVICE_NAME_WIZARHAND;
        else
            return DEVICE_NAME_COMMON_PAD;
    }

    public static int getPixByDP(Context context, float dp) {
        if (context == null)
            return 0;
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float fpixels = metrics.density * dp;
        return (int) (fpixels + 0.5f);
    }

    public static String getDeviceSerial() {
        return "Build.SERIAL: " + Build.SERIAL + "\n" +
                "Build.MANUFACTURER: " + Build.MANUFACTURER + "\n" +
                "Build.MODEL: " + Build.MODEL + "\n";
    }


}
