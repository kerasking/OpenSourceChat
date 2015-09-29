package com.fireant.oschat.api;

import android.content.Context;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.fireant.oschat.MyApp;
import com.fireant.oschat.utils.SystemUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 网络请求工具类
 * Created by zhangdeyi on 15/7/19.
 */
public class OkHttpUtils {

    private static final boolean debug = true;

    private static Context context = MyApp.getInstance();
    private static final OkHttpClient okHttpClient = new OkHttpClient();

    static {
        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
        okHttpClient.setCookieHandler(new CookieManager(new PersistentCookieStore(context), CookiePolicy.ACCEPT_ALL));
        if (debug) {
            okHttpClient.networkInterceptors().add(new StethoInterceptor());
        }
    }

    public static String getUserAgent() {
        StringBuilder ua = new StringBuilder("OSChina.NET");
        ua.append('/' + SystemUtils.getAppVersionName(MyApp.getInstance()) + '_'
                +  SystemUtils.getAppVersionCode(MyApp.getInstance()));// app版本信息
        ua.append("/Android");// 手机系统平台
        ua.append("/" + android.os.Build.VERSION.RELEASE);// 手机系统版本
        ua.append("/" + android.os.Build.MODEL); // 手机型号
        //ua.append("/" + context.getAppId());// 客户端唯一标识
        return ua.toString();
    }

    public static Builder getBuilder(String url) {
        return new Builder().url(url)
                .header("User-Agent", getUserAgent())
                .header("Host", "www.oschina.net")
                .header("domain", "www.oschina.net")
                .header("Connection", "Keep-Alive");
    }

    public static Response execute(Request request) throws IOException {
        return okHttpClient.newCall(request).execute();
    }

    public static void enqueue(Request request, Callback callback) throws IOException {
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static void enqueue(Request request) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

            }
        });
    }

    /***
     * 提交一个post请求
     * @param formEncodingBuilder
     * @param url
     * @param callback
     */
    public static void post(FormEncodingBuilder formEncodingBuilder, String url, Callback callback) {
        RequestBody requestBody = formEncodingBuilder.build();
        Request request = getBuilder(url)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static void get(Map<String, String> parims, String url, Callback callback) {
        Request request = getBuilder(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
