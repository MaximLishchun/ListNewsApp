package com.example.listnewsapp.parseData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrencyObject {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("image")
    @Expose
    private String image;

}
