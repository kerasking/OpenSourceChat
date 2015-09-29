package com.fireant.oschat.view;

import android.app.ProgressDialog;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import com.fireant.oschat.R;
import com.fireant.oschat.bean.LoginUser;
import com.fireant.oschat.iview.ILoginView;
import com.fireant.oschat.presenter.LoginPresenter;
import com.fireant.oschat.utils.ActivityUtils;
import com.fireant.oschat.utils.DialogHelp;
import com.fireant.oschat.view.base.BaseDonNeedIncludeToolBarActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 登录界面
 * Created by zhangdeyi on 15/7/19.
 */
public class LoginActivity extends BaseDonNeedIncludeToolBarActivity implements ILoginView {

    @Bind(R.id.tl_username)
    TextInputLayout tlUsername;
    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.tl_pwd)
    TextInputLayout tlPwd;
    @Bind(R.id.et_pwd)
    EditText etPwd;

    private LoginPresenter loginPresenter;

    @Override
    protected void afterView() {
        super.afterView();
        loginPresenter = new LoginPresenter(this);
        waitingDialog = DialogHelp.getWaitDialog(this, "登录中...");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.bt_login)
    void clickLogin() {
        if (checkInput()) {
            return;
        }
        String username = etUsername.getText().toString();
        String pwd = etPwd.getText().toString();

        loginPresenter.clickLogin(username, pwd);
    }

    private boolean checkInput() {
        if (etUsername.length() == 0) {
            tlUsername.setError("请输入邮箱或者用户名");
            etUsername.requestFocus();
            return true;
        }

        if (etPwd.length() == 0) {
            tlPwd.setError("请输入密码");
            etPwd.requestFocus();
            return true;
        }

        return false;
    }

    ProgressDialog waitingDialog;

    @Override
    public void showLogining() {
        waitingDialog.show();
    }

    @Override
    public void hideLogining() {
        waitingDialog.dismiss();
    }

    @Override
    public void handleLoginSuccess() {
        ActivityUtils.skipActivity(this, MainActivity.class);
    }

    @Override
    public void showLoginError(LoginUser loginUser) {

    }

    @Override
    public void showNetWorkError() {

    }
}
