package com.example.listnewsapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listnewsapp.NewsApplication;
import com.example.listnewsapp.R;
import com.example.listnewsapp.adapters.NewsRecyclerAdapter;
import com.example.listnewsapp.presenters.INewsConnection;
import com.example.listnewsapp.presenters.NewsPresenter;
import com.example.listnewsapp.usingData.NewsData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NewsFragment extends Fragment implements INewsConnection {

    private NewsRecyclerAdapter mAdapter;
    private NewsPresenter mPresenter;
    private boolean isLoadingData;
    private Set<NewsData> galleryList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        mPresenter = NewsPresenter.getInstance(this, this);
        mAdapter = new NewsRecyclerAdapter();
        galleryList = new HashSet<>();

        mPresenter.getNewsList(NewsApplication.getInstance().isOnline());
        isLoadingData = true;

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        return recyclerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!isLoadingData)
            mPresenter.getNewsList(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        mAdapter.clear();
    }

    @Override
    public void onCurrentNews(List<NewsData> newsDataList) {
        List<NewsData> galleryList = new ArrayList<>();
        mAdapter.setNewsList(newsDataList, false);
        for (NewsData newsData : newsDataList){
            if (newsData.isNeedAddToGallery() && !galleryList.contains(newsData)){
                galleryList.add(newsData);
            }
        }
        mAdapter.setGalleryItems(galleryList);
        isLoadingData = false;
    }
}
