package com.fireant.oschat.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.fireant.oschat.IPresenter.IContactsPresenter;
import com.fireant.oschat.R;
import com.fireant.oschat.adapter.ContactAdapter;
import com.fireant.oschat.bean.JsonUser;
import com.fireant.oschat.iview.ILoadDetailView;
import com.fireant.oschat.presenter.ContactsPresenter;
import com.fireant.oschat.utils.ActivityUtils;
import com.fireant.oschat.widget.LazyFragment;
import com.fireant.oschat.widget.SideBar;

import java.util.List;

import butterknife.Bind;

/**
 * 联系人界面
 *
 * Created by zhangdeyi on 15/7/25.
 */
public class ContactsFragment extends LazyFragment implements
        ILoadDetailView<List<JsonUser>>, AdapterView.OnItemClickListener {
    @Bind(R.id.listView)
    ListView listView;

    @Bind(R.id.tv_select_index)
    TextView tvSelectIndex;
    @Bind(R.id.siderBar)
    SideBar sideBar;
    @Bind(R.id.tip_layout)
    View tip;

    private ContactAdapter adapter;

    private View headerView;
    private TextView footerView;

    private IContactsPresenter contactsPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contacts;
    }

    @Override
    protected void afterView() {
        super.afterView();
        contactsPresenter = new ContactsPresenter(this);
        adapter = new ContactAdapter(getActivity(), R.layout.list_cell_contact);

        // 添加头部View
        headerView = inflateView(R.layout.list_cell_contact_header);
        listView.addHeaderView(headerView);

        // 添加底部View
        footerView = inflateView(R.layout.list_cell_contact_footer);
        listView.addFooterView(footerView);

        listView.setAdapter(adapter);
        sideBar.setTextView(tvSelectIndex);
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {

                if (s.equals("↑")) {
                    listView.setSelection(0);
                    return;
                }
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    // 这里需要加1是因为加了头部header
                    listView.setSelection(position + 1);
                }
            }
        });

        listView.setOnItemClickListener(this);
    }

    @Override
    protected void lazyLoad() {
        contactsPresenter.loadContcts();
    }

    @Override
    public void showLoading() {
        sideBar.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);
        tip.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        sideBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.VISIBLE);
        tip.setVisibility(View.GONE);
    }

    @Override
    public void showData(List<JsonUser> jsonUsers) {
        adapter.addItem(jsonUsers);
        footerView.setText(String.format("%s位联系人", adapter.getCount()));
    }

    @Override
    public void showError() {

    }

    @Override
    public void showNetError() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                break;
            default:
                JsonUser user = adapter.getItem(position - 1);
                Bundle bundle = new Bundle();
                bundle.putInt(UserDetailInfoActivity.BUNDLE_KEY_ID, user.getId());
                ActivityUtils.showActivity(getActivity(), UserDetailInfoActivity.class, bundle);
                break;
        }
    }
}
