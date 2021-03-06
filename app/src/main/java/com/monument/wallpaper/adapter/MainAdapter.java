package com.monument.wallpaper.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.monument.wallpaper.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright ©2017 by rawer
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MViewHolder> {

    private List<String> models = new ArrayList<>();
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
    public void onBindViewHolder(MViewHolder holder, int position) {
        Glide.with(activity).load(models.get(position)).into(holder.imageView);
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
    }

    public void setModels(List<String> models) {
        this.models = models;
        notifyDataSetChanged();
    }

    public void clearModels() {
        this.models.clear();
    }
}




