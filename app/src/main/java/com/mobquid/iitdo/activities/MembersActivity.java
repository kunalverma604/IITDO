package com.mobquid.iitdo.activities;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.adapters.MembersRecyclerViewAdapter;
import com.mobquid.iitdo.models.BoardMembersResponse;
import com.mobquid.iitdo.models.Members;
import com.mobquid.iitdo.retrofit.Api;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MembersActivity extends AppCompatActivity {

    @BindView(R.id.members_rv)
    RecyclerView membersRv;
    List<Members> membersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
        setContentView(R.layout.activity_members);
        ButterKnife.bind(this);

        String cateogry = getIntent().getExtras().getString("category");
        String subcateogry = getIntent().getExtras().getString("subcategory");
        int subcategoryat = getIntent().getExtras().getInt("subcategoryat");
        actionBar.setTitle(subcateogry);

        getAllMembers(subcateogry);
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    private void getAllMembers(String member_type) {
        Api.getClient().getBoardMembers(member_type, new Callback<BoardMembersResponse>() {
            @Override
            public void success(BoardMembersResponse boardMembersResponse, Response response) {
                membersList = boardMembersResponse.getBoardMembersResponses();
                MembersRecyclerViewAdapter recyclerViewAdapter = new MembersRecyclerViewAdapter(getApplicationContext(), membersList);
                membersRv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                membersRv.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
