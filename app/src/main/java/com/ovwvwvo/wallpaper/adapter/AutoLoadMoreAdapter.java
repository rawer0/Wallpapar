package com.ovwvwvo.wallpaper.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Copyright ©2017 by rawer
 *
 * http://sab99r.com/blog/recyclerview-endless-load-more/
 */

public abstract class AutoLoadMoreAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private OnLoadMoreListener loadMoreListener;
    public boolean isLoading = false, isMoreDataAvailable = true;

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (position >= getItemCount() - 1 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true;
            loadMoreListener.onLoadMore();
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }

    public void notifyDataChanged() {
        notifyDataSetChanged();
        isLoading = false;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }
}