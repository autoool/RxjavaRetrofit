package com.techidea.appclean.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techidea.appclean.R;
import com.techidea.appclean.base.BaseActivity;
import com.techidea.appclean.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zchao on 2016/6/13.
 */
public class MainFragment extends BaseFragment {


    @Bind(R.id.textview_title)
    TextView mTextViewTitle;

    private Toolbar mToolbar;

    public MainFragment() {
    }

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = initToolbar(R.id.toolbar_main);
        mToolbar.setNavigationIcon(R.drawable.mobile_back);
        mTextViewTitle.setText("MainActivity");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
