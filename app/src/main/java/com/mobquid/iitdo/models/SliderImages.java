package com.mobquid.iitdo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SliderImages {

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("caption")
    @Expose
    private String caption;

    public SliderImages(String image, String caption) {
        this.image = image;
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
