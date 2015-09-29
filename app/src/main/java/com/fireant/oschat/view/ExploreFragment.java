package com.fireant.oschat.view;

import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fireant.oschat.R;
import com.fireant.oschat.adapter.SeparatorAdapter;
import com.fireant.oschat.adapter.ViewHolder;
import com.fireant.oschat.bean.ListSimpleBean;
import com.fireant.oschat.utils.ActivityUtils;
import com.fireant.oschat.widget.LazyFragment;

import butterknife.Bind;

/**
 * Created by zhangdeyi on 15/7/25.
 */
public class ExploreFragment extends LazyFragment implements AdapterView.OnItemClickListener {

    @Bind(R.id.listView)
    ListView listView;

    @Bind(R.id.tip_layout)
    View tip;

    private SeparatorAdapter<ListSimpleBean> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_explore;
    }

    @Override
    protected void afterView() {
        super.afterView();
        adapter = new SeparatorAdapter<ListSimpleBean>(getActivity(), R.layout.list_cell_simple) {
            @Override
            public void convert(ViewHolder vh, ListSimpleBean item, int position) {
                vh.setText(R.id.tv_text, item.getText());
                vh.setImage(R.id.iv_img, item.getImgRes());
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
                adapter.addItem(new ListSimpleBean(R.drawable.icon_explore_friend_circle, "源友圈"));

                adapter.addSeparatorItem(null);
                adapter.addItem(new ListSimpleBean(R.drawable.icon_explore_scan, "扫一扫"));
                adapter.addItem(new ListSimpleBean(R.drawable.icon_explore_shake, "摇一摇"));

                adapter.addSeparatorItem(null);
                adapter.addItem(new ListSimpleBean(R.drawable.icon_explore_neighbourhood, "附近的人"));
                adapter.addItem(new ListSimpleBean(R.drawable.icon_explore_bottle, "漂流瓶"));

                adapter.addSeparatorItem(null);
                adapter.addItem(new ListSimpleBean(R.drawable.icon_explore_shopping, "购物"));
                adapter.addItem(new ListSimpleBean(R.drawable.icon_explore_game, "游戏"));
            }
        }, 300);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 1:
                ActivityUtils.showActivity(getActivity(), OSCCircleActivity.class);
                break;
            case 2:

                break;

            default:
                break;
        }
    }
}
