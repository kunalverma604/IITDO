package com.mobquid.iitdo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SuccessResponse {

    @SerializedName("insert")
    @Expose
    List<Success> successes = new ArrayList<>();

    public SuccessResponse(List<Success> successes) {
        this.successes = successes;
    }

    public List<Success> getSuccesses() {
        return successes;
    }

    public void setSuccesses(List<Success> successes) {
        this.successes = successes;
    }
}
