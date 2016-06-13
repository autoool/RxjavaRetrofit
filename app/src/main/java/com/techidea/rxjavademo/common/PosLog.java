package com.techidea.rxjavademo.common;

import android.util.Log;

import com.techidea.rxjavademo.BuildConfig;
import com.techidea.rxjavademo.RxjavaApplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by zchao on 2016/6/12.
 */
public class PosLog {

    public static boolean isDebug = BuildConfig.DEBUG;
    public static final String PATH = RxjavaApplication.cacheDir = "/Log";
    public static final String LOG_FILE_NAME = "log.txt";

    public static final boolean LOG_WRITE_TO_FILE = true;

    public static void e(String TAG, String msg) {
        Log.e(TAG, log(msg));
        if (LOG_WRITE_TO_FILE) {
            writeLogtoFile("e", TAG, msg);
        }
    }

    public static void w(String TAG, String msg) {
        if (isDebug) {
            Log.w(TAG, log(msg));
            if (LOG_WRITE_TO_FILE)
                writeLogtoFile("w", TAG, msg);
        }
    }

    public static void d(String TAG, String msg) {
        if (isDebug) {
            Log.d(TAG, log(msg));
            if (LOG_WRITE_TO_FILE)
                writeLogtoFile("d", TAG, msg);
        }
    }

    /**
     * 提示信息
     */
    public static void i(String TAG, String msg) {
        if (isDebug) {
            Log.i(TAG, log(msg));
            if (LOG_WRITE_TO_FILE) {
                writeLogtoFile("i", TAG, msg);
            }
        }
    }

    public static void e(String msg) {
        e(getClassName(), msg);
    }

    public static void w(String msg) {
        w(getClassName(), msg);
    }

    public static void d(String msg) {
        d(getClassName(), msg);
    }

    public static void i(String msg) {
        i(getClassName(), msg);
    }


    public static void isExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * @return 当前的类名(simpleName)
     */
    private static String getClassName() {

        String result;
        StackTraceElement thisMethodStack = Thread.currentThread().getStackTrace()[2];
        result = thisMethodStack.getClassName();
        int lastIndex = result.lastIndexOf(".");
        result = result.substring(lastIndex + 1);

        int i = result.indexOf("$");// 剔除匿名内部类名

        return i == -1 ? result : result.substring(0, i);
    }

    private static void writeLogtoFile(String logtype, String tag, String msg) {
        isExist(PATH);
        String writeMessage = "\r\n"
                + Time.getNowMDHMSTime()
                + "\r\n"
                + logtype
                + "      "
                + tag
                + "\r\n"
                + msg;
        File file = new File(PATH, LOG_FILE_NAME);
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(writeMessage);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static String log(String message) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[5];
        String className = stackTraceElement.getClassName();
        className = className.substring(className.lastIndexOf('.') + 1) + ".java";
        int lineNumber = stackTraceElement.getLineNumber();
        if (lineNumber < 0) lineNumber = 0;
        return "(" + className + ":" + lineNumber + ") " + message;
    }
}
