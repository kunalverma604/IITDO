package com.mobquid.iitdo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mobquid.iitdo.R;
import com.mobquid.iitdo.models.SliderImages;

import java.util.List;

public class SlidingImageAdapter extends PagerAdapter {

    private List<SliderImages> imagesList;
    private LayoutInflater inflater;
    private Context context;

    public SlidingImageAdapter(List<SliderImages> imagesList, Context context) {
        this.imagesList = imagesList;
        this.context = context;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return imagesList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.image_slider_single_item, null);
        assert view != null;
        final ImageView sliderImageSingleIv = (ImageView) viewLayout.findViewById(R.id.slider_image_single_iv);
        Glide.with(context)
                .load(imagesList.get(position).getImage())
                .apply(new RequestOptions())
                .into(sliderImageSingleIv);
        view.addView(viewLayout, 0);
        return viewLayout;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
