package com.mobquid.iitdo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class StaticDataResponse {

    @SerializedName("static_data")
    @Expose
    List<StaticData> staticDataResponses = new ArrayList<>();

    public StaticDataResponse(List<StaticData> staticDataResponses) {
        this.staticDataResponses = staticDataResponses;
    }

    public List<StaticData> getStaticDataResponses() {
        return staticDataResponses;
    }

    public void setStaticDataResponses(List<StaticData> staticDataResponses) {
        this.staticDataResponses = staticDataResponses;
    }
}
