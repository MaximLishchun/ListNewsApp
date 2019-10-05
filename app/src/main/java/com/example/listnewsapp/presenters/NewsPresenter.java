package com.example.listnewsapp.presenters;

import androidx.fragment.app.Fragment;

import com.example.listnewsapp.repo.ResponseRepo;
import com.example.listnewsapp.repo.repoInterface.IResponseRepo;
import com.example.listnewsapp.usingData.NewsData;

import java.util.List;

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

    public void getNewsList(boolean needUpdate) {
        mResponseRepo.getAllNews(needUpdate, new IResponseRepo.ListNewsCallback() {
            @Override
            public void onSuccess(List<NewsData> news) {
                mNewsConnection.onCurrentNews(news);
            }
        }, new IResponseRepo.ErrorCallback() {
            @Override
            public void onError() {

            }
        });
    }

}