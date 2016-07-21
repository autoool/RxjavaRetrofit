package com.techidea.appclean.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.techidea.appclean.R;
import com.techidea.appclean.base.BaseActivity;
import com.techidea.appclean.base.Injection;
import com.techidea.appclean.login.LoginFragment;
import com.techidea.appclean.login.LoginPresenter;
import com.techidea.data.net.HttpMethods;
import com.techidea.domain.entity.MemberInfoItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

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
