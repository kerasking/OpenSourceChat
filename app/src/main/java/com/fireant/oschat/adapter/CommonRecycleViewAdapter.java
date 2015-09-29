package com.fireant.oschat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用的RecycleView适配器
 *
 * Created by zhangdeyi on 15/9/21.
 */
public abstract class CommonRecycleViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewViewHolder> {

    private List<T> mDatas;// 数据集合，可以增删
    private int mLayoutId;// 布局id
    private Context mContext;// 上下文
    private LayoutInflater mLayoutInflater;

    public CommonRecycleViewAdapter(Context context, int layoutId) {
        this.mContext = context;
        this.mLayoutId = layoutId;
        mLayoutInflater = LayoutInflater.from(context);
        mDatas = new ArrayList<>();
    }

    @Override
    public RecyclerViewViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View rootView = mLayoutInflater.inflate(this.mLayoutId, viewGroup, false);
        return new RecyclerViewViewHolder(mContext, rootView, i);
    }

    @Override
    public void onBindViewHolder(RecyclerViewViewHolder recycleViewViewHolder, int i) {
        conver(recycleViewViewHolder, getItem(i));
    }

    @Override
    public int getItemCount() {
        return this.mDatas.size();
    }

    public T getItem(int position) {
        return this.mDatas.get(position);
    }

    /***
     * 添加一组数据
     * @param datas
     */
    public void addAll(List<T> datas) {
        this.mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    /***
     * 添加一组数据，从指定位置开始
     * @param datas
     * @param position
     */
    public void addAll(List<T> datas, int position) {
        this.mDatas.addAll(position, datas);
        notifyDataSetChanged();
    }

    /***
     * 添加单条数据
     * @param data
     */
    public void addItem(T data) {
        this.mDatas.add(data);
        notifyDataSetChanged();
    }

    /***
     * 添加单条数据到指定的位置
     * @param data
     * @param position
     */
    public void addItem(T data, int position) {
        this.mDatas.add(position, data);
        notifyDataSetChanged();
    }

    /***
     * 移除一条数据
     * @param position
     */
    public void removeItem(int position) {
        this.mDatas.remove(position);
        notifyDataSetChanged();
    }

    /***
     * 清空适配器
     */
    public void clearDatas() {
        this.mDatas.clear();
        notifyDataSetChanged();
    }

    /**
     * 提供一个供外部填充数据的实现方法
     * @param vh
     * @param data
     */
    protected abstract void conver(RecyclerViewViewHolder vh, T data);
}
