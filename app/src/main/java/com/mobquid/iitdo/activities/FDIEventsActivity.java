package com.mobquid.iitdo.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.adapters.FDIEventsRecyclerViewAdapter;
import com.mobquid.iitdo.models.FDI;
import com.mobquid.iitdo.models.FDIEvents;
import com.mobquid.iitdo.models.FdiResponse;
import com.mobquid.iitdo.retrofit.Api;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FDIEventsActivity extends AppCompatActivity {

    @BindView(R.id.fdi_events_rv)
    RecyclerView fdiEventsRv;
    List<FDI> fdiEventsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("FDI Events/Govt. Schemes");
        setContentView(R.layout.activity_fdievents);
        ButterKnife.bind(this);
        fetchFDIFromServer();
    }

    public void fetchFDIFromServer() {
        Api.getClient().getFDI(new Callback<FdiResponse>() {
            @Override
            public void success(FdiResponse fdiResponse, Response response) {
                fdiEventsList = fdiResponse.getFdiList();
                FDIEventsRecyclerViewAdapter fdiEventsRecyclerViewAdapter = new FDIEventsRecyclerViewAdapter(getApplicationContext(), fdiEventsList);
                fdiEventsRv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                fdiEventsRv.setAdapter(fdiEventsRecyclerViewAdapter);
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
