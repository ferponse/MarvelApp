package com.example.marvelapp.di

import com.example.marvelapp.MarvelApplication
import com.marvel.common_ui.di.ViewModelFactoryModule
import com.marvel.ui.di.MarvelComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ViewModelFactoryModule::class, NetworkModule::class])
interface ApplicationComponent: AndroidInjector<MarvelApplication> {

    fun plusMarvelComponentBuilder(): MarvelComponent.Builder

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent

        @BindsInstance
        fun marvelApplication(marvelApplication: MarvelApplication): Builder
    }
}