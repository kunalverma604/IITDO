package com.mobquid.iitdo.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.adapters.AllPhotosRecyclerViewAdapter;
import com.mobquid.iitdo.adapters.FullScreenImageAdapter;
import com.mobquid.iitdo.helpers.GalleryRecyclerViewClickListener;
import com.mobquid.iitdo.models.Images;
import com.mobquid.iitdo.models.ImagesResponse;
import com.mobquid.iitdo.retrofit.Api;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FullScreenImageActivity extends AppCompatActivity {

    @BindView(R.id.single_image_fullscreen_vp)
    ViewPager singleImageFullscreenVp;
    int position;
    List<Images> imagesList = new ArrayList<>();
    String galleryname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
        setContentView(R.layout.activity_full_screen_image);
        ButterKnife.bind(this);

        position = getIntent().getExtras().getInt("position", 0);
        galleryname = getIntent().getExtras().getString("galleryname");
        fetchAllImages(position, galleryname);
    }

    public void fetchAllImages(final int position, String galleryname) {
        Api.getClient().getImages(galleryname, new Callback<ImagesResponse>() {
            @Override
            public void success(ImagesResponse imagesResponse, Response response) {
                imagesList = imagesResponse.getSliderImagesList();
                FullScreenImageAdapter fullScreenImageAdapter = new FullScreenImageAdapter(getApplicationContext(), imagesList);
                singleImageFullscreenVp.setAdapter(fullScreenImageAdapter);
                singleImageFullscreenVp.setCurrentItem(position, true);
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
