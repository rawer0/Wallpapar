package com.ovwvwvo.wallpaper.agent;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.ovwvwvo.wallpaper.BuildConfig;
import com.ovwvwvo.wallpaper.model.UrlModel;

/**
 * Copyright ©2017 by rawer
 */

public class BootstrapImpl implements IBootstrap {

    @Override
    public void initBaas(Application application) {
        AVOSCloud.initialize(application, "2RtTwOCF2Y0U1sfbEkIXQJ1I-gzGzoHsz", "M4yztOu8qzCebty6ctLWleR8");
        AVObject.registerSubclass(UrlModel.class);
        AVOSCloud.setDebugLogEnabled(BuildConfig.DEBUG);
    }
}
