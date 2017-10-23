package com.ovwvwvo.wallpaper.ui;

import android.content.Context;
import android.content.Intent;
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

    public final static String MODELS = "models";
    public final static String POSITION = "position";

    public static Intent createIntent(Context context, ArrayList<UrlModel> models, int position) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putParcelableArrayListExtra(MODELS, models);
        intent.putExtra(POSITION, position);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_detail);
        ButterKnife.bind(this);

        ArrayList<UrlModel> models = getIntent().getParcelableArrayListExtra(MODELS);
        int position = getIntent().getIntExtra(POSITION, 0);
        DetailAdapter detailAdapter = new DetailAdapter(getSupportFragmentManager(), models);
        mViewPager.setAdapter(detailAdapter);
        mViewPager.setCurrentItem(position);
    }
}
