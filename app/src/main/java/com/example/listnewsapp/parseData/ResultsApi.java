package com.example.listnewsapp.parseData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class ResultsApi {
    @SerializedName("next")
    @Expose
    private String urlNextPage;

    @SerializedName("previous")
    @Expose
    private String previousPage;

    @SerializedName("results")
    @Expose
    private NewsObject[] results;

    @Override
    public String toString() {
        return "ResultsApi{" +
                "urlNextPage='" + urlNextPage + '\'' +
                ", previousPage='" + previousPage + '\'' +
                ", results=" + Arrays.toString(results) +
                '}';
    }
}
