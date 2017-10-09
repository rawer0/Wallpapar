package com.ovwvwvo.wallpaper;

import android.app.Application;

import com.ovwvwvo.jkit.AppWrapper;
import com.ovwvwvo.jkit.log.LogUtil;
import com.ovwvwvo.wallpaper.agent.BootstrapImpl;

/**
 * Copyright ©2017 by rawer
 */

public class MainApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppWrapper.getInstance().setAppContext(this);
        LogUtil.setEnable(true);
        new BootstrapImpl().initBaas(this);
    }
}
