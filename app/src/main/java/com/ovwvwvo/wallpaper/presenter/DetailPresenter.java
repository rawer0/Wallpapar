package com.ovwvwvo.wallpaper.presenter;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.ovwvwvo.jkit.weight.ToastMaster;
import com.ovwvwvo.wallpaper.R;

import java.io.IOException;

/**
 * Copyright ©2017 by rawer
 */

public class DetailPresenter {

    public DetailPresenter() {
    }


    public void setWallPaper(Context context, Bitmap bitmap) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        try {
            wallpaperManager.setBitmap(bitmap);
            ToastMaster.showToastMsg(R.string.set_wallpaper_successed);
            gotoHome(context);
        } catch (IOException e) {
            e.printStackTrace();
            ToastMaster.showToastMsg(R.string.set_wallpaper_failed);
        }
    }

    private void gotoHome(Context context) {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.addCategory(Intent.CATEGORY_HOME);
        context.startActivity(home);
    }
}
