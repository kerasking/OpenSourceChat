package com.fireant.oschat.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog.Builder;

/**
 * 对话框辅助类
 * Created by zhangdeyi on 15/7/19.
 */
public class DialogHelp {

    public static Builder getDialog(Context context) {
        return new Builder(context);
    }

    public static ProgressDialog getWaitDialog(Context context, String message) {
        ProgressDialog wait = new ProgressDialog(context);
        wait.setMessage(message);
        return wait;
    }

    public static Builder showMessageDialog(Context context, String msg) {
        return showMessageDialog(context, msg, "确定", null);
    }

    public static Builder showMessageDialog(Context context, String msg,
                                            DialogInterface.OnClickListener okClick) {
        return showMessageDialog(context, msg, "确定", okClick);
    }

    public static Builder showMessageDialog(Context context, String msg, String confirmString,
                                         DialogInterface.OnClickListener okClick) {
        Builder dialog = getDialog(context);
        dialog.setMessage(msg);
        dialog.setNegativeButton(confirmString, okClick);
        dialog.show();
        return dialog;
    }
}
