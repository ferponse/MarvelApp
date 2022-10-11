package com.example.marvelapp.di

import com.marvel.data.network.MarvelApiClient
import com.marvel.data.network.MarvelRemoteDataSource
import com.marvel.data.network.ServicesUrlProvider
import com.marvel.data.network.ServicesUrlProviderImpl
import com.marvel.data.persistence.MarvelPersistence
import com.marvel.domain.repository.MarvelRepository
import com.marvel.data.repository.MarvelRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/v1/public/characters/1011334/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesMarvelApiClient(retrofit: Retrofit): MarvelApiClient {
        return retrofit.create(MarvelApiClient::class.java)
    }

    @Singleton
    @Provides
    fun providesMarvelRepository(
        marvelDataSource: MarvelRemoteDataSource,
        marvelPersistence: MarvelPersistence
    ): MarvelRepository {
        return MarvelRepositoryImpl(
            marvelDataSource = marvelDataSource,
            marvelPersistence = marvelPersistence
        )
    }

    @Singleton
    @Provides
    fun providesServicesUrlProvider(
        servicesUrlProviderImpl: ServicesUrlProviderImpl
    ): ServicesUrlProvider {
        return servicesUrlProviderImpl
    }

}