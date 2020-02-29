package com.mobquid.iitdo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("gallery_name")
    @Expose
    private String gallery_name;

    @SerializedName("image")
    @Expose
    private String image;

    public Images(String gallery_name, String image) {
        this.gallery_name = gallery_name;
        this.image = image;
    }

    public String getGallery_name() {
        return gallery_name;
    }

    public void setGallery_name(String gallery_name) {
        this.gallery_name = gallery_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
