package com.mobquid.iitdo.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mobquid.iitdo.R;
import com.mobquid.iitdo.helpers.GalleryRecyclerViewClickListener;
import com.mobquid.iitdo.models.Images;

import java.util.List;

public class AllPhotosRecyclerViewAdapter extends RecyclerView.Adapter<AllPhotosRecyclerViewAdapter.MyViewHolder> {

    Context context;
    List<Images> imageList;
    GalleryRecyclerViewClickListener galleryRecyclerViewClickListener;

    public AllPhotosRecyclerViewAdapter(Context context, List<Images> imageList, GalleryRecyclerViewClickListener galleryRecyclerViewClickListener) {
        this.context = context;
        this.imageList = imageList;
        this.galleryRecyclerViewClickListener = galleryRecyclerViewClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.gallery_images_single_item, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        // holder.imageSingleGalleryIv.setImageDrawable(imageList.get(position).getImage_link());
        Glide.with(context)
                .load(imageList.get(position).getImage())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressSingleGalleryPb.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.imageSingleGalleryIv);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageSingleGalleryIv;
        ProgressBar progressSingleGalleryPb;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageSingleGalleryIv = itemView.findViewById(R.id.image_single_gallery_iv);
            progressSingleGalleryPb = itemView.findViewById(R.id.progress_single_gallery_pb);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            galleryRecyclerViewClickListener.onClick(view, getAdapterPosition());
        }

    }

}
