package com.fireant.oschat.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.fireant.oschat.IPresenter.IChatMessagePresenter;
import com.fireant.oschat.api.OSCApi;
import com.fireant.oschat.bean.Message;
import com.fireant.oschat.iview.ILoadDetailView;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * Created by zhangdeyi on 15/7/30.
 */
public class ChatMessagePresenter extends BasePresenter implements IChatMessagePresenter {

    private ILoadDetailView<List<Message>> loadDetailView;

    public ChatMessagePresenter(ILoadDetailView<List<Message>> loadDetailView) {
        this.loadDetailView = loadDetailView;
    }

    @Override
    public void loadChatMessage(int hisuid) {
        OSCApi.getMessageDetail(hisuid, 0, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

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
