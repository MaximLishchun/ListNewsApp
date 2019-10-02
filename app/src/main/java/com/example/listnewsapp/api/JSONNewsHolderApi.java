package com.example.listnewsapp.api;

import com.example.listnewsapp.parseData.ResultsApi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONNewsHolderApi {
    @GET("/api/v1.0/products/")
    public Call<ResultsApi> getResponce();
}
