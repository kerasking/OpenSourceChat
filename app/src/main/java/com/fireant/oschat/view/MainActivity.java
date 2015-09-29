package com.fireant.oschat.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fireant.oschat.R;
import com.fireant.oschat.view.base.BaseDonNeedIncludeToolBarActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 主界面
 * Created by zhangdeyi on 15/7/19.
 */
public class MainActivity extends BaseDonNeedIncludeToolBarActivity {

    @Bind(R.id.content)
    ViewPager content;

    private PagerAdapter pagerAdapter;

    private ChatFragment chatFragment;
    private ContactsFragment contactsFragment;
    private ExploreFragment exploreFragment;
    private MyFragment myFragment;

    @Bind({R.id.tv_chat, R.id.tv_contact, R.id.tv_explore, R.id.tv_my})
    TextView[] textViews;
    @Bind({R.id.iv_chat, R.id.iv_contact, R.id.iv_explore, R.id.iv_my})
    ImageView[] imageViews;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterView() {
        super.afterView();
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        chatFragment = new ChatFragment();
        contactsFragment = new ContactsFragment();
        exploreFragment = new ExploreFragment();
        myFragment = new MyFragment();

        pagerAdapter.add(chatFragment);
        pagerAdapter.add(contactsFragment);
        pagerAdapter.add(exploreFragment);
        pagerAdapter.add(myFragment);

        chatFragment.setUserVisibleHint(true);

        content.setAdapter(pagerAdapter);

        content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        content.setOffscreenPageLimit(3);
        textViews[0].setSelected(true);
        imageViews[0].setSelected(true);
    }

    private int currentIndex = 0;

    private void setTab(int index) {
        textViews[index].setSelected(true);
        imageViews[index].setSelected(true);

        textViews[currentIndex].setSelected(false);
        imageViews[currentIndex].setSelected(false);
        currentIndex = index;
    }

    @Override
    @OnClick({R.id.ll_chat, R.id.ll_contact, R.id.ll_explore, R.id.ll_my})
    public void onClick(View v) {
        super.onClick(v);
        int selectIndex = 0;
        switch (v.getId()) {
            case R.id.ll_chat:
                selectIndex = 0;
                break;
            case R.id.ll_contact:
                selectIndex = 1;
                break;
            case R.id.ll_explore:
                selectIndex = 2;
                break;
            case R.id.ll_my:
                selectIndex = 3;
                break;
            default:

                break;
        }

        content.setCurrentItem(selectIndex, false);
    }

    class PagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> fragments;

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }


        @Override
        public int getCount() {
            return fragments.size();
        }

        public void add(Fragment fragment) {
            if (fragments == null) {
                fragments = new ArrayList<>();
            }
            fragments.add(fragment);
            notifyDataSetChanged();
        }
    }

    @Override
    protected boolean havaBackButton() {
        return false;
    }
}
