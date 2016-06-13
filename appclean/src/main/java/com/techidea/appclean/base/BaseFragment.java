package com.techidea.appclean.base;

import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by zchao on 2016/5/18.
 */
public abstract class BaseFragment extends Fragment {

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity().getApplicationContext(),
                message, Toast.LENGTH_SHORT).show();
    }
}
