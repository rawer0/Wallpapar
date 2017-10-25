package com.ovwvwvo.wallpaper.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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

public class MainAdapter extends AutoLoadMoreAdapter<RecyclerView.ViewHolder> {

    private ArrayList<UrlModel> models = new ArrayList<>();
    private Activity activity;

    private LayoutInflater inflater;
    private int spancount = 2;

    public enum ItemType {
        VERTICAL, HORIZONTAL, LOAD_MORE
    }

    public MainAdapter(Activity activity) {
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
    }

    public void setSpancount(int spancount) {
        this.spancount = spancount;
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
        if (models.size() > 0)
            return models.get(models.size() - 1).getId();
        else
            return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ItemType.VERTICAL.ordinal()) {
            return new MViewHolder(inflater.inflate(R.layout.item_detail, parent, false));
        } else if (viewType == ItemType.HORIZONTAL.ordinal()) {
            return new MViewHolder(inflater.inflate(R.layout.item_detail_reversal, parent, false));
        } else {
            return new LoadMoreViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_loadmore, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof MViewHolder)
            Glide.with(activity)
                .load(models.get(position).getUrl() + (spancount == 2 ? "-half" : "-rectangle"))
                .thumbnail(0.4f)
                .into(((MViewHolder) holder).imageView);
        else {
            ((LoadMoreViewHolder) holder).loadMoreLayout.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return models.size() + (isLoading ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= models.size())
            return ItemType.LOAD_MORE.ordinal();
        else if (spancount == 2) {
            return ItemType.VERTICAL.ordinal();
        } else return ItemType.HORIZONTAL.ordinal();
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
            activity.startActivity(DetailActivity.createIntent(activity, models, getAdapterPosition()));
        }
    }

    class LoadMoreViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.load_more)
        RelativeLayout loadMoreLayout;

        LoadMoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}




