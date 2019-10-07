package com.example.listnewsapp.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.listnewsapp.view.ImageGalleryItem;

import java.util.List;

public class GalleryPageAdapter extends PagerAdapter {

    private List<ImageGalleryItem> imageGalleryItems;

    public GalleryPageAdapter(List<ImageGalleryItem> imageGalleryItems) {
        this.imageGalleryItems = imageGalleryItems;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        collection.addView(imageGalleryItems.get(position));
        return imageGalleryItems.get(position);
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return imageGalleryItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
