package com.example.listnewsapp.repo.repoInterface;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.listnewsapp.usingData.NewsData;

import java.util.List;

@Dao
public interface NewsDataDao {

    @Query("SELECT * FROM NewsData")
    LiveData<List<NewsData>> getListNewsData();

    @Query("SELECT * FROM NewsData WHERE id = :id")
    LiveData<NewsData> getNewsDataById(long id);

    @Insert
    void insert(NewsData employee);

    @Update
    void update(NewsData employee);

    @Delete
    void delete(NewsData employee);

    @Query("SELECT * FROM NewsData LIMIT 1")
    NewsData getAnyNews();

}
