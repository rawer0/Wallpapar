package com.ovwvwvo.wallpaper.ui;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.chrisbanes.photoview.PhotoView;
import com.ovwvwvo.jkit.weight.ToastMaster;
import com.ovwvwvo.wallpaper.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ovwvwvo.wallpaper.R.id.imageView;

/**
 * Copyright ©2017 by rawer
 */

public class DetailFragment extends Fragment {
    @BindView(imageView)
    PhotoView src_iv;
    @BindView(R.id.bottom_sheet)
    RelativeLayout bottomSheet_layout;
    @BindView(R.id.desc)
    AppCompatTextView desc_tv;

    private BottomSheetBehavior behavior;

    private static final String URL = "url";
    private Bitmap bitmap;

    public static DetailFragment newInstance(String url) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, rootView);
        src_iv.setScaleType(ImageView.ScaleType.FIT_XY);

        behavior = BottomSheetBehavior.from(bottomSheet_layout);
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String url = getArguments().getString(URL);
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                    src_iv.setImageBitmap(bitmap = resource);
                }
            });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detail, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_set_wallpaper) {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(getContext());
            try {
                wallpaperManager.setBitmap(bitmap);
                ToastMaster.showToastMsg(R.string.set_wallpaper_successed);
                gotoHome();
            } catch (IOException e) {
                e.printStackTrace();
                ToastMaster.showToastMsg(R.string.set_wallpaper_failed);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.move)
    void onMoveClick() {
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {//bottomSheet_layout 展开
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {//bottomSheet_layout 折叠
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    private void gotoHome() {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);
    }
}