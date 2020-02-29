package com.mobquid.iitdo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Events {

    @SerializedName("buyer_mission")
    @Expose
    private String buyerMission;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("participant_incentives")
    @Expose
    private String participantIncentives;

    public Events(String buyerMission, String country, String date, String participantIncentives) {
        this.buyerMission = buyerMission;
        this.country = country;
        this.date = date;
        this.participantIncentives = participantIncentives;
    }

    public String getBuyerMission() {
        return buyerMission;
    }

    public void setBuyerMission(String buyerMission) {
        this.buyerMission = buyerMission;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getParticipantIncentives() {
        return participantIncentives;
    }

    public void setParticipantIncentives(String participantIncentives) {
        this.participantIncentives = participantIncentives;
    }
}