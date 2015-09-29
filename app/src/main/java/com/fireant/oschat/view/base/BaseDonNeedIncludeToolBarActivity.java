package com.fireant.oschat.view.base;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import butterknife.ButterKnife;

/**
 * 不需要引入toobar的activity
 *
 * Created by zhangdeyi on 15/9/18.
 */
public abstract class BaseDonNeedIncludeToolBarActivity extends BaseHaveToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        if (havaBackButton()) {
            checkBackButton();
        }
        afterView();
    }

    private void checkBackButton() {
        if (havaBackButton()) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeButtonEnabled(false);
            } else {
                throw new IllegalArgumentException("tool bar is null");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    protected boolean havaBackButton() {
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (havaBackButton()) {
            finish();
            return true;
        }
        return super.onSupportNavigateUp();
    }
}
