package com.fireant.oschat.view;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fireant.oschat.IPresenter.IChatPresenter;
import com.fireant.oschat.R;
import com.fireant.oschat.adapter.CommonAdapter;
import com.fireant.oschat.adapter.ViewHolder;
import com.fireant.oschat.bean.Message;
import com.fireant.oschat.iview.ILoadDetailView;
import com.fireant.oschat.presenter.ChatPresenter;
import com.fireant.oschat.view.base.BaseFragment;

import java.util.List;

import butterknife.Bind;

/**
 * 源聊界面
 * Created by zhangdeyi on 15/7/25.
 */
public class ChatFragment extends BaseFragment implements
        ILoadDetailView<List<Message>>, AdapterView.OnItemClickListener {

    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.tip_layout)
    View tip;

    private CommonAdapter<Message> adapter;

    private IChatPresenter chatPresenter;

    private TipView tipView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat;
    }

    @Override
    protected void afterView() {
        super.afterView();
        if (getActivity() == null) return;
        adapter = new CommonAdapter<Message>(getActivity(), R.layout.list_cell_message) {
            @Override
            public void convert(ViewHolder vh, Message item, int position) {
                vh.setText(R.id.tv_name, item.getFriend().getName());
                vh.setText(R.id.tv_time, item.getCreate_at());
                vh.setImageForNet(R.id.iv_img, item.getFriend().getPortrait(), R.drawable.icon_dface);
                vh.setText(R.id.tv_content, item.getBody());
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        tipView = new TipView(tip);
        chatPresenter = new ChatPresenter(this);
        chatPresenter.loadChatList();
        tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatPresenter.loadChatList();
            }
        });
    }

    @Override
    public void showLoading() {
        listView.setVisibility(View.GONE);
        tipView.setLoading();
    }

    @Override
    public void hideLoading() {
        listView.setVisibility(View.VISIBLE);
        tipView.setHideLoading();
    }

    @Override
    public void showData(List<Message> messages) {
        adapter.addItem(messages);
    }

    @Override
    public void showError() {
        tipView.setTipInfo(R.drawable.icon_dface, "点击重新加载联系人");
    }

    @Override
    public void showNetError() {
        tipView.setNetWorkError();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case -1:
                break;
            default:
                Message message = adapter.getItem(position);
                Display.showChatActivity(getActivity(), message.getFriend().getId(), message.getFriend().getName());
                break;
        }
    }
}
