package com.ovwvwvo.wallpaper;

import android.app.Application;
import android.content.Context;

import com.ovwvwvo.jkit.AppWrapper;
import com.ovwvwvo.jkit.log.LogUtil;

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

        if ("bmob".equals(BuildConfig.FLAVOR)) {
            try {
                Class clazz = Class.forName("cn.bmob.v3.Bmob");
                Method method = clazz.getMethod("initialize", Context.class, String.class);
                method.invoke(null, this, "0e9e903b300da967bc0032cec8359895");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
