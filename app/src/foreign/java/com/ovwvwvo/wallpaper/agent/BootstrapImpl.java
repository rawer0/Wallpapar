package com.ovwvwvo.wallpaper.agent;

import android.app.Application;

import com.google.firebase.FirebaseApp;

/**
 * Copyright ©2017 by rawer
 */

public class BootstrapImpl implements IBootstrap {

    @Override
    public void initBaas(Application application) {
        FirebaseApp.initializeApp(application, null);
    }
}
