package com.example.listnewsapp.parseData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


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

    public String getUrlNextPage() {
        return urlNextPage;
    }

    public String getPreviousPage() {
        return previousPage;
    }

    public NewsObject[] getResults() {
        return results;
    }
}
