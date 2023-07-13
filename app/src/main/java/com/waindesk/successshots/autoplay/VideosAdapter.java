package com.waindesk.successshots.autoplay;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class VideosAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    public VideosAdapter() {
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = new View(parent.getContext());
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
    }

    @Override
    public void onViewDetachedFromWindow(final CustomViewHolder holder) {
        if (holder != null) {
            holder.getAah_vi().getCustomVideoView().clearAll();
            holder.getAah_vi().getCustomVideoView().invalidate();
        }
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewRecycled(CustomViewHolder holder) {
        if (holder != null) {
            holder.getAah_vi().getCustomVideoView().clearAll();
            holder.getAah_vi().getCustomVideoView().invalidate();
        }
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }



}
