package com.mobquid.iitdo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.models.FDI;
import com.mobquid.iitdo.models.FDIEvents;

import java.util.List;

public class FDIEventsRecyclerViewAdapter extends RecyclerView.Adapter<FDIEventsRecyclerViewAdapter.FDIEventsViewHolder> {

    Context context;
    List<FDI> fdiEventsList;

    public FDIEventsRecyclerViewAdapter(Context context, List<FDI> fdiEventsList) {
        this.context = context;
        this.fdiEventsList = fdiEventsList;
    }

    @NonNull
    @Override
    public FDIEventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.fdi_events_single_item, parent,false);
        return new FDIEventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FDIEventsViewHolder holder, int position) {
        holder.fdi_events_text_tv.setText(fdiEventsList.get(position).getHead());
        holder.fdi_events_head_tv.setText(fdiEventsList.get(position).getDescr());
    }

    @Override
    public int getItemCount() {
        return fdiEventsList.size();
    }

    public class FDIEventsViewHolder extends RecyclerView.ViewHolder {

        TextView fdi_events_text_tv, fdi_events_head_tv;

        public FDIEventsViewHolder(View itemView) {
            super(itemView);
            fdi_events_head_tv = itemView.findViewById(R.id.fdi_events_head_tv);
            fdi_events_text_tv = itemView.findViewById(R.id.fdi_events_text_tv);
        }
    }
}
