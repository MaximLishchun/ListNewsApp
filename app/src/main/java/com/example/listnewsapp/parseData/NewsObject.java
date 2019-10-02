package com.example.listnewsapp.parseData;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsObject {

    @SerializedName("id")
    @Expose
    private int newId;

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

    @Override
    public String toString() {
        return "NewsObject{" +
                "newId=" + newId +
                ", name='" + name + '\'' +
                ", image=" + image +
                ", price=" + price +
                ", priceWeek=" + priceWeek +
                ", currency=" + currency +
                ", view_count=" + view_count +
                ", favorite=" + favorite +
                ", emailCount=" + emailCount +
                ", phoneCount=" + phoneCount +
                ", owner=" + owner +
                ", category=" + category +
                '}';
    }
}
