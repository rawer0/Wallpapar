package com.ovwvwvo.wallpaper.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.ovwvwvo.wallpaper.R;
import com.ovwvwvo.wallpaper.adapter.DetailAdapter;
import com.ovwvwvo.wallpaper.model.UrlModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright ©2017 by rawer
 */

public class DetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.container)
    ViewPager mViewPager;

    private DetailAdapter detailAdapter;
    private boolean showStatusBar = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(android.R.style.Theme_Wallpaper_NoTitleBar_Fullscreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ArrayList<UrlModel> urls = getIntent().getParcelableArrayListExtra("urls");
        detailAdapter = new DetailAdapter(getSupportFragmentManager(), urls);
        mViewPager.setAdapter(detailAdapter);
    }

    public void toggleStatusBar() {
        showStatusBar = !showStatusBar;
        if (showStatusBar) {
            getSupportActionBar().show();
        } else {
            getSupportActionBar().hide();
        }
    }
}
