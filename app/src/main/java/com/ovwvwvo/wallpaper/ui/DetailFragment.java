package com.ovwvwvo.wallpaper.ui;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.ovwvwvo.wallpaper.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright ©2017 by rawer
 */

public class DetailFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.imageView)
    SubsamplingScaleImageView imageView;

    private static final String URL = "url";
    private String url;

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
        imageView.setOnClickListener(this);
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        url = getArguments().getString(URL);
        assert url != null;
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                    imageView.setImage(ImageSource.bitmap(resource));
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
            Glide.with(this)
                .asBitmap()
                .load(url)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getContext());
                        try {
                            wallpaperManager.setBitmap(resource);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        if (getActivity() instanceof DetailActivity) {
            ((DetailActivity) getActivity()).toggleStatusBar();
        }
    }
}