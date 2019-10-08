package com.example.listnewsapp.presenters;

import com.example.listnewsapp.usingData.NewsData;

import java.util.List;

public interface INewsConnection {
    void onCurrentNews(List<NewsData> newsDataList, boolean isLoading);
    void onError();
}
