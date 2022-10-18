package com.marvel.data.di

import com.marvel.data.network.MarvelApiClient
import com.marvel.data.network.MarvelRemoteDataSource
import com.marvel.data.network.ServicesUrlProvider
import com.marvel.data.network.ServicesUrlProviderImpl
import com.marvel.data.persistence.MarvelPersistence
import com.marvel.data.repository.MarvelRepositoryImpl
import com.marvel.domain.repository.MarvelRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object MarvelDataModule {

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