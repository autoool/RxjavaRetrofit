package com.techidea.commonlibrary.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by zchao on 2015/10/26.
 * RecyclerView的通用适配器地址  http://kymjs.com/code/2015/10/11/01/
 * RecyclerView的通用适配器使用的RecyclerHolder
 */
public class BaseRecyclerHolder extends RecyclerView.ViewHolder {

    private final SparseArray<View> mViews;
    private Context context;

    public BaseRecyclerHolder(View itemView, Context context) {
        super(itemView);
        //一般不会超过8个吧
        this.mViews = new SparseArray<>(8);
        this.context = context;
        ButterKnife.bind(itemView);
    }

    public SparseArray<View> getAllView() {
        return mViews;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views*
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     */
    public BaseRecyclerHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     */
    public BaseRecyclerHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     */
    public BaseRecyclerHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * 设置checkbox
     */
    public BaseRecyclerHolder setCheckBox(int viewId, boolean isSelected) {
        CheckBox view = getView(viewId);
        view.setChecked(isSelected);
        return this;
    }

    /**
     * 为ImageView设置来自url的图片
     */
    public BaseRecyclerHolder setImageByUrl(int viewId, String url, int drawableId) {
        ImageView view = getView(viewId);
        if (TextUtils.isEmpty(url) || !url.startsWith("http://"))
            view.setImageResource(drawableId);
        else
            try {
////                Bitmap bitmap = ImageCacheManager.getInstance().getBitmap(url);
//                if (bitmap!=null){
//                    view.setImageBitmap(bitmap);
//                }else{
//                    view.setImageResource(drawableId);
//                }
            } catch (IllegalStateException e) {
                view.setImageResource(drawableId);
            }

        return this;
    }

    /**
     * 为View设置drawable的背景
     */
    public BaseRecyclerHolder setBackgourndResource(int viewId, int drawableId) {
        View view = getView(viewId);
        view.setBackgroundResource(drawableId);

        return this;
    }

    /**
     * 为View设置背景颜色
     */
    public BaseRecyclerHolder setBackgourndColor(int viewId, int colorId) {
        View view = getView(viewId);
        view.setBackgroundColor(colorId);

        return this;
    }

    /**
     * 为ItemView设置drawable背景
     */
    public BaseRecyclerHolder setItemViewBackgourndResource(int drawableId) {
        itemView.setBackgroundResource(drawableId);

        return this;
    }

    /**
     * 为ItemView设置背景颜色
     */
    public BaseRecyclerHolder setItemViewBackgourndColor(int colorId) {
        itemView.setBackgroundColor(colorId);

        return this;
    }

    public BaseRecyclerHolder setViewVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    public BaseRecyclerHolder setViewSelected(int viewId, boolean select) {
        View view = getView(viewId);
        view.setSelected(select);
        return this;
    }


   /* public void setSpinner(int viewId,CommonSpinnerAdapter spinnerAdapter, AdapterView.OnItemSelectedListener itemClickListener) {
        Spinner spinner = getView(viewId);
        spinner.setAdapter(spinnerAdapter);
        //        设置偏移量
        int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        spinner.measure(measureSpec, measureSpec);
        int dropDownVerticalOffset = spinner.getMeasuredHeight();
        spinner.setDropDownVerticalOffset(dropDownVerticalOffset);
        spinner.setOnItemSelectedListener(itemClickListener);
    }*/

    public boolean setSpinneSelected(int viewId, int postion) {
        if (postion < 0)
            return false;
        Spinner spinner = getView(viewId);
        if (postion > spinner.getCount())
            return false;
        spinner.setSelection(postion);
        return true;
    }

    public void setClickListener(int viewId, View.OnClickListener clickListener) {
        View view = getView(viewId);
        view.setOnClickListener(clickListener);
    }

}
