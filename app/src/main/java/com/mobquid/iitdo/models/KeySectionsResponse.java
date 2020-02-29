package com.mobquid.iitdo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class KeySectionsResponse {

    @SerializedName("key_sections")
    @Expose
    List<MajorItems> majorItems = new ArrayList<>();

    public KeySectionsResponse(List<MajorItems> majorItems) {
        this.majorItems = majorItems;
    }

    public List<MajorItems> getMajorItems() {
        return majorItems;
    }

    public void setMajorItems(List<MajorItems> majorItems) {
        this.majorItems = majorItems;
    }
}
