package com.ovwvwvo.wallpaper.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.ovwvwvo.wallpaper.R;
import com.ovwvwvo.wallpaper.adapter.DetailAdapter;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        detailAdapter = new DetailAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(detailAdapter);
    }
}
