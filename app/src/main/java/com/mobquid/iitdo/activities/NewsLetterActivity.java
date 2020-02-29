package com.mobquid.iitdo.activities;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.models.News;
import com.mobquid.iitdo.models.SuccessResponse;
import com.mobquid.iitdo.retrofit.Api;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class NewsLetterActivity extends AppCompatActivity {

    @BindView(R.id.sign_in_button2)
    LinearLayout signInButton2;
    @BindView(R.id.newsletter_name)
    TextInputEditText newsletterName;
    @BindView(R.id.newsletter_email)
    TextInputEditText newsletterEmail;
    @BindView(R.id.newsletter_mobile)
    TextInputEditText newsletterMobile;
    @BindView(R.id.newsletter_business)
    TextInputEditText newsletterBusiness;
    String name, email, mobile, business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Newsletter");
        setContentView(R.layout.activity_news_letter);
        ButterKnife.bind(this);

        signInButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = newsletterName.getText().toString();
                email = newsletterEmail.getText().toString();
                mobile = newsletterMobile.getText().toString();
                business = newsletterBusiness.getText().toString();

                if (newsletterName.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(NewsLetterActivity.this, "Your name is required", Toast.LENGTH_SHORT).show();
                } else if (newsletterEmail.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(NewsLetterActivity.this, "Your email is required", Toast.LENGTH_SHORT).show();
                } else if (newsletterMobile.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(NewsLetterActivity.this, "Your mobile number is required", Toast.LENGTH_SHORT).show();
                } else if (newsletterBusiness.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(NewsLetterActivity.this, "Line of Business is required", Toast.LENGTH_SHORT).show();
                } else {
                    saveDatainDb(name, email, mobile, business);
                }
            }
        });
    }

    public void saveDatainDb(String name, String email, String mobile, String business) {
        Api.getClient().saveNewsletter(name, email, mobile, business, "app", new Callback<SuccessResponse>() {
            @Override
            public void success(SuccessResponse successResponse, Response response) {
                Toast.makeText(NewsLetterActivity.this, "You have been added to our subsribers list!", Toast.LENGTH_LONG).show();
                newsletterName.setText("");
                newsletterEmail.setText("");
                newsletterMobile.setText("");
                newsletterBusiness.setText("");
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(NewsLetterActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                Log.e("apinewsletter", error.toString());
            }
        });

    }



    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
