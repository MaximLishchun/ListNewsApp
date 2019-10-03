package com.example.listnewsapp.repo;

import com.example.listnewsapp.api.ApiService;
import com.example.listnewsapp.parseData.ResultsApi;
import com.example.listnewsapp.repo.repoInterface.IResponseRepo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResponseRepo implements IResponseRepo {

    private static ResponseRepo mResponseRepo;

    public static ResponseRepo getInstance(){
        if (mResponseRepo == null)
            mResponseRepo = new ResponseRepo();
        return mResponseRepo;
    }

    @Override
    public void getListNews(String page, ListNewsCallback newsCallback, ErrorCallback errorCallback) {
    }

    @Override
    public void getNewsFirstPage(final ListNewsCallback newsCallback, final ErrorCallback errorCallback) {
        ApiService.getInstance().getNewsResponce().getResponce().enqueue(new Callback<ResultsApi>() {
            @Override
            public void onResponse(Call<ResultsApi> call, Response<ResultsApi> response) {
                if(response.isSuccessful()){
                    if (response.body() != null){
                        ResultsApi resultsApi = response.body();
                        if (resultsApi.getResults() != null){
                            newsCallback.onSuccess(resultsApi.getResults());
                        }else {
                            errorCallback.onError();
                            call.cancel();
                        }
                    }else {
                        errorCallback.onError();
                        call.cancel();
                    }
                }else {
                    errorCallback.onError();
                    call.cancel();
                }
            }

            @Override
            public void onFailure(Call<ResultsApi> call, Throwable t) {

            }
        });
    }
}
