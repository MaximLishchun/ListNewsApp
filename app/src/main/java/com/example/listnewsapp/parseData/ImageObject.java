package com.example.listnewsapp.parseData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageObject {

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("width")
    @Expose
    private int width;

    @SerializedName("height")
    @Expose
    private int height;

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
