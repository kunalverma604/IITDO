package com.mobquid.iitdo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class IOResponse {

    @SerializedName("investment")
    @Expose
    List<Investment> investmentList = new ArrayList<>();

    public IOResponse(List<Investment> investmentList) {
        this.investmentList = investmentList;
    }

    public List<Investment> getInvestmentList() {
        return investmentList;
    }

    public void setInvestmentList(List<Investment> investmentList) {
        this.investmentList = investmentList;
    }
}
