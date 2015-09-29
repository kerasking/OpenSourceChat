package com.fireant.oschat.iview;

/**
 * 加载详情数据需要实现接口
 * Created by zhangdeyi on 15/7/23.
 */
public interface ILoadDetailView<T> {

    // 显示加载中
    public void showLoading();
    // 隐藏加载状态
    public void hideLoading();
    // 显示数据
    public void showData(T t);
    // 显示数据加载出错
    public void showError();
    // 显示网络出错
    public void showNetError();
}
