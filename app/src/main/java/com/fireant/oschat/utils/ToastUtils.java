package com.fireant.oschat.utils;

import android.widget.Toast;

import com.fireant.oschat.MyApp;

/**
 * Created by zhangdeyi on 15/7/25.
 */
public class ToastUtils {

    public static void showToast(String msg) {
        Toast.makeText(MyApp.getInstance(), msg, Toast.LENGTH_LONG).show();
    }
}
