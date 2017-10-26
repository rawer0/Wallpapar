package com.ovwvwvo.wallpaper.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.ovwvwvo.wallpaper.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright ©2016 by rawer
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.iv)
    ImageView imageView;
    @BindView(R.id.copyright)
    TextView copyright_tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        copyright_tv.setText(String.format(getString(R.string.copyright), getString(R.string.app_name)));

        Animation anim = AnimationUtils.loadAnimation(this, android.support.design.R.anim.abc_fade_in);
        anim.setDuration(1024);
        imageView.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                next();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    private void next() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }
}
