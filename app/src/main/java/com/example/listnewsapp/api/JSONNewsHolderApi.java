package com.example.listnewsapp.api;

import com.example.listnewsapp.parseData.ResultsApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONNewsHolderApi {
    @GET("/api/v1.0/products/?cursor=cD0yMDE3LTEwLTA2KzEwJTNBMTUlM0E1NS4yMTY2NzAlMkIwMCUzQTAwJnI9MQ%3D%3D")
    Call<ResultsApi> getResponseFirsList();

    @GET("/api/v1.0/products/")
    Call<ResultsApi> getResponseListByKey(@Query("cursor") String pageKey);
}
