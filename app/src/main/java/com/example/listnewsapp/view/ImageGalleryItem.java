package com.example.listnewsapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.listnewsapp.NewsApplication;
import com.example.listnewsapp.R;
import com.example.listnewsapp.usingData.NewsData;
import com.squareup.picasso.Picasso;


public class ImageGalleryItem extends CardView {

    public ImageGalleryItem(Context context, NewsData newsData) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.gallery_item, this, true);
        view.setBackgroundColor(context.getResources().getColor(R.color.black_color));

        ImageView newsImageView = view.findViewById(R.id.news_image);
        TextView tvNewsTitle = view.findViewById(R.id.news_title);
        TextView tvNewsTime = view.findViewById(R.id.news_time);

        Picasso.get().load(newsData.getImageUrl()).into(newsImageView);
        tvNewsTitle.setText(newsData.getTitle());
        tvNewsTime.setText(NewsApplication.getInstance().convertTime(newsData.getLoadTime()));
    }

    public ImageGalleryItem(Context context) {
        super(context);
    }
    public ImageGalleryItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
