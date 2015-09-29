package com.fireant.oschat.iview;

import com.fireant.oschat.bean.LoginUser;

/**
 * Created by zhangdeyi on 15/7/21.
 */
public interface ILoginView {

    public void showLogining();
    public void hideLogining();
    public void handleLoginSuccess();
    public void showLoginError(LoginUser loginUser);
    public void showNetWorkError();
}
