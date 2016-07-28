package com.techidea.commonlibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by zchao on 2015/10/26.
 * RecyclerView的通用适配器地址  http://kymjs.com/code/2015/10/11/01/
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerHolder> {

    protected List<T> realDatas;
    protected final int mItemLayoutId;
    protected boolean isScrolling;
    protected Context context;
    private OnItemClickListener listener;
    private OnItemLongClickListener longListener;

    //是否为单一Item
    private boolean isSingleLayout = true;
    //List<Integer>
    private List<Integer> layoutIds;
    private List layoutTypeKeys;

    public interface OnItemClickListener {
        void onItemClick(View view, Object data, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, Object data, int position);
    }

    /**
     * 多Item情况下的构造方法
     *
     * @param layoutIds List<Integer> 布局Postion 为期Type 使用时重写getItemViewType() 建立与layoutIds对应关系即可
     */
    public BaseRecyclerAdapter(RecyclerView v, Collection<T> datas, List layoutTypeKeys, List<Integer> layoutIds) {
        this(v, datas, 0);
        isSingleLayout = false;
        this.layoutIds = layoutIds;
        this.layoutTypeKeys = layoutTypeKeys;
    }

    public BaseRecyclerAdapter(RecyclerView v, Collection<T> datas, int itemLayoutId) {
        if (datas == null) {
            realDatas = new ArrayList<>();
        } else if (datas instanceof List) {
            realDatas = (List<T>) datas;
        } else {
            realDatas = new ArrayList<>(datas);
        }
        mItemLayoutId = itemLayoutId;
        context = v.getContext();
        v.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                isScrolling = !(newState == RecyclerView.SCROLL_STATE_IDLE);
                if (!isScrolling) {
                    notifyDataSetChanged();
                }
            }
        });
    }

    public abstract void convert(BaseRecyclerHolder holder, T item, int position, boolean isScrolling);

    @Override
    public BaseRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View root;
        if (isSingleLayout) {
            root = inflater.inflate(mItemLayoutId, parent, false);
        } else {
            root = inflater.inflate(layoutIds.get(viewType), parent, false);
        }
        return new BaseRecyclerHolder(root, context);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerHolder holder, int position) {
        convert(holder, getData(position), position, isScrolling);
        holder.itemView.setOnClickListener(getOnClickListener(position));
        holder.itemView.setOnLongClickListener(getOnLongClickListener(position));
    }

    @Override
    public int getItemCount() {
        return realDatas.size();
    }

    public T getData(int position) {
        return realDatas.get(position);
}

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        listener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        longListener = onItemLongClickListener;
    }

    public View.OnClickListener getOnClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null && v != null) {
                    listener.onItemClick(v, realDatas.get(position), position);
                }
            }
        };
    }

    public View.OnLongClickListener getOnLongClickListener(final int position) {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (longListener != null && v != null) {
                    longListener.onItemLongClick(v, realDatas.get(position), position);
                }
                return false;
            }
        };
    }

    public BaseRecyclerAdapter<T> refresh(Collection<T> datas) {
        if (datas == null) {
            realDatas = new ArrayList<>();
        } else if (datas instanceof List) {
            realDatas = (List<T>) datas;
        } else {
            realDatas = new ArrayList<>(datas);
        }
        notifyDataSetChanged();
        return this;
    }

    //通过Key获取对应LayoutIdPostion
    public int getTypePotinByKey(Object object) {
        return layoutTypeKeys.indexOf(object);
    }
}