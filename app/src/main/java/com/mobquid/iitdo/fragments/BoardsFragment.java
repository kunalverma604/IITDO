package com.mobquid.iitdo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.adapters.MembersRecyclerViewAdapter;
import com.mobquid.iitdo.models.BoardMembersResponse;
import com.mobquid.iitdo.models.Members;
import com.mobquid.iitdo.retrofit.Api;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class BoardsFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.members_rv)
    RecyclerView membersRv;
    List<Members> membersList = new ArrayList<>();

    public BoardsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_boards, container, false);
        unbinder = ButterKnife.bind(this, view);
        fetchBoardMembers();
        return view;
    }

    public void fetchBoardMembers() {
        Api.getClient().getBoardMembers("IITDO Boards", new Callback<BoardMembersResponse>() {
            @Override
            public void success(BoardMembersResponse boardMembersResponse, Response response) {
                membersList = boardMembersResponse.getBoardMembersResponses();
                MembersRecyclerViewAdapter recyclerViewAdapter = new MembersRecyclerViewAdapter(getContext(), membersList);
                membersRv.setLayoutManager(new GridLayoutManager(getContext(), 2));
                membersRv.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

}
