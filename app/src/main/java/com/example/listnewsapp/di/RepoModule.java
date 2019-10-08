package com.example.listnewsapp.di;


import com.example.listnewsapp.repo.ResponseRepo;
import com.example.listnewsapp.repo.ResponseRepoApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepoModule {

    @Provides
    @Singleton
    public ResponseRepo provideResponseRepo(){
        return new ResponseRepo();
    }

    @Provides
    @Singleton
    public ResponseRepoApi provideResponseRepoApi(){
        return new ResponseRepoApi();
    }
}
