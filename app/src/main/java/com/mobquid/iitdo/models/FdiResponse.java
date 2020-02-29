package com.mobquid.iitdo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FdiResponse {

    @SerializedName("fdi")
    @Expose
    List<FDI> fdiList = new ArrayList<>();

    public FdiResponse(List<FDI> fdiList) {
        this.fdiList = fdiList;
    }

    public List<FDI> getFdiList() {
        return fdiList;
    }

    public void setFdiList(List<FDI> fdiList) {
        this.fdiList = fdiList;
    }
}
