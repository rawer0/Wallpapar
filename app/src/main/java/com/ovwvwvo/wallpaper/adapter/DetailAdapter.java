package com.ovwvwvo.wallpaper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ovwvwvo.wallpaper.model.UrlModel;
import com.ovwvwvo.wallpaper.ui.DetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright ©2017 by rawer
 */

public class DetailAdapter extends FragmentStatePagerAdapter {
    private List<UrlModel> urlModels = new ArrayList<>();

    public DetailAdapter(FragmentManager fm, List<UrlModel> urlModels) {
        super(fm);
        this.urlModels = urlModels;
    }

    @Override
    public Fragment getItem(int position) {
        return DetailFragment.newInstance(urlModels.get(position).getUrl());
    }

    @Override
    public int getCount() {
        return urlModels.size();
    }
}
