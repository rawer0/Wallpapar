package com.ovwvwvo.wallpaper.presenter;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.ovwvwvo.jkit.log.LogUtil;
import com.ovwvwvo.wallpaper.model.UrlModel;
import com.ovwvwvo.wallpaper.view.MainView;

import java.util.List;

/**
 * Copyright ©2017 by rawer
 */

public class LoadDataPresenterImpl implements LoadDataPresenter {

    private static final String TAG = "LoadDataPresenterImpl";

    private MainView mainView;

    public LoadDataPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void loadData() {
        mainView.showProgress();
        readData(0);
    }

    private void writeData() {
        UrlModel todoFolder = new UrlModel();// 构建对象

        todoFolder.saveInBackground();
    }

    private void readData(long id) {
        AVQuery<UrlModel> avQuery = AVObject.getQuery(UrlModel.class);
        avQuery.whereGreaterThanOrEqualTo("id", id);
        avQuery.limit(10);
        avQuery.findInBackground(new FindCallback<UrlModel>() {
            @Override
            public void done(List<UrlModel> list, AVException e) {
                if (e == null) {
                    mainView.LoadDataSuccess(list);
                    LogUtil.i(TAG, list.toString());
                } else {
                    mainView.showToast(e.getMessage());
                    mainView.hideProgress();
                }
            }
        });
    }
}
