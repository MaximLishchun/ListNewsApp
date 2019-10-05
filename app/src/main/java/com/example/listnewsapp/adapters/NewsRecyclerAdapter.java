package com.example.listnewsapp.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listnewsapp.NewsApplication;
import com.example.listnewsapp.R;
import com.example.listnewsapp.usingData.NewsData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder>{

    private List<NewsData> newsList;

    public void setNewsList(List<NewsData> newsList, boolean needCheckFavorite){
        if (this.newsList == null)
            this.newsList = new ArrayList<>();
        if (needCheckFavorite){
            for (NewsData newsData: newsList)
                if (newsData.isFavorite())
                    this.newsList.add(newsData);
        }else {
            this.newsList.addAll(newsList);
        }
        notifyDataSetChanged();
    }

    public void clear(){
        if (newsList != null)
            newsList.clear();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        String mainImageUrl = newsList.get(position).getImageUrl();
        if (mainImageUrl != null && !mainImageUrl.isEmpty()) {
            holder.newsImageView.setImageDrawable(null);
            Picasso.get().load(mainImageUrl).into(holder.newsImageView);
            holder.newsImageView.setVisibility(View.VISIBLE);
        }
        holder.tvNewsTitle.setText(newsList.get(position).getTitle());
        holder.tvNewsTime.setText(NewsApplication.getInstance().convertTime(newsList.get(position).getLoadTime()));
    }

    @Override
    public int getItemCount() {
        if (newsList != null)
            return newsList.size();
        else return 0;
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{

        ImageView newsImageView;
        TextView tvNewsTitle;
        TextView tvNewsTime;

        NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            newsImageView = itemView.findViewById(R.id.news_image);
            tvNewsTitle = itemView.findViewById(R.id.news_title);
            tvNewsTime = itemView.findViewById(R.id.news_time);
        }
    }
}