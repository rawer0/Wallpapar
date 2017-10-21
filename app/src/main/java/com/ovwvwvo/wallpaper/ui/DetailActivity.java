package com.ovwvwvo.wallpaper.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

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

    @BindView(R.id.container)
    ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_detail);
        ButterKnife.bind(this);

        ArrayList<UrlModel> urls = getIntent().getParcelableArrayListExtra("urls");
        int position = getIntent().getIntExtra("position", 0);
        DetailAdapter detailAdapter = new DetailAdapter(getSupportFragmentManager(), urls);
        mViewPager.setAdapter(detailAdapter);
        mViewPager.setCurrentItem(position);
    }
}
