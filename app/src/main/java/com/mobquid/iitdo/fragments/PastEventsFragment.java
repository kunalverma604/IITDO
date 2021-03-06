package com.mobquid.iitdo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.adapters.EventsRecyclerViewAdapter;
import com.mobquid.iitdo.models.EventResponse;
import com.mobquid.iitdo.models.Events;
import com.mobquid.iitdo.retrofit.Api;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PastEventsFragment extends Fragment {

    Unbinder unbinder;
    List<Events> eventsList = new ArrayList<>();
    @BindView(R.id.past_events_rv)
    RecyclerView pastEventsRv;

    public PastEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_past_events, container, false);
        unbinder = ButterKnife.bind(this, view);
        getDataFromServer();
        return view;
    }

    public void getDataFromServer() {
        Api.getClient().getEvents("Past", new Callback<EventResponse>() {
            @Override
            public void success(EventResponse eventResponse, Response response) {
                eventsList = eventResponse.getEvents();
                EventsRecyclerViewAdapter eventsRecyclerViewAdapter = new EventsRecyclerViewAdapter(getContext(), eventsList);
                pastEventsRv.setLayoutManager(new GridLayoutManager(getContext(), 1));
                pastEventsRv.setAdapter(eventsRecyclerViewAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("apipast", error.toString());
            }
        });
    }
}
