package com.mobquid.iitdo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobquid.iitdo.R;
import com.mobquid.iitdo.models.Investment;
import com.mobquid.iitdo.models.InvestmentOpportunities;

import java.util.List;

public class InvOppRecyclerViewAdapter extends RecyclerView.Adapter<InvOppRecyclerViewAdapter.InvOppViewHolder> {

    Context context;
    List<Investment> investmentOpportunitiesList;

    public InvOppRecyclerViewAdapter(Context context, List<Investment> investmentOpportunitiesList) {
        this.context = context;
        this.investmentOpportunitiesList = investmentOpportunitiesList;
    }

    @NonNull
    @Override
    public InvOppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.inv_opp_single_item, parent,false);

        return new InvOppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvOppViewHolder holder, int position) {
        Glide.with(context)
                .load(investmentOpportunitiesList.get(position).getImage())
                .into(holder.inv_opp_single_image_iv);
        holder.inv_opp_single_name_tv.setText(investmentOpportunitiesList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return investmentOpportunitiesList.size();
    }

    public class InvOppViewHolder extends RecyclerView.ViewHolder {

        ImageView inv_opp_single_image_iv;
        TextView inv_opp_single_name_tv;

        public InvOppViewHolder(View itemView) {
            super(itemView);
            inv_opp_single_image_iv = itemView.findViewById(R.id.inv_opp_single_image_iv);
            inv_opp_single_name_tv = itemView.findViewById(R.id.inv_opp_single_name_tv);
        }
    }
}
