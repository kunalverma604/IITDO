package com.mobquid.iitdo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mobquid.iitdo.R;
import com.mobquid.iitdo.activities.AllPhotosActivity;
import com.mobquid.iitdo.models.Gallery;
import com.mobquid.iitdo.models.PhotoGallery;

import java.util.List;

public class PhotosGalleryRecyclerViewActivity extends RecyclerView.Adapter<PhotosGalleryRecyclerViewActivity.MyViewHolder> {

    Context context;
    List<Gallery> photosGalleryList;

    public PhotosGalleryRecyclerViewActivity(Context context, List<Gallery> photosGalleryList) {
        this.context = context;
        this.photosGalleryList = photosGalleryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.photos_gallery_single_item, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Glide.with(context)
                .setDefaultRequestOptions(new RequestOptions())
                .load(photosGalleryList.get(position).getCover_image())
                .into(holder.photosGallerySingleImageIv);
        holder.photosGallerySingleNameTv.setText(photosGalleryList.get(position).getGallery_name());

        holder.photosGallerySingleImageIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent singleGalleryIntent = new Intent(context, AllPhotosActivity.class);
                singleGalleryIntent.putExtra("gallery_name", ""+photosGalleryList.get(position).getGallery_name());
                singleGalleryIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(singleGalleryIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photosGalleryList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView photosGallerySingleImageIv;
        TextView photosGallerySingleNameTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            photosGallerySingleImageIv = itemView.findViewById(R.id.photos_gallery_single_image_iv);
            photosGallerySingleNameTv = itemView.findViewById(R.id.photos_gallery_single_name_tv);
        }
    }
}
