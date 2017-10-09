package com.ovwvwvo.wallpaper.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ArrayList<UrlModel> urls = getIntent().getParcelableArrayListExtra("urls");
        int position = getIntent().getIntExtra("position", 0);
        detailAdapter = new DetailAdapter(getSupportFragmentManager(), urls);
        mViewPager.setAdapter(detailAdapter);
        mViewPager.setCurrentItem(position);
    }

    public void toggleStatusBar() {
        showStatusBar = !showStatusBar;
        if (showStatusBar) {
            getSupportActionBar().show();
        } else {
            getSupportActionBar().hide();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    }
}
