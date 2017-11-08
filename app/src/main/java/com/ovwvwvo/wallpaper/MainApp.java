package com.ovwvwvo.wallpaper;

import android.app.Application;

import com.ovwvwvo.common.AppWrapper;
import com.ovwvwvo.wallpaper.agent.BootstrapImpl;

/**
 * Copyright ©2017 by rawer
 */

public class MainApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppWrapper.getInstance().setAppContext(this);
        new BootstrapImpl().initBaas(this);
    }
}
