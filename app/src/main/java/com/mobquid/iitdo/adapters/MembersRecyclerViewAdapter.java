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
import com.mobquid.iitdo.models.Members;

import java.util.ArrayList;
import java.util.List;

public class MembersRecyclerViewAdapter extends RecyclerView.Adapter<MembersRecyclerViewAdapter.MyViewHolder> {

    Context context;
    List<Members> membersList = new ArrayList<>();

    public MembersRecyclerViewAdapter(Context context, List<Members> membersList) {
        this.context = context;
        this.membersList = membersList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.members_single_item, parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Glide.with(context)
                .load(membersList.get(position).getImage())
                .into(holder.membersSingleImageIv);
        holder.membersSingleNameTv.setText(membersList.get(position).getName());
        holder.membersSingleDesignationTv.setText(membersList.get(position).getDesignation());
    }

    @Override
    public int getItemCount() {
        if (membersList ==  null) {
            return 0;
        } else {
            return membersList.size();
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView membersSingleImageIv;
        TextView membersSingleNameTv, membersSingleDesignationTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            membersSingleImageIv = itemView.findViewById(R.id.members_single_image_iv);
            membersSingleNameTv = itemView.findViewById(R.id.members_single_name_tv);
            membersSingleDesignationTv = itemView.findViewById(R.id.members_single_designation_tv);
        }
    }

}

