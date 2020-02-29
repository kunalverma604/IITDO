package com.mobquid.iitdo.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.adapters.AwardsRecyclerViewAdapter;
import com.mobquid.iitdo.models.Awards;
import com.mobquid.iitdo.models.AwardsResponse;
import com.mobquid.iitdo.retrofit.Api;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AwardsAchievementsActivity extends AppCompatActivity {

    @BindView(R.id.awards_rv)
    RecyclerView awardsRv;
    List<Awards> awardsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Awards & Achievements");
        setContentView(R.layout.activity_awards_achievements);
        ButterKnife.bind(this);
        fetchAwardsFromServer();
    }

    public void fetchAwardsFromServer() {
        Api.getClient().getAwards(new Callback<AwardsResponse>() {
            @Override
            public void success(AwardsResponse awardsResponse, Response response) {
                awardsList = awardsResponse.getAwardsList();
                AwardsRecyclerViewAdapter awardsRecyclerViewAdapter = new AwardsRecyclerViewAdapter(getApplicationContext(), awardsList);
                awardsRv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                awardsRv.setAdapter(awardsRecyclerViewAdapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

}
