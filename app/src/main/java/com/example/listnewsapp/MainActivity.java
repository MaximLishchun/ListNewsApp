package com.example.listnewsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.listnewsapp.adapters.MainFragmentPagerAdapter;
import com.example.listnewsapp.fragments.NewsFavoriteFragment;
import com.example.listnewsapp.fragments.NewsFragment;
import com.example.listnewsapp.fragments.NewsVideoFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new NewsFragment(), getResources().getString(R.string.toolbar_title));
        adapter.addFragment(new NewsVideoFragment(), getResources().getString(R.string.videos));
        adapter.addFragment(new NewsFavoriteFragment(), getResources().getString(R.string.favorite));
        viewPager.setOffscreenPageLimit(adapter.getCount());
        viewPager.setAdapter(adapter);
    }
}
