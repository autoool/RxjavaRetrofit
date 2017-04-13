package com.techidea.appclean.uploadfile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Toast;

import com.techidea.appclean.R;
import com.techidea.appclean.base.Injection;
import com.techidea.domain.interactor.DefaultSubscriber;
import com.techidea.domain.interactor.UploadFileWithPartMap;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zchao on 2016/11/29.
 */

public class UploadFileActivity extends AppCompatActivity {

    private UploadFileWithPartMap uploadFileWithPartMap;
    private UploafFileSubscriber subscriber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadfile);
        ButterKnife.bind(this);
        uploadFileWithPartMap = Injection.provideUploadFileWithPartMap(getApplicationContext());

    }

    @OnClick(R.id.button_uploadfile_param)
    public void uploadFileParam() {
        String appKey = "zhangchaoappkey";
        File cache = this.getFilesDir();
        File file = new File(cache, "zchao");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = file.getAbsolutePath();
        File file1 = new File(path);
        if (file1.exists()) {
            subscriber = new UploafFileSubscriber();
            uploadFileWithPartMap.initParams(file1, appKey);
            uploadFileWithPartMap.execute().subscribe(subscriber);
        }
    }

    private class UploafFileSubscriber extends DefaultSubscriber<String> {
        @Override
        public void onCompleted() {
            subscriber = null;
        }

        @Override
        public void onError(Throwable e) {
            toast(e.getMessage());
            subscriber = null;
        }

        @Override
        public void onNext(String s) {
            toast(s);
        }
    }

    private void toast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
