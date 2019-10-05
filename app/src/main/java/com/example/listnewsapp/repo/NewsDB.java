package com.example.listnewsapp.repo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.listnewsapp.repo.repoInterface.NewsDataDao;
import com.example.listnewsapp.usingData.NewsData;

@Database(entities = {NewsData.class}, version = 1)
public abstract class NewsDB extends RoomDatabase {
    public abstract NewsDataDao getNewsDataDao();
}
