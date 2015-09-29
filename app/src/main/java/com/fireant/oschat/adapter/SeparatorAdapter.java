package com.fireant.oschat.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.fireant.oschat.R;

import java.util.TreeSet;

/**
 * Created by zhangdeyi on 15/7/25.
 */
public abstract class SeparatorAdapter<T> extends CommonAdapter<T> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;

    private TreeSet mSeparatorsSet = new TreeSet();

    public SeparatorAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        switch (type) {
            case TYPE_ITEM:
                return super.getView(position, convertView, parent);
            default:
                return mInflater.inflate(R.layout.list_cell_separator, null);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mSeparatorsSet.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    public void addSeparatorItem(final T item) {
        mDatas.add(item);
        // save separator position
        mSeparatorsSet.add(mDatas.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }
}
