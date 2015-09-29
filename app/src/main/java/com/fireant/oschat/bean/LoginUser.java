package com.fireant.oschat.bean;

import android.content.Context;

import com.fireant.oschat.api.PersistentCookieStore;
import com.fireant.oschat.utils.MyDiskLruCacheUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 登录用户
 * Created by zhangdeyi on 15/7/19.
 */
@XStreamAlias("oschina")
public class LoginUser extends BaseBean {

    public static final String LOGIN_CACHE_KEY = "login_user_cache";

    @XStreamAlias("user")
    private User user;

    @XStreamAlias("result")
    private Result result;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static LoginUser getLoginUser(Context context) {
        LoginUser user = MyDiskLruCacheUtils.getObjectCache(context, LOGIN_CACHE_KEY);
        return user;
    }

    public static boolean isLogin(Context context) {
        if (getLoginUser(context) != null) {
            return true;
        } else {
            return false;
        }
    }

    public static int getLoginUid(Context context) {
        LoginUser user = getLoginUser(context);
        if (user != null) {
            return user.getUser().getId();
        } else {
            return 0;
        }
    }

    public static void logoutUser(Context context) {
        PersistentCookieStore cookieStore = new PersistentCookieStore(context);
        cookieStore.removeAll();
        MyDiskLruCacheUtils.deleteObject(context, LOGIN_CACHE_KEY);
    }
}
