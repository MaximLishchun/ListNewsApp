package com.example.listnewsapp.adapters;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.listnewsapp.NewsApplication;
import com.example.listnewsapp.R;
import com.example.listnewsapp.usingData.NewsData;
import com.example.listnewsapp.view.ImageGalleryItem;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final int IMAGE_GALLERY = 1;
    private final int NEWS_ITEM = 2;

    private List<NewsData> newsList;
    private List<NewsData> galleryItems;

    public void setNewsList(List<NewsData> newsList, boolean needCheckFavorite){
        if (this.newsList == null)
            this.newsList = new ArrayList<>();
        if (needCheckFavorite){
            for (NewsData newsData: newsList)
                if (newsData.isFavorite())
                    this.newsList.add(newsData);
        }else {
            this.newsList.addAll(newsList);
        }
        if (this.newsList.size() > 0)
            removeDuplicates(this.newsList);
        if (this.newsList != null)
            Log.i("NewsAdapter", "this.newsList " + this.newsList.size());
        this.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setGalleryItems(List<NewsData>  galleryItems){
        if (this.galleryItems == null)
            this.galleryItems = new ArrayList<>();
        this.galleryItems.addAll(galleryItems);
        if (this.galleryItems.size() > 0)
            removeDuplicates(this.galleryItems);

        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("NewsAdapter", "onCreateViewHolder " + viewType);
        if (viewType == IMAGE_GALLERY)
            return new GalleryViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.image_gallery, parent, false), parent.getContext());
        else return new NewsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.i("NewsAdapter", "onBindViewHolder");
        if (getItemViewType(position) == NEWS_ITEM){
            Log.i("NewsAdapter", "onBindViewHolder NEWS_ITEM");
            NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
            NewsData newsData = newsList.get(position);
            String mainImageUrl = newsData.getImageUrl();
            if (mainImageUrl != null && !mainImageUrl.isEmpty()) {
                newsViewHolder.newsImageView.setImageDrawable(null);
                Picasso.get().load(mainImageUrl).into(newsViewHolder.newsImageView);
                newsViewHolder.newsImageView.setVisibility(View.VISIBLE);
            }
            newsViewHolder.tvNewsTitle.setText(newsData.getTitle());
            newsViewHolder.tvNewsTime.setText(NewsApplication.getInstance().convertTime(newsData.getLoadTime()));
        }else if (getItemViewType(position) == IMAGE_GALLERY){
            Log.i("NewsAdapter", "onBindViewHolder IMAGE_GALLERY");
            GalleryViewHolder galleryViewHolder = (GalleryViewHolder) holder;
            List<ImageGalleryItem> imageGalleryItems = new ArrayList<>();
            for (NewsData newsData: galleryItems){
                imageGalleryItems.add(new ImageGalleryItem(NewsApplication.getInstance().getAppContext(), newsData));
            }
            galleryViewHolder.mImageViewPager.setAdapter(new GalleryPageAdapter(imageGalleryItems));
            galleryViewHolder.tabLayout.setupWithViewPager(galleryViewHolder.mImageViewPager, true);
        }
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (galleryItems != null)
            size += 1;
        if (newsList != null)
            size += newsList.size();
        Log.i("NewsAdapter", "getItemCount size " + size);
        return size;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 && galleryItems.size() > 0 ? IMAGE_GALLERY : NEWS_ITEM;
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{

        ImageView newsImageView;
        TextView tvNewsTitle;
        TextView tvNewsTime;

        NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            newsImageView = itemView.findViewById(R.id.news_image);
            tvNewsTitle = itemView.findViewById(R.id.news_title);
            tvNewsTime = itemView.findViewById(R.id.news_time);
        }
    }

    class GalleryViewHolder extends RecyclerView.ViewHolder{
        ViewPager mImageViewPager;
        Context parentContext;
        TabLayout tabLayout;

        public GalleryViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            mImageViewPager = (ViewPager) itemView.findViewById(R.id.pager);
            tabLayout = (TabLayout) itemView.findViewById(R.id.tabDots);

            parentContext = context;
        }
    }

    // Function to remove duplicates from an ArrayList
    private void removeDuplicates(List<NewsData> list)
    {
        Set<NewsData> set = new LinkedHashSet<>(list);
        list.clear();
        list.addAll(set);
    }
}