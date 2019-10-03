package com.example.listnewsapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listnewsapp.parseData.NewsObject;
import com.example.listnewsapp.repo.ResponseRepo;
import com.example.listnewsapp.repo.repoInterface.IResponseRepo;
import com.squareup.picasso.Picasso;

import org.apache.commons.text.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final NewsRecyclerAdapter adapter = new NewsRecyclerAdapter();
        ResponseRepo.getInstance().getNewsFirstPage(new IResponseRepo.ListNewsCallback() {
            @Override
            public void onSuccess(NewsObject[] data) {
                adapter.setNewsList(data);
            }
        }, new IResponseRepo.ErrorCallback() {
            @Override
            public void onError() {

            }
        });
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder>{

        private NewsObject[] newsList;

        public void setNewsList(NewsObject[] newsList){
            this.newsList = newsList;
            notifyDataSetChanged();
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
            Picasso.get().load(newsList[position].getImage().getUrl()).into(holder.newsImageView);
            holder.tvNewsTitle.setText(newsList[position].getName());
            holder.tvNewsPrice.setText(Html.escapeHtml(StringEscapeUtils.unescapeHtml4(newsList[position].getCurrency().getName()).replaceAll("[^\\x20-\\x7e]", "")));
            Picasso.get().load(newsList[position].getCurrency().getImage()).into(holder.priceImageView);
        }

        @Override
        public int getItemCount() {
            if (newsList != null)
                return newsList.length;
            else return 0;
        }

        class NewsViewHolder extends RecyclerView.ViewHolder{

            ImageView newsImageView;
            TextView tvNewsTitle;
            TextView tvNewsPrice;
            ImageView priceImageView;

            NewsViewHolder(@NonNull View itemView) {
                super(itemView);
                newsImageView = itemView.findViewById(R.id.news_image);
                tvNewsTitle = itemView.findViewById(R.id.news_title);
                tvNewsPrice = itemView.findViewById(R.id.price);
                priceImageView = itemView.findViewById(R.id.image_price);
            }
        }
    }

}
