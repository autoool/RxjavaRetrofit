package com.techidea.appclean.main;

import android.os.Bundle;

import com.techidea.appclean.R;
import com.techidea.appclean.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager()
                .findFragmentById(R.id.framelayout_main);
        if (mainFragment == null) {
            mainFragment = MainFragment.getInstance();
            addFragment(R.id.framelayout_main, mainFragment);
        }
    }

}
