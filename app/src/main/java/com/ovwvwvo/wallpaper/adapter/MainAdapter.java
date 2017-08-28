package com.ovwvwvo.wallpaper.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ovwvwvo.jkit.weight.ToastMaster;
import com.ovwvwvo.wallpaper.R;
import com.ovwvwvo.wallpaper.model.UrlModel;
import com.ovwvwvo.wallpaper.ui.DetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright ©2017 by rawer
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MViewHolder> {

    private List<UrlModel> models = new ArrayList<>();
    private Activity activity;

    public MainAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(final MViewHolder holder, int position) {
        Glide.with(activity)
            .load(models.get(position).getUrl() + "-half")
            .thumbnail(0.4f)
            .into(holder.imageView);
    }

    private static void setLayoutParams(Drawable resource, ImageView imageView) {
        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        // 获取容器实际存放图片的宽度
        int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
        // 计算出容器实际存放图片宽度与图片资源宽度的比例
        float scale = (float) vw / (float) resource.getIntrinsicWidth();
        // 依据比例算出容器实际存放图片高度值
        int vh = Math.round(resource.getIntrinsicHeight() * scale);
        // 计算容器的高度
        params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
        imageView.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class MViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView imageView;

        MViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.iv)
        void click() {
            activity.startActivity(new Intent(activity, DetailActivity.class));
        }
    }

    public void setModels(List<UrlModel> models) {
        if (models == null)
            ToastMaster.showToastMsg("data is null");
        else {
            this.models.addAll(models);
            notifyDataSetChanged();
        }
    }

    public void clearModels() {
        this.models.clear();
    }
}




