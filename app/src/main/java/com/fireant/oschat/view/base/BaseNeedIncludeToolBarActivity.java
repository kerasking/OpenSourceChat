package com.fireant.oschat.view.base;

import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * 需要includetoolbar布局的界面
 * Created by zhangdeyi on 15/9/18.
 */
public abstract class BaseNeedIncludeToolBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initToolBar();
        ButterKnife.bind(this);
        afterView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
