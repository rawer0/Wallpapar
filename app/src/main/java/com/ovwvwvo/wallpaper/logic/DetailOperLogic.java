package com.ovwvwvo.wallpaper.logic;

import android.Manifest;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;

import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.IOException;
import java.lang.reflect.Method;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Copyright ©2017 by rawer
 */

public class DetailOperLogic {

    public DetailOperLogic() {
    }

    public Observable<Object> setWallpaper(Context context, Bitmap bitmap) {
        return Observable.create(subscriber -> {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
            try {
                wallpaperManager.setBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                subscriber.onError(e);
            } finally {
                subscriber.onCompleted();
            }
        }).observeOn(Schedulers.io());
    }

    public Observable<Object> setLockScreen(Context context, Bitmap bitmap) {
        return Observable.create(subscriber -> {
            try {
                WallpaperManager mWallManager = WallpaperManager.getInstance(context);
                Class clazz = mWallManager.getClass();
                Method setWallPaperMethod = clazz.getMethod("setBitmapToLockWallpaper", Bitmap.class);
                setWallPaperMethod.invoke(mWallManager, bitmap);
            } catch (Exception e) {
                subscriber.onError(e);
                e.printStackTrace();
            } finally {
                subscriber.onCompleted();
            }
        }).observeOn(Schedulers.io());
    }

    public Observable<Boolean> requestPermission(Activity activity) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        return rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }
}