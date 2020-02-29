package com.mobquid.iitdo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AwardsResponse {

    @SerializedName("awards")
    @Expose
    List<Awards> awardsList = new ArrayList<>();

    public AwardsResponse(List<Awards> awardsList) {
        this.awardsList = awardsList;
    }

    public List<Awards> getAwardsList() {
        return awardsList;
    }

    public void setAwardsList(List<Awards> awardsList) {
        this.awardsList = awardsList;
    }
}
