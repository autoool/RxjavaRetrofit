package com.techidea.data.cache;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by zchao on 2016/5/4.
 */
public class FileManager {

    public FileManager() {
    }

    public void writeToFile(File file, String fileContent) {
        if (!file.exists()) {
            try {
                FileWriter writer = new FileWriter(file);
                writer.write(fileContent);
                writer.close();
            } catch (FileNotFoundException e) {

            } catch (IOException ioe) {

            } finally {

            }
        }
    }

    public String readFileContent(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        if (file.exists()) {
            String stringLine;
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while ((stringLine = bufferedReader.readLine()) != null) {
                    stringBuilder.append(stringLine + "\n");
                }
                bufferedReader.close();
                fileReader.close();
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } finally {

            }
        }
        return stringBuilder.toString();
    }

    public boolean exists(File file) {
        return file.exists();
    }

    public void clearDirectory(File directory) {
        if (directory.exists()) {
            for (File file : directory.listFiles()) {
                file.delete();
            }
        }
    }

    public void writeToPreferences(Context context, String preFileName, String key,
                                   long value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public long getFromPreferences(Context context, String preFileName, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preFileName, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, 0);
    }
}
