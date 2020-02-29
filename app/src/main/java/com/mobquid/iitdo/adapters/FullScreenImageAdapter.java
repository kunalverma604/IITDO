package com.mobquid.iitdo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mobquid.iitdo.R;
import com.mobquid.iitdo.models.Images;

import java.util.List;

public class FullScreenImageAdapter extends PagerAdapter {

    Context context;
    List<Images> imagesList;
    LayoutInflater layoutInflater;

    public FullScreenImageAdapter(Context context, List<Images> imagesList) {
        this.context = context;
        this.imagesList = imagesList;
    }

    @Override
    public int getCount() {
        return imagesList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.full_screen_image, null);

        ImageView singleImageIv = view.findViewById(R.id.single_image_iv);

        Glide.with(context)
                .load(imagesList.get(position).getImage())
                .apply(new RequestOptions())
                .into(singleImageIv);

//        ViewPager vp = (ViewPager)container;
//        vp.addView(vp, 0);
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager)container;
        View view = (View)object;
        viewPager.removeView(view);
    }
}

