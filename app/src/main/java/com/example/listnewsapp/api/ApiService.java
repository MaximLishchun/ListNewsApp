package com.example.listnewsapp.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static ApiService mInstance;
    private Retrofit mRetrofit;

    private ApiService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(LinksApi.getMainUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
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
