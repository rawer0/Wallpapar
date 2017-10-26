package com.ovwvwvo.wallpaper.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.ovwvwvo.wallpaper.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright ©2017 by rawer
 */

public class AboutActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }
}
