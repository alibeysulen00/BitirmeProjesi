package com.example.bitirmeprojesi.di;

import com.example.bitirmeprojesi.data.repo.UrunlerDaoRepository;
import com.example.bitirmeprojesi.retrofit.ApiUtils;
import com.example.bitirmeprojesi.retrofit.UrunlerDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public UrunlerDaoRepository provideUrunlerDaoRepository(UrunlerDao udao){
        return new UrunlerDaoRepository(udao);
    }

    @Provides
    @Singleton
    public UrunlerDao providerUrunlerDao(){
        return ApiUtils.getUrunlerDao();
    }


}
