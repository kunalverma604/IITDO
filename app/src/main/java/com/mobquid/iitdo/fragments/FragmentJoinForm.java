package com.mobquid.iitdo.fragments;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.models.SuccessResponse;
import com.mobquid.iitdo.retrofit.Api;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FragmentJoinForm extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.member_country_spinner)
    Spinner memberCountrySpinner;
    @BindView(R.id.member_category_spinner)
    Spinner memberCategorySpinner;

    @BindView(R.id.member_name)
    TextInputEditText memberName;
    @BindView(R.id.member_designation)
    TextInputEditText memberDesignation;
    @BindView(R.id.member_address)
    TextInputEditText memberAddress;
    @BindView(R.id.member_mobile)
    TextInputEditText memberMobile;
    @BindView(R.id.member_email)   
    TextInputEditText memberEmail;
    @BindView(R.id.member_business)
    TextInputEditText memberBusiness;
    @BindView(R.id.sign_in_button3)
    LinearLayout sign_in_button3;

    public FragmentJoinForm() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_join_form, container, false);
        unbinder = ButterKnife.bind(this, view);

        populateCountrySpinner();

        sign_in_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDataToServer();
            }
        });
        return view;
    }

    public void saveDataToServer() {
        Api.getClient().saveNewMember(memberName.getText().toString(), memberDesignation.getText().toString(),
                memberAddress.getText().toString(), memberMobile.getText().toString(),
                memberEmail.getText().toString(), memberBusiness.getText().toString(),
                memberCountrySpinner.getSelectedItem().toString(), memberCategorySpinner.getSelectedItem().toString(),
                new Callback<SuccessResponse>() {
                    @Override
                    public void success(SuccessResponse successResponse, Response response) {
                        showDialog();
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
    }

    public void populateCountrySpinner() {
        List<String> spinnerCountryArray =  new ArrayList<String>();
        spinnerCountryArray.add("India");
        spinnerCountryArray.add("Others");

        List<String> spinnerCategoryIndia = new ArrayList<>();
        spinnerCategoryIndia.add("Diamond (1,00,00,000 INR)");
        spinnerCategoryIndia.add("Platinum (50,00,000 INR)");
        spinnerCategoryIndia.add("Gold (5,00,000 INR)");
        spinnerCategoryIndia.add("Corporate(1,00,000 INR)");
        spinnerCategoryIndia.add("Business (50,000 INR)");
        spinnerCategoryIndia.add("Ordinary (25,000 INR)");
        spinnerCategoryIndia.add("Student (1,000 INR)");

        List<String> spinnerCategoryOthers = new ArrayList<>();
        spinnerCategoryOthers.add("Diamond (2,00,000 USD)");
        spinnerCategoryOthers.add("Platinum (1,00,000 USD)");
        spinnerCategoryOthers.add("Gold (20,000 USD)");
        spinnerCategoryOthers.add("Corporate (2,000 USD)");
        spinnerCategoryOthers.add("Business (1,000 USD)");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
              getContext(), android.R.layout.simple_spinner_item, spinnerCountryArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        memberCountrySpinner.setAdapter(adapter);

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, spinnerCategoryIndia);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> adapter2 = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, spinnerCategoryOthers);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        memberCountrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                if(position == 0){
                    memberCategorySpinner.setAdapter(adapter1);
                } else if (position == 1){
                    memberCategorySpinner.setAdapter(adapter2);
                }

            }

            public void onNothingSelected(AdapterView<?> arg0) {// do nothing
            }

        });

//        if (memberCountrySpinner.getSelectedItem().toString().equalsIgnoreCase("India")) {
//              ArrayAdapter<String> adapter1 = new ArrayAdapter<>(
//                      getContext(), android.R.layout.simple_spinner_item, spinnerCategoryIndia);
//              adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//              memberCategorySpinner.setAdapter(adapter1);
//        } else if (memberCountrySpinner.getSelectedItem().toString().equalsIgnoreCase("Others")) {
//              ArrayAdapter<String> adapter2 = new ArrayAdapter<>(
//                      getContext(), android.R.layout.simple_spinner_item, spinnerCategoryOthers);
//              adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//              memberCountrySpinner.setAdapter(adapter2);
//        }

    }


    protected void showDialog(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());
        alertDialogBuilder.setTitle("Data Saved");
        alertDialogBuilder
                .setMessage("Your details have been saved and we will get in touch with you soon with the next steps")
                .setCancelable(false)
                .setPositiveButton("OKAY",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    };

}
