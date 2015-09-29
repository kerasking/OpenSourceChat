package com.fireant.oschat.view;

import android.app.Activity;
import android.os.Bundle;

import com.fireant.oschat.utils.ActivityUtils;

/**
 * Created by zhangdeyi on 15/7/30.
 */
public class Display {

    public static void showChatActivity(Activity context, int hisuid, String hisName) {
        Bundle bundle = new Bundle();
        bundle.putInt(ChatActivity.BUNDLE_KEY_HISUID, hisuid);
        bundle.putString(ChatActivity.BUNDLE_KEY_HISNAME, hisName);
        ActivityUtils.showActivity(context, ChatActivity.class, bundle);
    }
}
