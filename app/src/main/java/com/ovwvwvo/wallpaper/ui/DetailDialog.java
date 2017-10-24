package com.ovwvwvo.wallpaper.ui;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.ovwvwvo.wallpaper.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright ©2017 by rawer
 */

public class DetailDialog extends BottomSheetDialogFragment {

    private onItemClickListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_detail_operation, container, true);
        ButterKnife.bind(this, view);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //fixed dialog 弹出之后 StatusBar 变黑
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        if (dialog.getWindow() != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        return dialog;
    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    @OnClick(R.id.set_wallpaper)
    void setWallpaper() {
        if (listener != null)
            listener.onSetWallpaperClick();
        dismiss();
    }

    @OnClick(R.id.download)
    void download() {
        if (listener != null)
            listener.onDownLoadClick();
        dismiss();
    }

    @OnClick(R.id.share)
    void share() {
        if (listener != null)
            listener.onShareClick();
        dismiss();
    }

    interface onItemClickListener {
        void onSetWallpaperClick();

        void onDownLoadClick();

        void onShareClick();
    }
}
