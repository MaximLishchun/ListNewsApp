package com.example.listnewsapp.fragments;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    private List<NewsData> mNewsDataList;
    private ProgressBar progressBar;
    private TextView tvError;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        mPresenter = NewsPresenter.getInstance(this);
        mAdapter = new NewsRecyclerAdapter();
        mPresenter.getNewsList(NewsApplication.getInstance().isOnline());

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        progressBar = view.findViewById(R.id.progress);
        tvError = view.findViewById(R.id.tv_error);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.setNewsConnectionListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCurrentNews(List<NewsData> newsDataList, boolean isLoading) {
        if (mNewsDataList == null) {
            mNewsDataList = new ArrayList<>();
        }
        mNewsDataList.addAll(newsDataList);
        if (!isLoading) {
            progressBar.setVisibility(View.GONE);
            List<NewsData> galleryList = new ArrayList<>();
            mAdapter.setNewsList(mNewsDataList);
            for (NewsData newsData : mNewsDataList) {
                if (newsData.isNeedAddToGallery()) {
                    galleryList.add(newsData);
                }
            }
            mAdapter.setGalleryItems(galleryList);
        }
    }

    @Override
    public void onError() {
        recyclerView.setVisibility(View.GONE);
        tvError.setVisibility(View.VISIBLE);
    }
}
