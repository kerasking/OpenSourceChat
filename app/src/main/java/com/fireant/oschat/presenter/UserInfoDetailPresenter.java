package com.fireant.oschat.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.fireant.oschat.IPresenter.IUserInfoDetailPresenter;
import com.fireant.oschat.api.OSCApi;
import com.fireant.oschat.bean.ActiveList;
import com.fireant.oschat.iview.ILoadDetailView;
import com.fireant.oschat.utils.XML;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by zhangdeyi on 15/7/30.
 */
public class UserInfoDetailPresenter extends BasePresenter implements IUserInfoDetailPresenter {

    private ILoadDetailView<ActiveList> loadDetailView;

    public UserInfoDetailPresenter(ILoadDetailView<ActiveList> loadDetailView) {
        this.loadDetailView = loadDetailView;
    }

    @Override
    public void loadDetail(int uid) {
        loadDetailView.showLoading();
        OSCApi.getUserInfo(uid, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(final Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    new AsyncTask<Integer, Integer, ActiveList>() {

                        @Override
                        protected ActiveList doInBackground(Integer... params) {
                            try {
                                return XML.parseBean(ActiveList.class, response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            return null;
                        }

                        @Override
                        protected void onPostExecute(ActiveList messageList) {
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
