package com.monument.wallpaper;

import android.app.Application;

import com.ovwvwvo.jkit.log.LogUtil;

/**
 * Copyright ©2017 by rawer
 */

public class MainApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LogUtil.setEnable(true);
    }
}
