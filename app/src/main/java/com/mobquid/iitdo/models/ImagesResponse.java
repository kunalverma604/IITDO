package com.mobquid.iitdo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ImagesResponse {

    @SerializedName("images")
    @Expose
    List<Images> sliderImagesList = new ArrayList<>();

    public ImagesResponse(List<Images> sliderImagesList) {
        this.sliderImagesList = sliderImagesList;
    }

    public List<Images> getSliderImagesList() {
        return sliderImagesList;
    }

    public void setSliderImagesList(List<Images> sliderImagesList) {
        this.sliderImagesList = sliderImagesList;
    }
}
