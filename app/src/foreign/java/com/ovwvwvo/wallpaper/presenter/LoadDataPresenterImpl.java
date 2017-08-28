package com.ovwvwvo.wallpaper.presenter;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ovwvwvo.wallpaper.model.UrlModel;
import com.ovwvwvo.wallpaper.model.UrlsResponse;
import com.ovwvwvo.wallpaper.view.MainView;

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
        readDatabase();
//        writeDatabase();
    }

    public void writeDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        UrlsResponse urlModels = new UrlsResponse();
        UrlModel urlModel = new UrlModel();
        urlModel.setId(123);
        urlModel.setUrl("www.baidu.com");
        urlModel.setDesc("baidu");
        urlModels.addUrlModels(urlModel);
        myRef.setValue(urlModels);
        Log.i(TAG, "数据写入完成");
    }

    private void readDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UrlsResponse model = dataSnapshot.getValue(UrlsResponse.class);
                mainView.LoadDataSuccess(model.getUrlModels());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                mainView.showToast(error.getMessage());
                mainView.hideProgress();
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
