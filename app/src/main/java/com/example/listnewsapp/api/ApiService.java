package com.example.listnewsapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static ApiService mInstance;
    private Retrofit mRetrofit;

    private ApiService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(LinksApi.getMainUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService getInstance() {
        if (mInstance == null) {
            mInstance = new ApiService();
        }
        return mInstance;
    }

    public JSONNewsHolderApi getNewsResponce(){
        return mRetrofit.create(JSONNewsHolderApi.class);
    }
}
