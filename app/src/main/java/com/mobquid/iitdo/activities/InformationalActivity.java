package com.mobquid.iitdo.activities;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.models.StaticData;
import com.mobquid.iitdo.models.StaticDataResponse;
import com.mobquid.iitdo.models.SuccessResponse;
import com.mobquid.iitdo.retrofit.Api;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class InformationalActivity extends AppCompatActivity {

    @BindView(R.id.activity_informational_iv)
    ImageView activityInformationalIv;
    @BindView(R.id.activity_informational_tv)
    TextView activityInformationalTv;
    @BindView(R.id.activity_coming_soon_tv)
    TextView activityComingSoonTv;
    List<StaticData> staticData;
    SweetAlertDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
        setContentView(R.layout.activity_informational);
        ButterKnife.bind(this);
        pDialog = new SweetAlertDialog(getApplicationContext(), SweetAlertDialog.PROGRESS_TYPE);
        if (getIntent().getExtras() != null) {
            String category = getIntent().getExtras().getString("category");
            String subcategory = getIntent().getExtras().getString("subcategory");
            int subcategoryat = getIntent().getExtras().getInt("subcategoryat");
            actionBar.setTitle(subcategory);
            populateDataToView(category, subcategory, subcategoryat);
        }

    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    public void fetchDataFromServer(String name) {
        Api.getClient().getStaticData(name, new Callback<StaticDataResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void success(StaticDataResponse staticDataResponse, Response response) {
                staticData = staticDataResponse.getStaticDataResponses();
                activityInformationalTv.setText(staticData.get(0).getDescription());
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    activityInformationalTv.setText(Html.fromHtml(staticData.get(0).getDescription(),Html.FROM_HTML_MODE_COMPACT));
                    activityInformationalTv.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
                } else {
                    activityInformationalTv.setText(Html.fromHtml(staticData.get(0).getDescription()));
                }
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }

    public void populateDataToView(String category, String subcategory, int subcategoryat) {
        if (category.equalsIgnoreCase("about")) {
            switch (subcategoryat) {
                case 0:
                    fetchDataFromServer("about_about_us");
                    break;
                case 1:
                    fetchDataFromServer("about_mission_vision");
                    break;
            }
        } else if (category.equalsIgnoreCase("sectors")) {
            switch (subcategoryat) {
                case 0:
                    fetchDataFromServer("sectors_foreign");
                    break;
                case 1:
                    fetchDataFromServer("sectors_agriculture");
                    break;
                case 2:
                    fetchDataFromServer("sectors_Finance");
                    break;
                case 3:
                    fetchDataFromServer("sectors_Legal");
                    break;
                case 4:
                    fetchDataFromServer("sectors_Arts");
                    break;
                case 5:
                    fetchDataFromServer("sectors_Education");
                    break;
                case 6:
                    fetchDataFromServer("sectors_Metals");
                    break;
                case 7:
                    fetchDataFromServer("sectors_IT");
                    break;
                case 8:
                    fetchDataFromServer("sectors_Sports");
                    break;
                case 9:
                    fetchDataFromServer("sectors_Health");
                    break;
                case 10:
                    fetchDataFromServer("sectors_Defense");
                    break;
                case 11:
                    fetchDataFromServer("sectors_Media");
                    break;
            }
        } else if (category.equalsIgnoreCase("services")) {
            switch (subcategoryat) {
                case 0:
                    fetchDataFromServer("services_Research");
                    break;
                case 1:
                    fetchDataFromServer("services_Arbitration");
                    break;
                case 2:
                    fetchDataFromServer("services_B2B");
                    break;
                case 3:
                    fetchDataFromServer("services_CSR");
                    break;
                case 4:
                    fetchDataFromServer("services_Business");
                    break;
                case 5:
                    fetchDataFromServer("services_Workshop");
                    break;
                case 6:
                    fetchDataFromServer("services_Networking");
                    break;
                case 7:
                    fetchDataFromServer("services_Program");
                    break;
                case 8:
                    fetchDataFromServer("services_Skills");
                    break;
                case 9:
                    fetchDataFromServer("services_Women");
                    break;
            }
        }  else if (category.equalsIgnoreCase("international")) {
            switch (subcategoryat) {
                case 0:
                    activityInformationalTv.setVisibility(View.GONE);
                    activityComingSoonTv.setVisibility(View.VISIBLE);
                    fetchDataFromServer("international_asia");
                    break;
                case 1:
                    activityInformationalTv.setVisibility(View.GONE);
                    activityComingSoonTv.setVisibility(View.VISIBLE);
                    fetchDataFromServer("international_africa");
                    break;
                case 2:
                    activityInformationalTv.setVisibility(View.GONE);
                    activityComingSoonTv.setVisibility(View.VISIBLE);
                    fetchDataFromServer("international_australia");
                    break;
                case 3:
                    activityInformationalTv.setVisibility(View.GONE);
                    activityComingSoonTv.setVisibility(View.VISIBLE);
                    fetchDataFromServer("international_europe");
                    break;
                case 4:
                    activityInformationalTv.setVisibility(View.GONE);
                    activityComingSoonTv.setVisibility(View.VISIBLE);
                    fetchDataFromServer("international_middle_east");
                    break;
                case 5:
                    activityInformationalTv.setVisibility(View.GONE);
                    activityComingSoonTv.setVisibility(View.VISIBLE);
                    fetchDataFromServer("international_north_america");
                    break;
                case 6:
                    activityInformationalTv.setVisibility(View.GONE);
                    activityComingSoonTv.setVisibility(View.VISIBLE);
                    fetchDataFromServer("international_south_america");
                    break;
            }
        }

    }

}