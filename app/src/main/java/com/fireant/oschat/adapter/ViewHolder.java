package com.fireant.oschat.adapter;

/**
 * Created by zhangdeyi on 15/7/25.
 */

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * 通用性极高的ViewHolder
 * Created by 火蚁 on 15/4/8.
 */
public class ViewHolder {
    // 用于存储listView item的容器
    private SparseArray<View> mViews;

    private int layoutId;

    // item根view
    private View mConvertView;

    protected Context mContext;

    private int position;

    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mViews = new SparseArray<View>();
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.mConvertView.setTag(this);
        this.mContext = context;
        this.position = position;
        this.layoutId = layoutId;
    }

    /**
     * 获取一个viewHolder
     *
     * @param context     context
     * @param convertView view
     * @param parent      parent view
     * @param layoutId    布局资源id
     * @param position    索引
     * @return
     */
    public static ViewHolder getViewHolder(Context context, View convertView, ViewGroup parent, int layoutId, int position) {

        boolean needCreateView = false;
        ViewHolder vh = null;
        if (convertView == null) {
            needCreateView = true;
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        if (vh != null && (vh.layoutId != layoutId)) {
            needCreateView = true;
        }
        if (needCreateView) {
            return new ViewHolder(context, parent, layoutId, position);
        }
        return (ViewHolder) convertView.getTag();
    }

    public int getPosition() {
        return this.position;
    }

    // 通过一个viewId来获取一个view
    public <T extends View> T getView(int viewId) {

        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    // 返回viewHolder的容器类
    public View getConvertView() {
        return this.mConvertView;
    }

    // 给TextView设置文字
    public void setText(int viewId, String text) {
        if (TextUtils.isEmpty(text)) return;
        TextView tv = getView(viewId);
        tv.setText(text);
    }

    // 给TextView设置文字
    public void setText(int viewId, SpannableString text) {
        if (text == null) return;
        TextView tv = getView(viewId);
        tv.setText(text);
    }

    // 给TextView设置文字
    public void setText(int viewId, Spanned text) {
        if (text == null) return;
        TextView tv = getView(viewId);
        tv.setText(text);
    }

    // 给TextView设置文字
    public void setText(int viewId, int textRes) {
        TextView tv = getView(viewId);
        tv.setText(textRes);
    }

    public void setText(int viewId, String text, boolean gone) {
        if (TextUtils.isEmpty(text) && gone) {
            getView(viewId).setVisibility(View.GONE);
            return;
        }
        getView(viewId).setVisibility(View.VISIBLE);
        setText(viewId, text);
    }

    public void setText(int viewId, String text, int emptyRes) {
        TextView tv = getView(viewId);
        if (TextUtils.isEmpty(text)) {
            tv.setText(emptyRes);
        } else {
            tv.setText(text);
        }
    }

    public void setText(int viewId, String text, String emptyText) {
        TextView tv = getView(viewId);
        if (TextUtils.isEmpty(text)) {
            tv.setText(emptyText);
        } else {
            tv.setText(text);
        }
    }

    public void setImage(int viewId, int res) {
        ImageView imageView = getView(viewId);
        imageView.setBackgroundResource(res);
    }

    public void setImageForNet(int viewId, String imgUrl, int emptyRes) {
        ImageView imageView = getView(viewId);

        if (TextUtils.isEmpty(imgUrl)) {
            imageView.setImageResource(emptyRes);
            return;
        } else {
            Picasso.with(mContext)
                    .load(imgUrl)
                    .into(imageView);
            imageView.setTag(imgUrl);
        }
    }

    public void setGone(int viewId) {
        getView(viewId).setVisibility(View.GONE);
    }

    public void setVisible(int viewId) {
        getView(viewId).setVisibility(View.VISIBLE);
    }

    public void setInVisible(int viewId) {
        getView(viewId).setVisibility(View.INVISIBLE);
    }
}
