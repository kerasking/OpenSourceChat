package com.fireant.oschat.api;

import com.squareup.okhttp.Callback;

import java.text.MessageFormat;


/**
 * 豆瓣相关api
 * Created by zhangdeyi on 15/9/17.
 */
public class DouBanApi {

    public static void searchDonBanImage(String q, int start, Callback callback) {
        String url = MessageFormat.format("http://www.douban.com/search/j/?q={0}start={1}", q, start);
        OkHttpUtils.get(null, url, callback);
    }
}
