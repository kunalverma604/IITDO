package com.mobquid.iitdo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.models.Events;

import java.util.List;

public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<EventsRecyclerViewAdapter.EventsViewHolder> {

    Context context;
    List<Events> eventsList;

    public EventsRecyclerViewAdapter(Context context, List<Events> eventsList) {
        this.context = context;
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.events_single_item, parent,false);
        return new EventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {
        holder.eventsMissionTv.setText("Buyer Mission : " +eventsList.get(position).getBuyerMission());
        holder.eventsCountryTv.setText("Country : "+eventsList.get(position).getCountry());
        holder.eventsDateTv.setText("Date : "+eventsList.get(position).getDate());
        holder.eventsIncentivesTv.setText("Participant Incentives :"+eventsList.get(position).getParticipantIncentives());
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public class EventsViewHolder extends RecyclerView.ViewHolder {

        TextView eventsMissionTv, eventsCountryTv, eventsDateTv, eventsIncentivesTv;

        public EventsViewHolder(View itemView) {
            super(itemView);
            eventsMissionTv = itemView.findViewById(R.id.events_mission_tv);
            eventsCountryTv = itemView.findViewById(R.id.events_country_tv);
            eventsDateTv = itemView.findViewById(R.id.events_date_tv);
            eventsIncentivesTv = itemView.findViewById(R.id.events_incentives_tv);
        }

    }
}
