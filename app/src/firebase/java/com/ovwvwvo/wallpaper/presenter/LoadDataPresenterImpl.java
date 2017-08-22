package com.ovwvwvo.wallpaper.presenter;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ovwvwvo.wallpaper.model.UrlModel;
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

    private void readDatabase() {
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
