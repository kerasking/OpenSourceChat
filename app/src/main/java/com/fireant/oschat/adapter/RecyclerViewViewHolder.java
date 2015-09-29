package com.fireant.oschat.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fireant.oschat.R;
import com.squareup.picasso.Picasso;

/**
 * RecycleView 通用ViewHolder
 * <p/>
 * Created by zhangdeyi on 15/9/21.
 */
public class RecyclerViewViewHolder extends RecyclerView.ViewHolder {

    private View mConvertView;
    private SparseArray<View> mViews;
    private Context mContext;
    private int mPosition;


    public RecyclerViewViewHolder(Context context, View itemView, int position) {
        super(itemView);
        this.mContext = context;
        this.mConvertView = itemView;
        this.mViews = new SparseArray<>();
        this.mPosition = position;
    }

    /***
     * 获取view
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public RecyclerViewViewHolder setVisible(int viewId, boolean isVisible) {
        View view = getView(viewId);
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 设置文字start
     **/

    public RecyclerViewViewHolder setText(int viewId, String text) {
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }

    public RecyclerViewViewHolder setText(int viewId, CharSequence charSequence) {
        TextView textView = getView(viewId);
        textView.setText(charSequence);
        return this;
    }

    public RecyclerViewViewHolder setText(int viewId, Spanned spanned) {
        TextView textView = getView(viewId);
        textView.setText(spanned);
        return this;
    }

    public RecyclerViewViewHolder setText(int viewId, int textRes) {
        TextView textView = getView(viewId);
        textView.setText(textRes);
        return this;
    }

    /** 设置文字end **/

    /**
     * 设置图片start
     **/

    public RecyclerViewViewHolder setImageResource(int viewId, int res) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(viewId);
        return this;
    }

    public RecyclerViewViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView imageView = getView(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    public RecyclerViewViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView imageView = getView(viewId);
        imageView.setImageDrawable(drawable);
        return this;
    }

    public RecyclerViewViewHolder setImageFromNet(int viewId, String imageUrl) {
        if (TextUtils.isEmpty(imageUrl)) {

            return this;
        }
        ImageView imageView = getView(viewId);
        Picasso.with(this.mContext)
                .load(imageUrl)
                .error(R.mipmap.ic_launcher)
                .into(imageView);

        return this;
    }

    /** 设置图片end **/
    public void setImageForNet(int viewId, String imgUrl, int emptyRes) {
        ImageView imageView = getView(viewId);

        if (TextUtils.isEmpty(imgUrl)) {
            imageView.setImageResource(emptyRes);
            return;
        }
        imageView.setTag(imgUrl);
        Picasso.with(mContext)
                .load(imgUrl)
                .into(imageView);
    }
}
