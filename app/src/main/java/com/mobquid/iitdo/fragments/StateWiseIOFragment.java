package com.mobquid.iitdo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.adapters.InvOppRecyclerViewAdapter;
import com.mobquid.iitdo.adapters.MajorItemsRecyclerViewAdapter;
import com.mobquid.iitdo.models.IOResponse;
import com.mobquid.iitdo.models.Investment;
import com.mobquid.iitdo.models.InvestmentOpportunities;
import com.mobquid.iitdo.retrofit.Api;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class StateWiseIOFragment extends Fragment {

    Unbinder unbinder;
    List<Investment> invOppList = new ArrayList<>();
    @BindView(R.id.investment_opportunities_rv)
    RecyclerView investmentOpportunitiesRv;

    public StateWiseIOFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_state_wise_io, container, false);
        unbinder = ButterKnife.bind(this, view);
        fetchioFromServer();
        return view;
    }

    public void fetchioFromServer() {
        Api.getClient().getIO("State Wise", new Callback<IOResponse>() {
            @Override
            public void success(IOResponse ioResponse, Response response) {
                invOppList = ioResponse.getInvestmentList();
                InvOppRecyclerViewAdapter invOppRecyclerViewAdapter = new InvOppRecyclerViewAdapter(getContext(), invOppList);
                investmentOpportunitiesRv.setLayoutManager(new GridLayoutManager(getContext(), 3));
                investmentOpportunitiesRv.setAdapter(invOppRecyclerViewAdapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

}
