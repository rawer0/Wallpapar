package com.ovwvwvo.wallpaper.rx;

import android.util.Log;

import com.ovwvwvo.wallpaper.BuildConfig;

import rx.Observer;

/**
 * Copyright ©2017 by rawer
 */
public class EmptyObserver<T> implements Observer<T> {

    public static final String TAG = BuildConfig.APPLICATION_ID;

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError", e);
    }

    @Override
    public void onNext(T t) {

    }
}