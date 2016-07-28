package com.techidea.appclean.adapter;

import android.support.v7.widget.RecyclerView;

import com.techidea.appclean.R;
import com.techidea.commonlibrary.adapter.BaseRecyclerAdapter;
import com.techidea.commonlibrary.adapter.BaseRecyclerHolder;
import com.techidea.domain.entity.CityInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by zchao on 2016/7/27.
 */
public class CityInfoAdapter extends BaseRecyclerAdapter<CityInfo> {

    private List<CityInfo> mDatas;

    public CityInfoAdapter(RecyclerView v, Collection<CityInfo> datas, int itemLayoutId) {
        super(v, datas, itemLayoutId);
        this.mDatas = new ArrayList<>(datas);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, CityInfo item, int position, boolean isScrolling) {
        holder.setText(R.id.textview_cityinfo_city, item.getCity());
        holder.setText(R.id.textview_cityinfo_cnty, item.getCnty());
        holder.setText(R.id.textview_cityinfo_prov, item.getProv());
    }
}
