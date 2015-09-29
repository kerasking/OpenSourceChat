package com.fireant.oschat.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.fireant.oschat.IPresenter.IChatPresenter;
import com.fireant.oschat.api.OSCApi;
import com.fireant.oschat.bean.Message;
import com.fireant.oschat.iview.ILoadDetailView;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * 源聊列表控制器
 * Created by zhangdeyi on 15/7/29.
 */
public class ChatPresenter extends BasePresenter implements IChatPresenter {

    private ILoadDetailView<List<Message>> loadDetailView;

    public ChatPresenter(ILoadDetailView loadDetailView) {
        this.loadDetailView = loadDetailView;
    }

    @Override
    public void loadChatList() {
        loadDetailView.showLoading();
        OSCApi.getMessage(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                loadDetailView.showNetError();
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    new AsyncTask<Integer, Integer, List<Message>>() {

                        @Override
                        protected List<Message> doInBackground(Integer... params) {
                            try {
                                return JSON.parseArray(response.body().string(), Message.class);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            return null;
                        }

                        @Override
                        protected void onPostExecute(List<Message> messageList) {
                            super.onPostExecute(messageList);
                            loadDetailView.hideLoading();
                            loadDetailView.showData(messageList);
                        }
                    }.execute();

                } else {
                    Log.i("Test", "失败");
                }
            }
        });
    }
}
