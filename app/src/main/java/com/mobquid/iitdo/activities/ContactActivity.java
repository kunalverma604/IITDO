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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.models.SuccessResponse;
import com.mobquid.iitdo.retrofit.Api;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ContactActivity extends AppCompatActivity {

    @BindView(R.id.sign_in_button1)
    LinearLayout signInButton;
    @BindView(R.id.msg_tv)
    TextInputEditText msgTv;
    @BindView(R.id.contact_tv)
    TextInputEditText contactTv;
    @BindView(R.id.email_tv)
    TextInputEditText emailTv;
    @BindView(R.id.name_tv)
    TextInputEditText nameTv;
    String name, email, contact, msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Contact Us");
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameTv.getText().toString();
                email = emailTv.getText().toString();
                contact = contactTv.getText().toString();
                msg = msgTv.getText().toString();

                if (nameTv.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(ContactActivity.this, "Your name is required", Toast.LENGTH_SHORT).show();
                } else if (emailTv.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(ContactActivity.this, "Your email is required", Toast.LENGTH_SHORT).show();
                } else if (contactTv.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(ContactActivity.this, "Your contact is required", Toast.LENGTH_SHORT).show();
                } else if (msgTv.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(ContactActivity.this, "Message is required", Toast.LENGTH_SHORT).show();
                } else {
                    saveDatainDb(name, email, contact, msg);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    public void saveDatainDb(String name, String email, String contact, String msg) {
        Api.getClient().saveContact(name, email, contact, msg, "app", new Callback<SuccessResponse>() {
            @Override
            public void success(SuccessResponse successResponse, Response response) {
                Toast.makeText(ContactActivity.this, "Your message has been sent!", Toast.LENGTH_LONG).show();
                nameTv.setText("");
                emailTv.setText("");
                contactTv.setText("");
                msgTv.setText("");
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ContactActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                Log.e("apiconatc", error.toString());
            }
        });

    }
}

