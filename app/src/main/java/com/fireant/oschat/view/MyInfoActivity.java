package com.fireant.oschat.view;

import android.widget.ListView;

import com.fireant.oschat.R;
import com.fireant.oschat.adapter.SeparatorAdapter;
import com.fireant.oschat.adapter.ViewHolder;
import com.fireant.oschat.bean.ListLeftRightBean;
import com.fireant.oschat.bean.LoginUser;
import com.fireant.oschat.bean.User;
import com.fireant.oschat.view.base.BaseDonNeedIncludeToolBarActivity;

import butterknife.Bind;

/**
 * 个人信息
 * Created by zhangdeyi on 15/7/29.
 */
public class MyInfoActivity extends BaseDonNeedIncludeToolBarActivity {

    @Bind(R.id.listView)
    ListView listView;

    private SeparatorAdapter<ListLeftRightBean> adapter;

    private User loginUser;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_info;
    }

    @Override
    protected void afterView() {
        super.afterView();
        loginUser = LoginUser.getLoginUser(this).getUser();
        adapter = new SeparatorAdapter<ListLeftRightBean>(this, R.layout.list_cell_my_info) {
            @Override
            public void convert(ViewHolder vh, ListLeftRightBean item, int position) {
                vh.setText(R.id.tv_left, item.getLeft());

                switch (position) {
                    case 1:
                        vh.setGone(R.id.tv_right);
                        vh.setVisible(R.id.iv_right);
                        vh.setImageForNet(R.id.iv_right, loginUser.getPortrait(), R.drawable.icon_dface);
                        break;
                    default:
                        vh.setGone(R.id.iv_right);
                        vh.setText(R.id.tv_right, item.getRight(), "未填写");
                        break;
                }
            }
        };

        listView.setAdapter(adapter);

        adapter.addSeparatorItem(null);
        adapter.addItem(new ListLeftRightBean("头像", loginUser.getPortrait()));
        adapter.addItem(new ListLeftRightBean("昵称", loginUser.getName()));
        adapter.addSeparatorItem(null);

        adapter.addItem(new ListLeftRightBean("性别", loginUser.getGenderShow()));
        adapter.addItem(new ListLeftRightBean("地区", loginUser.getLocation()));
        adapter.addItem(new ListLeftRightBean("个性签名", loginUser.getDevplatform()));
    }
}
