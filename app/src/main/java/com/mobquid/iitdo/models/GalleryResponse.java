package com.mobquid.iitdo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GalleryResponse {

    @SerializedName("gallery")
    @Expose
    List<Gallery> galleryList = new ArrayList<>();

    public GalleryResponse(List<Gallery> galleryList) {
        this.galleryList = galleryList;
    }

    public List<Gallery> getGalleryList() {
        return galleryList;
    }

    public void setGalleryList(List<Gallery> galleryList) {
        this.galleryList = galleryList;
    }
}
