package com.example.listnewsapp.repo;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.listnewsapp.NewsApplication;
import com.example.listnewsapp.repo.repoInterface.IResponseRepo;
import com.example.listnewsapp.usingData.NewsData;

import java.util.List;

public class ResponseRepo implements IResponseRepo {

    private static ResponseRepo mInstance;
    private ResponseRepoApi mRepoApi;
    private Fragment mContext;

    public static ResponseRepo getInstance(Fragment context) {
        if(mInstance == null){
            mInstance = new ResponseRepo(context);
        }
        return mInstance;
    }

    private ResponseRepo(Fragment context){
        this.mContext = context;
        mRepoApi = ResponseRepoApi.getInstance();
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
        getListNewsData().observe(mContext, new Observer<List<NewsData>>() {
            @Override
            public void onChanged(List<NewsData> newsDataList) {
                if (newsDataList != null){
                    newsCallback.onSuccess(newsDataList);
                }else errorCallback.onError();
            }
        });
    }
}
