package com.monument.wallpaper.presenter;

import com.monument.wallpaper.model.UrlModel;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

/**
 * Copyright ©2017 by rawer
 */

public class LoadDataPresenterImpl implements LoadDataPresenter {

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
    }


    private List<UrlModel> generateData() {
        return null;
    }
}
