package com.mobquid.iitdo.activities;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.TextView;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.adapters.FDIEventsRecyclerViewAdapter;
import com.mobquid.iitdo.models.FDI;
import com.mobquid.iitdo.models.FDIEvents;
import com.mobquid.iitdo.models.FdiResponse;
import com.mobquid.iitdo.models.StaticData;
import com.mobquid.iitdo.models.StaticDataResponse;
import com.mobquid.iitdo.retrofit.Api;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class FDIEventsActivity extends AppCompatActivity {

    @BindView(R.id.fdi_inputs_tv)
    TextView fdiInputsTv;
    List<FDI> fdiEventsList = new ArrayList<>();
    List<StaticData> staticData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("FDI Inputs/Govt. Schemes");
        setContentView(R.layout.activity_fdievents);
        ButterKnife.bind(this);
        fetchFDIFromServer();
    }

    public void fetchFDIFromServer() {
        Api.getClient().getStaticData("fdi_inputs", new Callback<StaticDataResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void success(StaticDataResponse staticDataResponse, Response response) {
                staticData = staticDataResponse.getStaticDataResponses();
                fdiInputsTv.setText(staticData.get(0).getDescription());
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    fdiInputsTv.setText(Html.fromHtml(staticData.get(0).getDescription(),Html.FROM_HTML_MODE_COMPACT));
                    fdiInputsTv.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
                } else {
                    fdiInputsTv.setText(Html.fromHtml(staticData.get(0).getDescription()));
                }
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
