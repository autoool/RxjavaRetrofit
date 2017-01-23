package com.techidea.appclean.view.main;

import android.os.Bundle;

import com.techidea.appclean.R;
import com.techidea.appclean.base.BaseActivity;



import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonhttpclient)
    void buttonhttpclient(){

    }

    @OnClick(R.id.buttonhttpsclient)
    void buttonhttpsclient(){

    }

    @OnClick(R.id.buttonhttpsclientone)
    void buttonhttpsclientone(){

    }

    @OnClick(R.id.buttonokhttp)
    void buttonokhttp(){

    }

    @OnClick(R.id.buttonokhttps)
    void buttonokhttps(){

    }

    @OnClick(R.id.buttonokhttpsone)
    void buttonokhttpsone(){

    }

    @OnClick(R.id.buttonhttpsurl)
    void buttonhttpsurl(){

    }

    @OnClick(R.id.buttonhttpurl)
    void buttonhttpurl(){

    }

    @OnClick(R.id.buttonhttpsurlone)
    void buttonhttpsurlone(){

    }


}
