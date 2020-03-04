package com.mobquid.iitdo.retrofit;

import retrofit.RestAdapter;

public class Api {

    public static ApiInterface getClient() {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://iitdo.org/kunal/domains/")
                .build();

        ApiInterface api = adapter.create(ApiInterface.class);
        return api;
    }

}
