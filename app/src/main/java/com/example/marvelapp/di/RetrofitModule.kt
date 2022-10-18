package com.example.marvelapp.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object RetrofitModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/v1/public/characters/1011334/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}