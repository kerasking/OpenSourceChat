package com.fireant.oschat.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fireant.oschat.R;

/**
 * Created by zhangdeyi on 15/7/31.
 */
public class TipView {

    private View root;

    private View llLoading;
    private TextView tvLoading;
    private View llTip;
    private ImageView ivTip;
    private TextView tvTip;

    public TipView(View root) {
        this.root = root;
        this.llLoading = root.findViewById(R.id.ll_loading);
        this.tvLoading = (TextView) root.findViewById(R.id.tv_loading);
        this.llTip = root.findViewById(R.id.ll_tip);
        this.ivTip = (ImageView) root.findViewById(R.id.iv_tip);
        this.tvTip = (TextView) root.findViewById(R.id.tv_tip);
    }

    public void setLoading() {
        setTipViewState(View.GONE);
        setLoadingViewState(View.VISIBLE);
    }

    public void setLoading(String loadingText) {
        setLoading();
        this.tvLoading.setText(loadingText);
    }

    public void setHideLoading() {
        root.setVisibility(View.GONE);
    }

    public void setNetWorkError() {
        setTipViewState(View.VISIBLE);
        setLoadingViewState(View.GONE);
        setTipInfo(R.drawable.icon_dface, "轻触屏幕重新加载");
    }

    public void setTipInfo(int tipImg, String tipText) {
        setTipViewState(View.VISIBLE);
        setLoadingViewState(View.GONE);
        this.ivTip.setImageResource(tipImg);
        this.tvTip.setText(tipText);
    }

    private void setTipViewState(int state) {
        this.llTip.setVisibility(state);
    }

    private void setLoadingViewState(int state) {
        this.llLoading.setVisibility(state);
    }
}
