package com.mobquid.iitdo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gallery {

    @SerializedName("gallery_name")
    @Expose
    private String gallery_name;

    @SerializedName("cover_image")
    @Expose
    private String cover_image;

    public Gallery(String gallery_name, String cover_image) {
        this.gallery_name = gallery_name;
        this.cover_image = cover_image;
    }

    public String getGallery_name() {
        return gallery_name;
    }

    public void setGallery_name(String gallery_name) {
        this.gallery_name = gallery_name;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }
}
