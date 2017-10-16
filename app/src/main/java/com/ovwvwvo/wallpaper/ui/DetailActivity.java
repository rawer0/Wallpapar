package com.ovwvwvo.wallpaper.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
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

    private boolean showStatusBar = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ArrayList<UrlModel> urls = getIntent().getParcelableArrayListExtra("urls");
        int position = getIntent().getIntExtra("position", 0);
        DetailAdapter detailAdapter = new DetailAdapter(getSupportFragmentManager(), urls);
        mViewPager.setAdapter(detailAdapter);
        addListener();
        mViewPager.setCurrentItem(position);


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//            ViewPager.LayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
//                //将侧边栏顶部延伸至status bar
//                mDrawerLayout.setFitsSystemWindows(true);
//                //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
//                mDrawerLayout.setClipToPadding(false);
//            }
//        }
    }

    private void addListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (!showStatusBar)
                    getSupportActionBar().show();
            }
        });
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
