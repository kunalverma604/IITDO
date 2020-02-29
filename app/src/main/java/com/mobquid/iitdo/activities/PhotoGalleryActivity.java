package com.mobquid.iitdo.activities;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.adapters.PhotosGalleryRecyclerViewActivity;
import com.mobquid.iitdo.models.Gallery;
import com.mobquid.iitdo.models.GalleryResponse;
import com.mobquid.iitdo.models.PhotoGallery;
import com.mobquid.iitdo.retrofit.Api;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PhotoGalleryActivity extends AppCompatActivity {

    @BindView(R.id.photos_gallery_rv)
    RecyclerView photosGalleryRv;
    List<Gallery> photoGalleryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Photo Gallery");
        setContentView(R.layout.activity_photo_gallery);
        ButterKnife.bind(this);
        fetchGalleryList();
    }

    public void fetchGalleryList() {
        Api.getClient().getGallery(new Callback<GalleryResponse>() {
            @Override
            public void success(GalleryResponse galleryResponse, Response response) {
                photoGalleryList = galleryResponse.getGalleryList();
                PhotosGalleryRecyclerViewActivity photosGalleryRecyclerViewAdapter = new PhotosGalleryRecyclerViewActivity(getApplicationContext(), photoGalleryList);
                photosGalleryRv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
                photosGalleryRv.setAdapter(photosGalleryRecyclerViewAdapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
