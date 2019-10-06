package com.example.listnewsapp.repo;

import com.example.listnewsapp.NewsApplication;
import com.example.listnewsapp.api.ApiService;
import com.example.listnewsapp.parseData.NewsObject;
import com.example.listnewsapp.parseData.ResultsApi;
import com.example.listnewsapp.repo.repoInterface.IResponseRepo;
import com.example.listnewsapp.repo.repoInterface.NewsDataDao;
import com.example.listnewsapp.usingData.NewsData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResponseRepoApi implements IResponseRepo {

    private static ResponseRepoApi mResponseRepo;

    public static ResponseRepoApi getInstance() {
        if (mResponseRepo == null)
            mResponseRepo = new ResponseRepoApi();
        return mResponseRepo;
    }

    @Override
    public void getAllNews(boolean needUpdate, final ListNewsCallback newsCallback, final ErrorCallback errorCallback) {
        ApiService.getInstance().getNewsResponce().getResponseFirsList().enqueue(new Callback<ResultsApi>() {
            @Override
            public void onResponse(Call<ResultsApi> call, Response<ResultsApi> response) {
                parseDataFromApi(newsCallback, errorCallback, call, response);
            }

            @Override
            public void onFailure(Call<ResultsApi> call, Throwable t) {
                errorCallback.onError();
                call.cancel();
            }
        });
    }

    private void loadNextPages(String page, final ListNewsCallback newsCallback, final ErrorCallback errorCallback) {
        ApiService.getInstance().getNewsResponce().getResponseListByKey(page).enqueue(new Callback<ResultsApi>() {
            @Override
            public void onResponse(Call<ResultsApi> call, Response<ResultsApi> response) {
                parseDataFromApi(newsCallback, errorCallback, call, response);
            }

            @Override
            public void onFailure(Call<ResultsApi> call, Throwable t) {
                errorCallback.onError();
                call.cancel();
            }
        });
    }

    private void parseDataFromApi(final ListNewsCallback newsCallback, final ErrorCallback errorCallback, Call<ResultsApi> call, Response<ResultsApi> response) {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                ResultsApi resultsApi = response.body();
                if (resultsApi.getResults() != null) {
                    NewsObject[] newsObjects = resultsApi.getResults();
                    long tsLong = new Date().getTime();
//                    final String mydate =
//                            java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                    List<NewsData> newsDataList = new ArrayList<>();
                    int firstItem = 0;
                    //add data to DB
                    for (final NewsObject newsObject : newsObjects) {
                        boolean topNews = firstItem == 0;
                        final NewsData newsData = new NewsData(newsObject.getNewsId(), newsObject.getName(), newsObject.getImage().getUrl(), tsLong, newsObject.isFavorite(), topNews);
                        newsDataList.add(newsData);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                final NewsDataDao dataDao = NewsApplication.getInstance().getDatabase().getNewsDataDao();
                                if (dataDao.getAnyNews() == null)
                                    dataDao.insert(newsData);
                            }
                        }).start();
                        firstItem++;
                    }

                    newsCallback.onSuccess(newsDataList);
                    if (resultsApi.getUrlNextPage() != null) {
                        String page = parseParameterFromUrl(resultsApi.getUrlNextPage());
                        if (page != null)
                            loadNextPages(page, newsCallback, errorCallback);
                    }
                } else {
                    errorCallback.onError();
                    call.cancel();
                }
            } else {
                errorCallback.onError();
                call.cancel();
            }
        } else {
            errorCallback.onError();
            call.cancel();
        }
    }

    private String parseParameterFromUrl(String nextUrl) {
        final HttpUrl url = HttpUrl.parse(nextUrl);
        if (url != null) {
            return url.queryParameter("cursor");
        } else return null;
    }
}
