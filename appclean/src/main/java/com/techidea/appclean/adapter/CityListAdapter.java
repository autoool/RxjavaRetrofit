package com.techidea.appclean.adapter;

import android.support.v7.widget.RecyclerView;

import com.techidea.appclean.R;
import com.techidea.commonlibrary.adapter.BaseRecyclerAdapter;
import com.techidea.commonlibrary.adapter.BaseRecyclerHolder;
import com.techidea.domain.entity.CityItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by zchao on 2016/7/20.
 */
public class CityListAdapter extends BaseRecyclerAdapter<CityItem> {

    private List<CityItem> mDatas;

    public CityListAdapter(RecyclerView v, Collection<CityItem> datas, int itemLayoutId) {
        super(v, datas, itemLayoutId);
        this.mDatas = new ArrayList<>(datas);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, CityItem item, int position, boolean isScrolling) {
        holder.setText(R.id.textview_cityid,item.getArea_id());
        holder.setText(R.id.textview_cityname,item.getName_cn());
    }
}
