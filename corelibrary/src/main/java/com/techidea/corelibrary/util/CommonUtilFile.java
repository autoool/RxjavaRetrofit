package com.techidea.corelibrary.util;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CommonUtilFile {

    public static String loadFileAsString(String filePath) throws java.io.IOException {
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }

    public static File getDiskCacheDir(Context context, String uniqueName) {
        if (context==null)
            return null;
        Context appContext = context.getApplicationContext();
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = appContext.getExternalFilesDir("").getPath();
        } else {
            cachePath = appContext.getFilesDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    public static String getDiskCachePath(Context context, String uniqueName) {
        if (context==null)
            return null;
        Context appContext = context.getApplicationContext();
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = appContext.getExternalFilesDir("").getPath();
        } else {
            cachePath = appContext.getFilesDir().getPath();
        }
        return cachePath + File.separator + uniqueName;
    }
}
