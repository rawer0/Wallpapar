package com.ovwvwvo.wallpaper.rx;

import android.util.Log;

import com.ovwvwvo.wallpaper.BuildConfig;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Copyright ©2017 by rawer
 */
public class EmptyObserver<T> implements Observer<T> {

    public static final String TAG = BuildConfig.APPLICATION_ID;

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError", e);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }
}