package com.fireant.oschat.adapter;

import android.content.Context;

import com.fireant.oschat.R;
import com.fireant.oschat.bean.JsonUser;

/**
 * 联系人通讯录
 * Created by zhangdeyi on 15/7/26.
 */
public class ContactAdapter extends CommonAdapter<JsonUser> {


    public ContactAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder vh, JsonUser item, int position) {
        vh.setText(R.id.tv_name, item.getName());
        vh.setImageForNet(R.id.iv_img, item.getPortrait(), R.drawable.icon_dface);

        // get position and get the first letter
        int section = getSectionForPosition(position);

        if (position == getPositionForSection(section)) {
            vh.setVisible(R.id.tv_pinyin);
            // 这里需要注意的是返回的是char, 记得加上""
            vh.setText(R.id.tv_pinyin, item.getPinyin().toUpperCase().charAt(0) + "");
        } else {
            vh.setGone(R.id.tv_pinyin);
        }
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = mDatas.get(i).getPinyin();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section)
                return i;
        }

        return -1;
    }

    public int getSectionForPosition(int arg0) {
        return mDatas.get(arg0).getPinyin().toUpperCase().charAt(0);
    }
}
