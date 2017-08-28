package com.ovwvwvo.wallpaper;

import android.app.Application;
import android.content.Context;

import com.ovwvwvo.jkit.AppWrapper;
import com.ovwvwvo.jkit.log.LogUtil;
import com.ovwvwvo.wallpaper.model.UrlModel;

import java.lang.reflect.Method;

/**
 * Copyright ©2017 by rawer
 */

public class MainApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppWrapper.getInstance().setAppContext(this);
        LogUtil.setEnable(true);
        try {
            if ("inland".equals(BuildConfig.FLAVOR)) {
                Class AVObject = Class.forName("com.avos.avoscloud.AVObject");
                Method registerSubclass = AVObject.getMethod("registerSubclass", Class.class);
                registerSubclass.invoke(null, UrlModel.class);

                Class AVOSCloud = Class.forName("com.avos.avoscloud.AVOSCloud");
                Method initialize = AVOSCloud.getMethod("initialize", Context.class, String.class, String.class);
                initialize.invoke(null, this, "2RtTwOCF2Y0U1sfbEkIXQJ1I-gzGzoHsz", "M4yztOu8qzCebty6ctLWleR8");
            } else {
                Class FirebaseApp = Class.forName("com.google.firebase.FirebaseApp");
                Method registerSubclass = FirebaseApp.getMethod("initializeApp", Context.class);
                registerSubclass.invoke(null, this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
