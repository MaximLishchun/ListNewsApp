package com.example.listnewsapp.presenters;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.listnewsapp.NewsApplication;
import com.example.listnewsapp.repo.ResponseRepo;
import com.example.listnewsapp.usingData.NewsData;

import java.util.Comparator;

import javax.inject.Inject;

public class NewsPresenter{

    @Inject
    public ResponseRepo mResponseRepo;
    private INewsConnection mNewsConnection;
    private static NewsPresenter mNewsController;
    private static NewsPresenter mNewsControllerFavorite;


    public static NewsPresenter getInstance(Fragment context){
        if (mNewsController == null)
            mNewsController = new NewsPresenter(context);

        return mNewsController;
    }

    public static NewsPresenter getInstanceFavorite(Fragment context){
        if (mNewsControllerFavorite == null)
            mNewsControllerFavorite = new NewsPresenter(context);

        return mNewsControllerFavorite;
    }

    private NewsPresenter(Fragment context){
        NewsApplication.getInstance().getAppComponent().inject(this);
        mResponseRepo.setContext(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getNewsList(boolean needUpdate) {
        mResponseRepo.getAllNews(needUpdate, (news, isLoading) -> {
            news.sort(Comparator.comparing(NewsData::getId));
            mNewsConnection.onCurrentNews(news, isLoading);
        }, () -> mNewsConnection.onError());
    }

    public void setNewsConnectionListener(INewsConnection connection){
        mNewsConnection = connection;
    }

}
