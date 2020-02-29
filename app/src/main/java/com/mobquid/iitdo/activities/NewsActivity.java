package com.mobquid.iitdo.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.adapters.NewsRecyclerViewAdapter;
import com.mobquid.iitdo.models.News;
import com.mobquid.iitdo.models.NewsResponse;
import com.mobquid.iitdo.retrofit.Api;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class NewsActivity extends AppCompatActivity {

    @BindView(R.id.news_recyclerview)
    RecyclerView newsRecyclerview;
    List<News> newsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("IITDO News");
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        fetchNewsFromServer();
    }

    public void fetchNewsFromServer() {
        Api.getClient().getNews(new Callback<NewsResponse>() {
            @Override
            public void success(NewsResponse newsResponse, Response response) {
                newsList = newsResponse.getNewsList();
                NewsRecyclerViewAdapter newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(getApplicationContext(), newsList);
                newsRecyclerview.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                newsRecyclerview.setAdapter(newsRecyclerViewAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("newsapi", error.toString());
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
