package com.ovwvwvo.wallpaper.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;

import com.ovwvwvo.jkit.rx.EmptyObserver;
import com.ovwvwvo.jkit.weight.ToastMaster;
import com.ovwvwvo.wallpaper.R;
import com.ovwvwvo.wallpaper.logic.DetailOperLogic;
import com.ovwvwvo.wallpaper.view.DetailView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Copyright ©2017 by rawer
 */

public class DetailPresenter {

    private DetailView detailView;
    private DetailOperLogic logic;

    public DetailPresenter(DetailView detailView) {
        this.detailView = detailView;
        this.logic = new DetailOperLogic();
    }

    public void setWallPaper(Context context, Bitmap bitmap) {
        new DetailOperLogic().setWallpaper(context, bitmap)
            .doOnSubscribe(() -> detailView.showProgress())
            .doOnTerminate(() -> detailView.showProgress())
            .doOnError(throwable -> ToastMaster.showToastMsg(R.string.set_wallpaper_failed))
            .doOnCompleted(() -> ToastMaster.showToastMsg(R.string.set_wallpaper_successed))
            .subscribe(new EmptyObserver<>());
    }

    private void gotoHome(Context context) {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.addCategory(Intent.CATEGORY_HOME);
        context.startActivity(home);
    }

    public void setLockScreen(Context context, Bitmap bitmap) {
        logic.setLockScreen(context, bitmap)
            .doOnSubscribe(() -> detailView.showProgress())
            .doOnTerminate(() -> detailView.showProgress())
            .doOnError(throwable -> ToastMaster.showToastMsg(R.string.set_lockscreen_failed))
            .doOnCompleted(() -> ToastMaster.showToastMsg(R.string.set_lockscreen_successed))
            .subscribe(new EmptyObserver<>());
    }

    public void download(Activity activity, Bitmap bitmap) {
        logic.requestPermission(activity)
            .doOnNext(granted -> {
                if (granted) {
                    downloadPic(bitmap);
                    ToastMaster.showToastMsg(R.string.download_successed);
                } else {
                    ToastMaster.showToastMsg(R.string.permissions);
                }
            })
            .doOnError(throwable -> ToastMaster.showToastMsg(R.string.download_failed))
            .subscribe(new EmptyObserver<>());
    }

    private void downloadPic(Bitmap bitmap) {
        String path = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES).getPath() + File.separator
            + System.currentTimeMillis() + ".png";
        File file = new File(path);
        file.deleteOnExit();
        try {
            FileOutputStream out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
