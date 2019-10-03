package com.example.listnewsapp.controllers;

public class NewsController {

    private NewsController mNewsController;

    private NewsController getInstance(){
        if (mNewsController == null)
            mNewsController = new NewsController();
        return mNewsController;
    }


}
