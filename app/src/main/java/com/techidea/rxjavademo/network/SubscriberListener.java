package com.techidea.rxjavademo.network;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.techidea.rxjavademo.RxjavaApplication;
import com.techidea.rxjavademo.httpinterf.SubscriberOnCompletedListener;
import com.techidea.rxjavademo.httpinterf.SubscriberOnNextListener;

import java.net.UnknownHostException;

import rx.Subscriber;

/**
 * Created by zchao on 2016/3/25.
 */
public class SubscriberListener<T> extends Subscriber<T> {

    private static class NetWorkHandler extends Handler {

        public NetWorkHandler() {
        }

        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(RxjavaApplication.getContext(), msg.obj.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    private SubscriberOnNextListener subscriberOnNextListener;
    private SubscriberOnCompletedListener subscriberOnCompletedListener;

    private NetWorkHandler netWorkHandler;

    public SubscriberListener(SubscriberOnNextListener subscriberOnNextListener,
                              SubscriberOnCompletedListener subscriberOnCompletedListener
    ) {
        this.subscriberOnNextListener = subscriberOnNextListener;
        this.subscriberOnCompletedListener = subscriberOnCompletedListener;
    }

    @Override
    public void onStart() {
        super.onStart();
        netWorkHandler = new NetWorkHandler();
    }

    @Override
    public void onCompleted() {
        subscriberOnCompletedListener.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        String msg = "";
        int code = 0;
        if (e instanceof UnknownHostException) {
            msg = "UnknownHostException";
            code = 1;
        } else if (e instanceof ArithmeticException) {
            msg = "ArithmeticException";
            code = 2;
        } else if (!TextUtils.isEmpty(e.getMessage())) {
            msg = e.getMessage();
        } else {
            msg = "请联系管理员";
            code = 3;
        }
        sendMessage(code, msg);
    }

    @Override
    public void onNext(T t) {
        subscriberOnNextListener.onNext(t);
    }

    private void sendMessage(int what, String msg) {
        if (netWorkHandler != null) {
            Message message = Message.obtain();
            message.what = what;
            message.obj = msg;
            netWorkHandler.sendMessage(message);
        }
    }
}
