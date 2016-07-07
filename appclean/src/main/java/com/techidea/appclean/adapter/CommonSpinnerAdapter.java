package com.techidea.appclean.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.techidea.appclean.R;

import java.util.List;

/**
 * Created by zchao on 2016/7/7.
 */
public class CommonSpinnerAdapter extends BaseAdapter {

    private List<SpinnerItem> mDatas;

    public CommonSpinnerAdapter(Context context, List<SpinnerItem> datas) {
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        if (position >= 0 && position < mDatas.size())
            return mDatas.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderItem holderItem;
        TextView textViewName;
        if (convertView == null) {
            holderItem = new HolderItem();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_spinner_item, parent, false);
            textViewName = (TextView) convertView.findViewById(R.id.spinner_name);
            holderItem.textViewname = textViewName;
            convertView.setTag(holderItem);
        } else {
            holderItem = (HolderItem) convertView.getTag();
            textViewName = holderItem.textViewname;
        }
        textViewName.setText(mDatas.get(position).getName());
        return convertView;
    }

    private static class HolderItem {
        private TextView textViewname;

        public HolderItem() {
        }
    }
}
