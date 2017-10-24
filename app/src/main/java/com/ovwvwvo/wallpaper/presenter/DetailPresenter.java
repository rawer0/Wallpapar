package com.ovwvwvo.wallpaper.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.ovwvwvo.jkit.rx.EmptyObserver;
import com.ovwvwvo.jkit.weight.ToastMaster;
import com.ovwvwvo.wallpaper.R;
import com.ovwvwvo.wallpaper.logic.DetailOperLogic;
import com.ovwvwvo.wallpaper.view.DetailView;

import java.io.File;
import java.io.FileNotFoundException;
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
            .doOnTerminate(() -> detailView.hideProgress())
            .doOnError(throwable -> ToastMaster.showToastMsg(R.string.set_wallpaper_failed))
            .doOnCompleted(() -> ToastMaster.showToastMsg(R.string.set_wallpaper_successed))
            .subscribe(new EmptyObserver<>());
    }

    private void gotoHome(Context context) {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.addCategory(Intent.CATEGORY_HOME);
        context.startActivity(home);
    }

    public void download(Activity activity, Bitmap bitmap) {
        logic.requestPermission(activity)
            .doOnSubscribe(() -> detailView.showProgress())
            .doOnTerminate(() -> detailView.hideProgress())
            .doOnNext(granted -> {
                if (granted) {
                    String path = downloadPic(bitmap, Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES).getPath() + File.separator + System.currentTimeMillis() + ".png");
                    if (!TextUtils.isEmpty(path)) {
                        refreshPic(activity, path);
                    }
                    ToastMaster.showToastMsg(R.string.download_successed);
                } else {
                    ToastMaster.showToastMsg(R.string.permissions);
                }
            })
            .doOnError(throwable -> ToastMaster.showToastMsg(R.string.download_failed))
            .subscribe(new EmptyObserver<>());
    }

    private String downloadPic(Bitmap bitmap, String path) {
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
            return null;
        }
        return path;
    }

    private void refreshPic(Context context, String path) {
        try {
            // 把文件插入到系统图库
            MediaStore.Images.Media.insertImage(context.getContentResolver(), path, "", null);
            // 通知图库更新
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(path)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void share(Context context, Bitmap bitmap) {
        File file = new File(context.getExternalCacheDir(), "share.png");
        file.deleteOnExit();
        downloadPic(bitmap, file.getPath());
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/png");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.menu_item_share)));
    }
}
