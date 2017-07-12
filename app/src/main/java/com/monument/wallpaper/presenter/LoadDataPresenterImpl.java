package com.monument.wallpaper.presenter;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.monument.wallpaper.model.UrlModel;
import com.monument.wallpaper.view.MainView;

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
<<<<<<< HEAD
        BmobQuery<UrlModel> bmobQuery = new BmobQuery<UrlModel>();
        bmobQuery.getObject("6b6c11c537", new QueryListener<UrlModel>() {
            @Override
            public void done(UrlModel object, BmobException e) {
                if (e == null) {

                } else {
                }
            }
        });
=======
        mainView.showProgress();
        readDatabase();
>>>>>>> add loadData
    }

    public void writeDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("pics");
        UrlModel urlModel = new UrlModel();
        urlModel.addUrl("1asdf");
        urlModel.addUrl("2asdf");
        urlModel.addUrl("3asdf");
        myRef.setValue(urlModel);
    }

    public void readDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("pics");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UrlModel model = dataSnapshot.getValue(UrlModel.class);
                mainView.LoadDataSuccess(model);
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
