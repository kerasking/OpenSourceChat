package com.fireant.oschat.view.base;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fireant.oschat.R;
import com.squareup.picasso.Picasso;

/**
 * 基类activity
 *
 * 旨在封装一些基础的操作，并且让继承的界面尽量少申明变量
 * Created by zhangdeyi on 15/7/19.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;

    protected void initToolBar() {
        toolbar = findView(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    protected abstract int getLayoutId();
    protected void afterView() {}

    protected <T extends View> T findView(int viewId) {
       return (T) findViewById(viewId);
    }

    protected void setText(int viewId, String text) {
        TextView textView = findView(viewId);
        if (TextUtils.isEmpty(text)) {
            return;
        }
        textView.setText(text);
    }

    protected void setText(int viewId, String text, String emptyTip) {
        TextView textView = findView(viewId);
        if (TextUtils.isEmpty(text)) {
            textView.setText(emptyTip);
            return;
        }
        textView.setText(text);
    }

    protected void setTextEmptyGone(int viewId, String text) {
        TextView textView = findView(viewId);
        if (TextUtils.isEmpty(text)) {
            textView.setVisibility(View.GONE);
            return;
        }
        textView.setText(text);
    }

    protected void setImage(int viewId, int imgRes) {
        ImageView imageView = findView(viewId);
        imageView.setImageResource(imgRes);
    }

    protected void setImageForNet(int viewId, String url, int emptyRes) {
        ImageView imageView = findView(viewId);
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(emptyRes);
            return;
        }
        Picasso.with(this)
                .load(url)
                .into(imageView);
    }

    @Override
    public void onClick(View v) {

    }
}
