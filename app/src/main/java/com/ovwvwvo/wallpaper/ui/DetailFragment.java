package com.ovwvwvo.wallpaper.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.chrisbanes.photoview.PhotoView;
import com.ovwvwvo.jkit.weight.ToastMaster;
import com.ovwvwvo.wallpaper.R;
import com.ovwvwvo.wallpaper.model.UrlModel;
import com.ovwvwvo.wallpaper.presenter.DetailPresenter;
import com.ovwvwvo.wallpaper.view.DetailView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Copyright ©2017 by rawer
 */

public class DetailFragment extends Fragment implements DetailDialog.onItemClickListener, DetailView {
    @BindView(R.id.src)
    PhotoView src_iv;
    @BindView(R.id.bottom_sheet)
    RelativeLayout bottomSheet_layout;
    @BindView(R.id.desc)
    AppCompatTextView desc_tv;

    private BottomSheetBehavior behavior;
    private DetailDialog detailDialog;

    private static final String MODEL = "model";

    private UrlModel model;
    private Bitmap bitmap;

    private DetailPresenter presenter;

    public static DetailFragment newInstance(UrlModel model) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(MODEL, model);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = getArguments().getParcelable(MODEL);
        presenter = new DetailPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, rootView);
        src_iv.setScaleType(ImageView.ScaleType.FIT_XY);

        if (TextUtils.isEmpty(model.getDesc())) {
            bottomSheet_layout.setVisibility(View.GONE);
        } else {
            behavior = BottomSheetBehavior.from(bottomSheet_layout);
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Glide.with(this)
            .asBitmap()
            .load(model.getUrl())
            .into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                    src_iv.setImageBitmap(bitmap = resource);
                }
            });
    }

    @OnLongClick(R.id.src)
    boolean onLongClick() {
        if (detailDialog == null) {
            detailDialog = new DetailDialog();
            detailDialog.setListener(this);
        }
        detailDialog.show(getFragmentManager(), "dialog");
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

    @Override
    public void onSetWallpaperClick() {
        presenter.setWallPaper(getContext(), bitmap);
    }

    @Override
    public void onSetLockScreenClick() {
        presenter.setLockScreen(getContext(), bitmap);
    }

    @Override
    public void onDownLoadClick() {
        presenter.download(getActivity(), bitmap);
    }


    @Override
    public void onShareClick() {
        ToastMaster.showToastMsg(R.string.menu_item_share);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}