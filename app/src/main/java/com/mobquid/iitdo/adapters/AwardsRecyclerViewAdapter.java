package com.mobquid.iitdo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.models.Awards;

import org.w3c.dom.Text;

import java.util.List;

public class AwardsRecyclerViewAdapter extends RecyclerView.Adapter<AwardsRecyclerViewAdapter.AwardsViewHolder> {

    Context context;
    List<Awards> awardsList;

    public AwardsRecyclerViewAdapter(Context context, List<Awards> awardsList) {
        this.context = context;
        this.awardsList = awardsList;
    }

    @NonNull
    @Override
    public AwardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.awards_single_item, parent,false);
        return new AwardsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AwardsViewHolder holder, int position) {
        holder.awards_text_tv.setText(awardsList.get(position).getHead());
    }

    @Override
    public int getItemCount() {
        return awardsList.size();
    }

    public class AwardsViewHolder extends RecyclerView.ViewHolder {

        TextView awards_text_tv;

        public AwardsViewHolder(View itemView) {
            super(itemView);
            awards_text_tv = itemView.findViewById(R.id.awards_text_tv);
        }
    }
}
