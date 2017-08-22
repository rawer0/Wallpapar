package com.ovwvwvo.wallpaper.presenter;

import com.ovwvwvo.wallpaper.model.UrlModel;
import com.ovwvwvo.wallpaper.view.MainView;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

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
        BmobQuery<UrlModel> bmobQuery = new BmobQuery<UrlModel>();
        bmobQuery.getObject("6b6c11c537", new QueryListener<UrlModel>() {
            @Override
            public void done(UrlModel object, BmobException e) {
                if (e == null) {

                } else {
                }
            }
        });
        mainView.showProgress();
    }
}
