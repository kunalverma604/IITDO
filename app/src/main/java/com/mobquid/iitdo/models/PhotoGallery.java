package com.mobquid.iitdo.models;

public class PhotoGallery {

    String galleryImage;
    String galleryName;

    public PhotoGallery(String galleryImage, String galleryName) {
        this.galleryImage = galleryImage;
        this.galleryName = galleryName;
    }

    public String getGalleryImage() {
        return galleryImage;
    }

    public void setGalleryImage(String galleryImage) {
        this.galleryImage = galleryImage;
    }

    public String getGalleryName() {
        return galleryName;
    }

    public void setGalleryName(String galleryName) {
        this.galleryName = galleryName;
    }
}
