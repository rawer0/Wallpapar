package com.ovwvwvo.wallpaper.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.chrisbanes.photoview.PhotoView;
import com.ovwvwvo.wallpaper.R;
import com.ovwvwvo.wallpaper.presenter.DetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Copyright ©2017 by rawer
 */

public class DetailFragment extends Fragment {
    @BindView(R.id.src)
    PhotoView src_iv;
    @BindView(R.id.bottom_sheet)
    RelativeLayout bottomSheet_layout;
    @BindView(R.id.desc)
    AppCompatTextView desc_tv;

    private BottomSheetBehavior behavior;

    private static final String URL = "url";
    private Bitmap bitmap;

    private DetailPresenter presenter;

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
        src_iv.setScaleType(ImageView.ScaleType.FIT_XY);
        registerForContextMenu(src_iv);

        behavior = BottomSheetBehavior.from(bottomSheet_layout);
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DetailPresenter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String url = getArguments().getString(URL);
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                    src_iv.setImageBitmap(bitmap = resource);
                }
            });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.detail_contentmenu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_set_wallpaper) {//设置壁纸
            presenter.setWallPaper(getContext(), bitmap);
        } else if (item.getItemId() == R.id.meun_set_lockScreen) {//设置锁屏
        } else if (item.getItemId() == R.id.meun_download) {//下载
        } else if (item.getItemId() == R.id.menu_share) {//分享图片
        }
        return super.onContextItemSelected(item);
    }

    @OnLongClick(R.id.src)
    boolean onLongClick() {
        src_iv.showContextMenu();
        return false;
    }

    @OnClick(R.id.move)
    void onMoveClick() {
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {//bottomSheet_layout 展开
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {//bottomSheet_layout 折叠
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }
}