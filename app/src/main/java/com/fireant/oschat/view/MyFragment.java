package com.fireant.oschat.view;

import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fireant.oschat.R;
import com.fireant.oschat.adapter.SeparatorAdapter;
import com.fireant.oschat.adapter.ViewHolder;
import com.fireant.oschat.bean.ListSimpleBean;
import com.fireant.oschat.bean.LoginUser;
import com.fireant.oschat.utils.ActivityUtils;
import com.fireant.oschat.utils.ToastUtils;
import com.fireant.oschat.widget.LazyFragment;

import butterknife.Bind;

/**
 * Created by zhangdeyi on 15/7/25.
 */
public class MyFragment extends LazyFragment implements AdapterView.OnItemClickListener {
    @Bind(R.id.listView)
    ListView listView;

    @Bind(R.id.tip_layout)
    View tip;

    private SeparatorAdapter<ListSimpleBean> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void afterView() {
        super.afterView();
        adapter = new SeparatorAdapter<ListSimpleBean>(getActivity(), R.layout.list_cell_simple) {

            @Override
            protected int getLayoutId(int position) {
                if (position == 1) {
                    return R.layout.list_cell_my_header;
                } else {
                    return super.getLayoutId(position);
                }
            }

            @Override
            public void convert(ViewHolder vh, ListSimpleBean item, int position) {
                if (position == 1) {
                    LoginUser loginUser = LoginUser.getLoginUser(mContext);
                    vh.setText(R.id.tv_name, loginUser.getUser().getName());
                    vh.setText(R.id.tv_email, loginUser.getUser().getLocation());
                    vh.setImageForNet(R.id.iv_portrait, loginUser.getUser().getPortrait(), R.drawable.icon_dface);
                    vh.getView(R.id.iv_qr_code).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtils.showToast("二维码");
                        }
                    });
                } else {
                    vh.setText(R.id.tv_text, item.getText());
                    vh.setImage(R.id.iv_img, item.getImgRes());
                }
            }
        };

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
    }

    @Override
    protected void lazyLoad() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.setVisibility(View.VISIBLE);
                tip.setVisibility(View.GONE);

                adapter.addSeparatorItem(null);
                adapter.addItem(new ListSimpleBean(R.drawable.icon_dface, "昵称"));

                adapter.addSeparatorItem(null);
                adapter.addItem(new ListSimpleBean(R.drawable.icon_my_album, "相册"));
                adapter.addItem(new ListSimpleBean(R.drawable.icon_my_collect, "收藏"));

                adapter.addSeparatorItem(null);
                adapter.addItem(new ListSimpleBean(R.drawable.icon_my_purse, "钱包"));

                adapter.addSeparatorItem(null);
                adapter.addItem(new ListSimpleBean(R.drawable.icon_my_setting, "设置"));

            }
        }, 300);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 1:
                ActivityUtils.showActivity(getActivity(), MyInfoActivity.class);
                break;
            default:
                break;
        }
    }
}
