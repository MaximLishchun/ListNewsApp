package com.example.listnewsapp.presenters;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.listnewsapp.repo.ResponseRepo;
import com.example.listnewsapp.usingData.NewsData;

import java.util.Comparator;

public class NewsPresenter{

    private INewsConnection mNewsConnection;
    private static NewsPresenter mNewsController;
    private ResponseRepo mResponseRepo;

    public static NewsPresenter getInstance(INewsConnection connection, Fragment context){
        if (mNewsController == null)
            mNewsController = new NewsPresenter(connection, context);
        return mNewsController;
    }

    private NewsPresenter(INewsConnection connection, Fragment context){
        this.mNewsConnection = connection;
        this.mResponseRepo = ResponseRepo.getInstance(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getNewsList(boolean needUpdate) {
        mResponseRepo.getAllNews(needUpdate, news -> {
            news.sort(Comparator.comparing(NewsData::getId));
            mNewsConnection.onCurrentNews(news);
        }, () -> {

        });
    }

}
