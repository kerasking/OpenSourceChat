package com.fireant.oschat.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by zhangdeyi on 15/7/23.
 */
public class ActivityUtils {

    public static void skipActivity(Activity aty, Class<?> cls) {
        showActivity(aty, cls);
        aty.finish();
    }

    public static void skipActivity(Activity aty, Intent it) {
        showActivity(aty, it);
        aty.finish();
    }

    public static void skipActivity(Activity aty, Class<?> cls, Bundle extras) {
        showActivity(aty, cls, extras);
        aty.finish();
    }

    public static void showActivity(Activity aty, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }

    public static void showActivity(Activity aty, Intent it) {
        aty.startActivity(it);
    }

    public static void showActivity(Activity aty, Class<?> cls, Bundle extras) {
        Intent intent = new Intent();
        intent.putExtras(extras);
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }

    public static void showActivityForResult(Activity aty, Class<?> cls, int requestCode) {
        showActivityForResult(aty, cls, null, requestCode);
    }

    public static void showActivityForResult(Activity aty, Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setClass(aty, cls);
        aty.startActivityForResult(intent, requestCode);
    }
}
