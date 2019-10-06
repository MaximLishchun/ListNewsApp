package com.example.listnewsapp;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.format.DateUtils;
import android.util.Log;

import androidx.room.Room;

import com.example.listnewsapp.repo.NewsDB;

import java.util.Date;

public class NewsApplication extends Application {
    public static NewsApplication instance;

    private NewsDB mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mDatabase = Room.databaseBuilder(this, NewsDB.class, "news_database")
                .build();
    }

    public static NewsApplication getInstance() {
        return instance;
    }

    public NewsDB getDatabase() {
        return mDatabase;
    }

    //check internet connection
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    //convert time to "time ago"
    public String convertTime(long timestamp){
        long now = new Date().getTime();
        Log.i("CheckDate", "timestamp --- " + timestamp + " now --- " + now);
        return String.valueOf(DateUtils.getRelativeTimeSpanString(timestamp, now, DateUtils.MINUTE_IN_MILLIS));
    }

    public Context getAppContext(){
        return getApplicationContext();
    }
}
