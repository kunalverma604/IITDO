package com.mobquid.iitdo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mobquid.iitdo.R;
import com.mobquid.iitdo.activities.AwardsAchievementsActivity;
import com.mobquid.iitdo.activities.EventsActivity;
import com.mobquid.iitdo.activities.FDIEventsActivity;
import com.mobquid.iitdo.activities.InvestmentOpportunitiesActivity;
import com.mobquid.iitdo.activities.JoinIITDOActivity;
import com.mobquid.iitdo.activities.KeyPeopleActivity;
import com.mobquid.iitdo.activities.NewsActivity;
import com.mobquid.iitdo.activities.OpportunityActivity;
import com.mobquid.iitdo.activities.PhotoGalleryActivity;
import com.mobquid.iitdo.activities.PhotoGalleryActivity_ViewBinding;
import com.mobquid.iitdo.models.FDIEvents;
import com.mobquid.iitdo.models.MajorItems;

import java.util.List;

public class MajorItemsRecyclerViewAdapter extends RecyclerView.Adapter<MajorItemsRecyclerViewAdapter.MajorItemsViewHolder> {

    Context context;
    List<MajorItems> majorItemsList;

    public MajorItemsRecyclerViewAdapter(Context context, List<MajorItems> majorItemsList) {
        this.context = context;
        this.majorItemsList = majorItemsList;
    }

    @NonNull
    @Override
    public MajorItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.major_items_single_item, parent,false);

        return new MajorItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MajorItemsViewHolder holder, final int position) {
        Glide.with(context)
                .load(majorItemsList.get(position).getImage())
                .apply(new RequestOptions())
                .into(holder.majorItemsSingleImageIv);
        holder.majorItemsSingleNameTv.setText(majorItemsList.get(position).getName());
        holder.majorItemSingleParentCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position) {
                    case 0:
                        Intent opportunityIntent = new Intent(context, OpportunityActivity.class);
                        opportunityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(opportunityIntent);
                        break;
                    case 1:
                        Intent fdiEventsIntent = new Intent(context, FDIEventsActivity.class);
                        fdiEventsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(fdiEventsIntent);
                        break;
                    case 2:
                        Intent investmentOpportunities = new Intent(context, InvestmentOpportunitiesActivity.class);
                        investmentOpportunities.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(investmentOpportunities);
                        break;
                    case 3:
                        Intent keyPeopleIntent = new Intent(context, KeyPeopleActivity.class);
                        keyPeopleIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(keyPeopleIntent);
                        break;
                    case 4:
                        Intent eventsIntent = new Intent(context, EventsActivity.class);
                        eventsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(eventsIntent);
                        break;
                    case 5:
                        Intent awardsIntent = new Intent(context, AwardsAchievementsActivity.class);
                        awardsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(awardsIntent);
                        break;
                    case 6:
                        Intent galleryIntent = new Intent(context, PhotoGalleryActivity.class);
                        galleryIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(galleryIntent);
                        break;
                    case 7:
                        Intent newsIntent = new Intent(context, NewsActivity.class);
                        newsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(newsIntent);
                        break;
                    case 8:
                        Intent joinIntent = new Intent(context, JoinIITDOActivity.class);
                        joinIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(joinIntent);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return majorItemsList.size();
    }

    public static class MajorItemsViewHolder extends RecyclerView.ViewHolder {

        ImageView majorItemsSingleImageIv;
        TextView majorItemsSingleNameTv;
        CardView majorItemSingleParentCv;

        public MajorItemsViewHolder(View itemView) {
            super(itemView);
            majorItemsSingleImageIv = itemView.findViewById(R.id.major_items_single_image_iv);
            majorItemsSingleNameTv = itemView.findViewById(R.id.major_items_single_name_tv);
            majorItemSingleParentCv = itemView.findViewById(R.id.major_items_single_parent_cv);

        }
    }

}
