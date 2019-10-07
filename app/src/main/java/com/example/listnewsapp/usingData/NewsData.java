package com.example.listnewsapp.usingData;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NewsData {

    @PrimaryKey
    private int id;

    private String title;
    private String imageUrl;
    private long loadTime;
    private boolean favorite;
    private boolean needAddToGallery;

    public NewsData(int id, String title, String imageUrl, long loadTime, boolean favorite, boolean needAddToGallery) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.loadTime = loadTime;
        this.favorite = favorite;
        this.needAddToGallery = needAddToGallery;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public long getLoadTime() {
        return loadTime;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public boolean isNeedAddToGallery(){
        return needAddToGallery;
    }

    @Override
    public int hashCode() {
        return id*title.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof NewsData)) {
            return false;
        }
        NewsData newsData = (NewsData) obj;
        return id == newsData.id && newsData.title.equals(title);
    }
}
