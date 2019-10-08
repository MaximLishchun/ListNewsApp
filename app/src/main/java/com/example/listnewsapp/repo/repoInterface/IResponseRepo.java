package com.example.listnewsapp.repo.repoInterface;

import com.example.listnewsapp.usingData.NewsData;

import java.util.List;

public interface IResponseRepo {

    interface ListNewsCallback {
        void onSuccess(List<NewsData> news, boolean isLoading);
    }

    interface ErrorCallback{
        void onError();
    }

    void getAllNews(boolean needUpdate, ListNewsCallback newsCallback, ErrorCallback errorCallback);
}
