package com.mobquid.iitdo.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.adapters.MajorItemsRecyclerViewAdapter;
import com.mobquid.iitdo.models.KeySectionsResponse;
import com.mobquid.iitdo.models.StaticData;
import com.mobquid.iitdo.models.StaticDataResponse;
import com.mobquid.iitdo.retrofit.Api;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OpportunityActivity extends AppCompatActivity {

    List<StaticData> staticData;
    @BindView(R.id.opportunity_tv)
    TextView opportunityTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Opportunities with IITDO");
        setContentView(R.layout.activity_opportunity);
        ButterKnife.bind(this);
        fetchDataFromServer("home_opportunities_with_iitdo");
    }

    public void fetchDataFromServer(String name) {
        Api.getClient().getStaticData(name, new Callback<StaticDataResponse>() {
            @Override
            public void success(StaticDataResponse staticDataResponse, Response response) {
                staticData = staticDataResponse.getStaticDataResponses();
                opportunityTv.setText(staticData.get(0).getDescription());
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
