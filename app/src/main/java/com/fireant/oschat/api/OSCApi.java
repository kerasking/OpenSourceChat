package com.fireant.oschat.api;

import com.fireant.oschat.MyApp;
import com.fireant.oschat.bean.LoginUser;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;

import static com.fireant.oschat.api.OkHttpUtils.get;
import static com.fireant.oschat.api.OkHttpUtils.post;

/**
 * 登录
 * Created by zhangdeyi on 15/7/19.
 */
public class OSCApi {

    public static final String osc_root = "http://www.oschina.net/action/";

    public static void login(String username, String pwd, Callback callback) {
        FormEncodingBuilder form = new FormEncodingBuilder();
        form.add("username", username);
        form.add("pwd", pwd);
        post(form, osc_root + "api/login_validate", callback);
    }

    public static void getContacts(Callback callback) {
        int uid = LoginUser.getLoginUid(MyApp.getInstance());
        get(null, osc_root + "zbApi/contacts_list?uid=" + uid, callback);
    }

    public static void getMessage(Callback callback) {
        int uid = LoginUser.getLoginUid(MyApp.getInstance());
        get(null, osc_root + "zbApi/message_list?uid=" + uid, callback);
    }

    public static void getUserInfo(int hisuid, Callback callback) {
        get(null, osc_root + "api/user_information?hisuid=" + hisuid, callback);
    }

    public static void getMessageDetail(int hisuid, int pageIndex, Callback callback) {
        int uid = LoginUser.getLoginUid(MyApp.getInstance());
        get(null, osc_root + "zbApi/message_detail_list?uid=" + uid + "&hisuid=" + hisuid, callback);
    }

}
