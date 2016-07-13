package com.techidea.appclean.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.techidea.appclean.R;
import com.techidea.corelibrary.util.CommonUtilAPP;
import com.techidea.data.net.HttpMethods;
import com.techidea.domain.entity.MemberInfoItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.button_member)
    Button mButtonMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.button_member)
    void buttonMember() {
        HttpMethods.getInstance().getMemberInfo("3452354", "STANDARD")
                .subscribe(new Observer<MemberInfoItem>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("error", e.getMessage());
                    }

                    @Override
                    public void onNext(MemberInfoItem memberInfoItem) {
                        Log.d("memberInfoItem", memberInfoItem.getMemberName());
                    }
                });
    }
}
