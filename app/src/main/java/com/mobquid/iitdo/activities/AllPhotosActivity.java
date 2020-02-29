package com.mobquid.iitdo.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.adapters.AllPhotosRecyclerViewAdapter;
import com.mobquid.iitdo.helpers.GalleryRecyclerViewClickListener;
import com.mobquid.iitdo.models.Images;
import com.mobquid.iitdo.models.ImagesResponse;
import com.mobquid.iitdo.models.SuccessResponse;
import com.mobquid.iitdo.retrofit.Api;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AllPhotosActivity extends AppCompatActivity {

    String galleryName;
    @BindView(R.id.all_photos_rv)
    RecyclerView allPhotosRv;
    List<Images> imagesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_all_photos);
        ButterKnife.bind(this);

        galleryName = getIntent().getExtras().getString("gallery_name");
        actionBar.setTitle(galleryName);
        fetchAllImages(galleryName);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void fetchAllImages(final String galleryName) {
        Api.getClient().getImages(galleryName, new Callback<ImagesResponse>() {
            @Override
            public void success(ImagesResponse imagesResponse, Response response) {
                imagesList = imagesResponse.getSliderImagesList();
                allPhotosRv.setHasFixedSize(true);
                allPhotosRv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));

                final GalleryRecyclerViewClickListener clickListener = new GalleryRecyclerViewClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(), FullScreenImageActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("galleryname", galleryName);
                        startActivity(intent);
                    }
                };

                AllPhotosRecyclerViewAdapter recyclerViewAdapter = new AllPhotosRecyclerViewAdapter(getApplicationContext(), imagesList, clickListener);
                allPhotosRv.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

}

