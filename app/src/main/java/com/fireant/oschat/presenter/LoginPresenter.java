package com.fireant.oschat.presenter;

import com.fireant.oschat.MyApp;
import com.fireant.oschat.api.OSCApi;
import com.fireant.oschat.bean.LoginUser;
import com.fireant.oschat.iview.ILoginView;
import com.fireant.oschat.utils.MyDiskLruCacheUtils;
import com.fireant.oschat.utils.XML;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**登陆控制器
 * Created by zhangdeyi on 15/7/21.
 */
public class LoginPresenter {

    private ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    public void clickLogin(String username, String pwd) {
        loginView.showLogining();
        OSCApi.login(username, pwd, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                loginView.showNetWorkError();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                loginView.hideLogining();
                if (response.isSuccessful()) {
                    LoginUser loginUser = XML.parseBean(LoginUser.class, response.body().string());
                    if (loginUser != null) {
                        if (loginUser.getResult() != null && loginUser.getResult().ok()) {
                            MyDiskLruCacheUtils.saveObjectCache(MyApp.getInstance(), LoginUser.LOGIN_CACHE_KEY, loginUser);
                            loginView.handleLoginSuccess();
                        } else {

                        }
                    }
                } else {
                    loginView.showNetWorkError();
                }
            }
        });
    }
}
