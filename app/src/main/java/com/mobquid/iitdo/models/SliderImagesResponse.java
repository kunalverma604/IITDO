package com.mobquid.iitdo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SliderImagesResponse {

    @SerializedName("slider_images_app")
    @Expose
    List<SliderImages> sliderImagesList = new ArrayList<>();

    public SliderImagesResponse(List<SliderImages> sliderImagesList) {
        this.sliderImagesList = sliderImagesList;
    }

    public List<SliderImages> getSliderImagesList() {
        return sliderImagesList;
    }

    public void setSliderImagesList(List<SliderImages> sliderImagesList) {
        this.sliderImagesList = sliderImagesList;
    }
}
