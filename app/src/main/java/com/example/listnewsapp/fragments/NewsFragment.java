package com.example.listnewsapp.fragments;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import java.util.List;

public class NewsFragment extends Fragment implements INewsConnection {

    private NewsRecyclerAdapter mAdapter;
    private NewsPresenter mPresenter;
    private RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        mPresenter = NewsPresenter.getInstance(this, this);
        mAdapter = new NewsRecyclerAdapter();

        mPresenter.getNewsList(NewsApplication.getInstance().isOnline());

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCurrentNews(List<NewsData> newsDataList) {
        List<NewsData> galleryList = new ArrayList<>();
        mAdapter.setNewsList(newsDataList, false);
        for (NewsData newsData : newsDataList){
            if (newsData.isNeedAddToGallery()){
                galleryList.add(newsData);
            }
        }
        mAdapter.setGalleryItems(galleryList);
    }
}
