package com.example.listnewsapp.di;

import com.example.listnewsapp.presenters.NewsPresenter;
import com.example.listnewsapp.repo.ResponseRepo;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {RepoModule.class})
@Singleton
public interface AppComponent {
    void inject(ResponseRepo responseRepo);
    void inject(NewsPresenter newsPresenter);
}
