package com.fireant.oschat.presenter;

import android.os.AsyncTask;

import com.alibaba.fastjson.JSON;
import com.fireant.oschat.IPresenter.IContactsPresenter;
import com.fireant.oschat.api.OSCApi;
import com.fireant.oschat.bean.JsonUser;
import com.fireant.oschat.iview.ILoadDetailView;
import com.fireant.oschat.utils.PinYinKitUtil;
import com.fireant.oschat.utils.PinyinComparator;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * 联系人列表控制类
 * Created by zhangdeyi on 15/7/29.
 */
public class ContactsPresenter extends BasePresenter implements IContactsPresenter {

    private ILoadDetailView<List<JsonUser>> loadDetailView;

    public ContactsPresenter(ILoadDetailView loadDetailView) {
        this.loadDetailView = loadDetailView;
    }

    @Override
    public void loadContcts() {
        loadDetailView.showLoading();
        OSCApi.getContacts(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                loadDetailView.showNetError();
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    new AsyncTask<Integer, Integer, List<JsonUser>>() {
                        @Override
                        protected List<JsonUser> doInBackground(Integer... params) {
                            try {
                                List<JsonUser> jsonUserList = JSON.parseArray(response.body().string(), JsonUser.class);
                                // sort by a-z
                                Collections.sort(setPinyin(jsonUserList), new PinyinComparator());
                                return jsonUserList;
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                                badHanyuPinyinOutputFormatCombination.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(List<JsonUser> jsonUsers) {
                            super.onPostExecute(jsonUsers);
                            loadDetailView.hideLoading();
                            loadDetailView.showData(jsonUsers);
                        }
                    }.execute();

                } else {
                    loadDetailView.showError();
                }
            }
        });
    }

    private List<JsonUser> setPinyin(List<JsonUser> jsonUserList) throws BadHanyuPinyinOutputFormatCombination {
        for (JsonUser user : jsonUserList) {
            user.setPinyin(PinYinKitUtil.getPingYin(user.getName()).toUpperCase());
        }

        return jsonUserList;
    }
}
