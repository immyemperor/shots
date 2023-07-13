package com.waindesk.successshots.videoutils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.squareup.picasso.Picasso;
import com.waindesk.successshots.R;
import com.waindesk.successshots.autoplay.CustomViewHolder;
import com.waindesk.successshots.autoplay.VideosAdapter;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AutoPlayVideoAdapter extends VideosAdapter {
    private String TAG = "AutoPlayVideoAdapter";

    private final List<VideoModel> list;
    private final Picasso picasso;

    public static class MyViewHolder extends CustomViewHolder {
        @BindView(R.id.tv) TextView tv;
        @BindView(R.id.fb_user_name) TextView userName;
        @BindView(R.id.img_vol) ImageView img_vol;
        @BindView(R.id.img_playback) ImageView img_playback;
        @BindView(R.id.fb_user_icon) ImageView userIcon;

//        @BindView(R.id.fb_item_top) FrameLayout fb_item_top;
//
//        @BindView(R.id.fb_item_bottom) LinearLayout fb_item_bottom;
        //to mute/un-mute video (optional)
        boolean isMuted;

        public MyViewHolder(View x) {
            super(x);
            ButterKnife.bind(this, x);
        }

        //override this method to get callback when video starts to play
        @Override
        public void videoStarted() {
            super.videoStarted();
            img_playback.setImageResource(R.drawable.ic_pause);
            if (isMuted) {
                muteVideo();
                img_vol.setImageResource(R.drawable.ic_mute);
            } else {
                unmuteVideo();
                img_vol.setImageResource(R.drawable.ic_unmute);
            }
        }
        @Override
        public void pauseVideo() {
            super.pauseVideo();
            img_playback.setImageResource(R.drawable.ic_play);
        }
    }
    public AutoPlayVideoAdapter(List<VideoModel> list_urls, Picasso p) {
        this.list = list_urls;
        this.picasso = p;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {
//        ((MyViewHolder) holder).fb_item_top.setZ(2);
//        ((MyViewHolder) holder).fb_item_bottom.setZ(2);
        ((MyViewHolder) holder).userName.setText(list.get(position).getName());
        ((MyViewHolder) holder).tv.setText(String.format("%s・%s", (position + 5) + " minutes from now",list.get(position).getName().toUpperCase()));

        picasso.load(R.mipmap.ic_launcher).config(Bitmap.Config.RGB_565).into(((MyViewHolder) holder).userIcon);
        //todo
        //Bitmap bitmap = null;
        Log.e(TAG, "--->ImageUrl=" + list.get(position).getImage_url());
        if(list.get(position).getImage_url() == "") {
            Log.e(TAG, "--->ImageUrl=" + list.get(0).getImage_url());
            holder.setImageUrl(list.get(0).getImage_url());
            Log.e(TAG, "--->ImageUrl=" + holder.getImageUrl());
        } else {
            Log.e(TAG, "--->ImageUrl position=" + list.get(position).getImage_url());
            holder.setImageUrl(list.get(position).getImage_url());
            Log.e(TAG, "--->ImageUrl holder=" + holder.getImageUrl());
        }

        if(!list.get(position).getVideo_url().isEmpty()) {
            holder.setVideoUrl(list.get(position).getVideo_url());
            Log.e(TAG, "--->ImageUrl=" + holder.getImageUrl());
        } else {
            holder.setVideoUrl(list.get(0).getVideo_url());
        }



        //load image into imageview
        if (list.get(position).getImage_url() != null && !list.get(position).getImage_url().isEmpty()) {
            picasso.load(holder.getImageUrl()).config(Bitmap.Config.RGB_565).into(holder.getImageView());

            Log.e(TAG, "--->ImageUrl=" + holder.getImageUrl());
        }

        holder.setLooping(true); //optional - true by default

        //to play pause videos manually (optional)
        ((MyViewHolder) holder).img_playback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.isPlaying()) {
                    holder.pauseVideo();
                    holder.setPaused(true);
                } else {
                    holder.playVideo();
                    holder.setPaused(false);
                }
            }
        });

        //to mute/un-mute video (optional)
        ((MyViewHolder) holder).img_vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((MyViewHolder) holder).isMuted) {
                    holder.unmuteVideo();
                    ((MyViewHolder) holder).img_vol.setImageResource(R.drawable.ic_unmute);
                } else {
                    holder.muteVideo();
                    ((MyViewHolder) holder).img_vol.setImageResource(R.drawable.ic_mute);
                }
                ((MyViewHolder) holder).isMuted = !((MyViewHolder) holder).isMuted;
            }
        });

        if (list.get(position).getVideo_url() == null) {
            ((MyViewHolder) holder).img_vol.setVisibility(View.GONE);
            ((MyViewHolder) holder).img_playback.setVisibility(View.GONE);
        } else {
            ((MyViewHolder) holder).img_vol.setVisibility(View.VISIBLE);
            ((MyViewHolder) holder).img_playback.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public Bitmap decodeBitmap(String encodedImage){
        byte[] decodedImageBytes = Base64.decode(encodedImage, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedImageBytes, 0, decodedImageBytes.length);
        return  bitmap;
    }


}