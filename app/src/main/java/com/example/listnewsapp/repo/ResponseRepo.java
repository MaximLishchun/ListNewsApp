package com.example.listnewsapp.repo;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.listnewsapp.NewsApplication;
import com.example.listnewsapp.repo.repoInterface.IResponseRepo;
import com.example.listnewsapp.usingData.NewsData;

import java.util.List;

import javax.inject.Inject;

public class ResponseRepo implements IResponseRepo {

    @Inject
    public ResponseRepoApi mRepoApi;
    private static Fragment mContext;

    public void setContext(Fragment context) {
        mContext = context;
    }

    public ResponseRepo(){
        NewsApplication.getInstance().getAppComponent().inject(this);
    }

    @Override
    public void getAllNews(boolean needUpdate, ListNewsCallback newsCallback, ErrorCallback errorCallback) {
        if (needUpdate){
            mRepoApi.getAllNews(needUpdate, newsCallback, errorCallback);
        }else {
            loadNewsFromDB(newsCallback, errorCallback);
        }
    }

    private LiveData<List<NewsData>> getListNewsData(){
        return NewsApplication.getInstance().getDatabase().getNewsDataDao().getListNewsData();
    }

    private void loadNewsFromDB(final ListNewsCallback newsCallback, final ErrorCallback errorCallback){
        if (mContext != null) {
            getListNewsData().observe(mContext, newsDataList -> {
                if (newsDataList != null) {
                    newsCallback.onSuccess(newsDataList, false);
                } else errorCallback.onError();
            });
        }
    }
}
