package com.techidea.presentation.clean.base;

import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by zchao on 2016/5/18.
 */
public abstract class CBaseFragment extends Fragment {

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity().getApplicationContext(),
                message, Toast.LENGTH_SHORT);
    }
}
