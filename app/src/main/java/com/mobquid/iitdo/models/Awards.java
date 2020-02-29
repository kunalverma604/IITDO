package com.mobquid.iitdo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Awards {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("head")
    @Expose
    private String head;

    @SerializedName("descr")
    @Expose
    private String descr;

    public Awards(int id, String head, String descr) {
        this.id = id;
        this.head = head;
        this.descr = descr;
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
}
