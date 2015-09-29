package com.fireant.oschat.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fireant.oschat.view.base.BaseFragment;

/**
 * Created by zhangdeyi on 15/7/25.
 */
public abstract class LazyFragment extends BaseFragment {

    private boolean isInit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isInit = true;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint() && isInit) {
            isInit = false;
            lazyLoad();
        }
    }

    protected abstract void lazyLoad();
}
