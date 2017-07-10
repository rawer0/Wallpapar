package com.monument.wallpaper.presenter;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.monument.wallpaper.model.UrlModel;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

/**
 * Copyright ©2017 by rawer
 */

public class LoadDataPresenterImpl implements LoadDataPresenter {

    private static final String TAG = "DATA_CHANGE";

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


    public void writeDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, 慧慧!");
    }

    public void readDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private List<UrlModel> generateData() {
        return null;
    }
}
