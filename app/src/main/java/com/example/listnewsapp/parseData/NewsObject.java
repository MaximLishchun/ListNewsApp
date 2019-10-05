package com.example.listnewsapp.parseData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsObject {

    @SerializedName("id")
    @Expose
    private int newsId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("image")
    @Expose
    private ImageObject image;

    @SerializedName("price")
    @Expose
    private int price;

    @SerializedName("price_week")
    @Expose
    private int priceWeek;

    @SerializedName("currency")
    @Expose
    private CurrencyObject currency;

    @SerializedName("view_count")
    @Expose
    private int view_count;

    @SerializedName("favorite")
    @Expose
    private boolean favorite;

    @SerializedName("email_count")
    @Expose
    private int emailCount;

    @SerializedName("phone_count")
    @Expose
    private int phoneCount;

    @SerializedName("owner")
    @Expose
    private boolean owner;

    @SerializedName("category")
    @Expose
    private int category;

    public int getNewsId() {
        return newsId;
    }

    public String getName() {
        return name;
    }

    public ImageObject getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

    public int getPriceWeek() {
        return priceWeek;
    }

    public CurrencyObject getCurrency() {
        return currency;
    }

    public int getView_count() {
        return view_count;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public int getEmailCount() {
        return emailCount;
    }

    public int getPhoneCount() {
        return phoneCount;
    }

    public boolean isOwner() {
        return owner;
    }

    public int getCategory() {
        return category;
    }
}
