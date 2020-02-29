package com.mobquid.iitdo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("head")
    @Expose
    private String head;

    @SerializedName("descr")
    @Expose
    private String descr;

    @SerializedName("image")
    @Expose
    private String image;

    public News(int id, String head, String descr, String image) {
        this.id = id;
        this.head = head;
        this.descr = descr;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
