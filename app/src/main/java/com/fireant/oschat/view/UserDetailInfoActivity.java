package com.fireant.oschat.view;

import com.fireant.oschat.IPresenter.IUserInfoDetailPresenter;
import com.fireant.oschat.R;
import com.fireant.oschat.bean.ActiveList;
import com.fireant.oschat.bean.User;
import com.fireant.oschat.iview.ILoadDetailView;
import com.fireant.oschat.presenter.UserInfoDetailPresenter;
import com.fireant.oschat.view.base.BaseDonNeedIncludeToolBarActivity;

import butterknife.OnClick;

/**
 * 用户主页
 * Created by zhangdeyi on 15/7/29.
 */
public class UserDetailInfoActivity extends BaseDonNeedIncludeToolBarActivity implements ILoadDetailView<ActiveList> {

    public static final String BUNDLE_KEY_ID = "bundle_key_id";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_detail_info;
    }

    private IUserInfoDetailPresenter presenter;
    private int hisuid;
    private User user;

    @Override
    protected void afterView() {
        super.afterView();
        hisuid = getIntent().getExtras().getInt(BUNDLE_KEY_ID);
        presenter = new UserInfoDetailPresenter(this);
        presenter.loadDetail(hisuid);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showData(ActiveList activeList) {
        User user = activeList.getUser();
        this.user = user;
        setText(R.id.tv_name, user.getName());
        setText(R.id.tv_time, user.getJointime());
        int gender = user.getGender().equals("男") ? R.drawable.icon_boy : R.drawable.icon_gril;
        setImage(R.id.iv_gender, gender);
        setImageForNet(R.id.iv_portrait, user.getPortrait(), R.drawable.icon_dface);
        setText(R.id.tv_location, user.getFrom());
        setText(R.id.tv_devplatform, user.getDevplatform());
        setText(R.id.tv_expertise, user.getExpertise());
    }

    @Override
    public void showError() {

    }

    @Override
    public void showNetError() {

    }

    @OnClick(R.id.bt_send_message)
    void toSendMessage(){
        Display.showChatActivity(this, hisuid, user.getName());
    }
}
