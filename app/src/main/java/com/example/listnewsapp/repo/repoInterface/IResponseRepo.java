package com.example.listnewsapp.repo.repoInterface;

import com.example.listnewsapp.parseData.NewsObject;

public interface IResponseRepo {

    interface ListNewsCallback {
        void onSuccess(NewsObject[] news);
    }

    interface ErrorCallback{
        void onError();
    }

    void getListNews(String page, ListNewsCallback newsCallback, ErrorCallback errorCallback);
    void getNewsFirstPage(ListNewsCallback newsCallback, ErrorCallback errorCallback);
}
