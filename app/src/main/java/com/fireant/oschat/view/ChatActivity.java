package com.fireant.oschat.view;

import android.text.Html;
import android.widget.ListView;

import com.fireant.oschat.IPresenter.IChatMessagePresenter;
import com.fireant.oschat.R;
import com.fireant.oschat.adapter.CommonAdapter;
import com.fireant.oschat.adapter.ViewHolder;
import com.fireant.oschat.bean.LoginUser;
import com.fireant.oschat.bean.Message;
import com.fireant.oschat.iview.ILoadDetailView;
import com.fireant.oschat.presenter.ChatMessagePresenter;
import com.fireant.oschat.view.base.BaseDonNeedIncludeToolBarActivity;

import java.util.List;

import butterknife.Bind;

/**
 * 用户聊天界面
 * Created by zhangdeyi on 15/7/29.
 */
public class ChatActivity extends BaseDonNeedIncludeToolBarActivity implements ILoadDetailView<List<Message>> {

    public static final String BUNDLE_KEY_HISUID = "bundle_key_hisuid";
    public static final String BUNDLE_KEY_HISNAME = "bundle_key_hisname";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Bind(R.id.listView)
    ListView listView;

    private CommonAdapter<Message> adapter;

    private IChatMessagePresenter presenter;

    private  int hisuid;
    private String hisName;

    @Override
    protected void afterView() {
        super.afterView();
        adapter = new CommonAdapter<Message>(this, R.layout.list_cell_message_right) {

            int uid = LoginUser.getLoginUid(mContext);

            @Override
            protected int getLayoutId(int position) {
                if (uid == mDatas.get(position).getSend().getId()) {
                    return R.layout.list_cell_message_right;
                }

                return R.layout.list_cell_message_left;
            }

            @Override
            public void convert(ViewHolder vh, Message item, int position) {
                vh.setImageForNet(R.id.iv_portrait, item.getSend().getPortrait(), R.drawable.icon_dface);
                vh.setText(R.id.tv_content, Html.fromHtml(item.getBody()));
            }
        };

        listView.setAdapter(adapter);
        hisuid = getIntent().getExtras().getInt(BUNDLE_KEY_HISUID);
        hisName = getIntent().getExtras().getString(BUNDLE_KEY_HISNAME);
        setTitle(hisName);
        presenter = new ChatMessagePresenter(this);

        presenter.loadChatMessage(hisuid);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showData(List<Message> jsonMessages) {
        adapter.addItem(jsonMessages);
        listView.setSelection(adapter.getCount());
    }

    @Override
    public void showError() {

    }

    @Override
    public void showNetError() {

    }
}
