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
import com.mobquid.iitdo.models.MajorItems;
import com.mobquid.iitdo.models.News;

import java.util.List;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder>{

    Context context;
    List<News> newsList;

    public NewsRecyclerViewAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.news_single_item, parent,false);

        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Glide.with(context)
                .load(newsList.get(position).getImage())
                .into(holder.news_image_iv);
        holder.news_head_tv.setText(newsList.get(position).getHead());
        // holder.news_date_tv.setText(newsList.get(position).get());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView news_image_iv;
        TextView news_head_tv, news_date_tv;

        public NewsViewHolder(View itemView) {
            super(itemView);
            news_image_iv = itemView.findViewById(R.id.news_image_iv);
            news_head_tv = itemView.findViewById(R.id.news_head_tv);
            news_date_tv = itemView.findViewById(R.id.news_date_tv);
        }
    }
}
