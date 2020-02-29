package com.mobquid.iitdo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EventResponse {

    @SerializedName("events")
    @Expose
    List<Events> events = new ArrayList<>();

    public EventResponse(List<Events> events) {
        this.events = events;
    }

    public List<Events> getEvents() {
        return events;
    }

    public void setEvents(List<Events> events) {
        this.events = events;
    }
}
