package com.ovwvwvo.wallpaper;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.ovwvwvo.jkit.log.LogUtil;

/**
 * Copyright ©2017 by rawer
 */

public class MainApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LogUtil.setEnable(true);

        if ("firebase".equals(BuildConfig.FLAVOR))
            FirebaseApp.initializeApp(this, null);
    }
}
