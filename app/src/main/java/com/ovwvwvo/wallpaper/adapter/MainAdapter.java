package com.ovwvwvo.wallpaper.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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

public class MainAdapter extends AutoLoadMoreAdapter<MainAdapter.MViewHolder> {

    private List<UrlModel> models = new ArrayList<>();
    private Activity activity;

    public MainAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setModels(List<UrlModel> models) {
        clearModels();
        this.models.addAll(models);
        notifyDataChanged();
    }

    public void addModels(List<UrlModel> models) {
        this.models.addAll(models);
        notifyDataChanged();
    }

    public void clearModels() {
        this.models.clear();
        notifyDataChanged();
    }

    public long getLastId() {
        return models.get(models.size() - 1).getId();
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(MViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Glide.with(activity)
            .load(models.get(position).getUrl() + "-half")
            .thumbnail(0.4f)
            .into(holder.imageView);
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
}




