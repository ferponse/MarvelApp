package com.example.marvelapp

import android.app.Application
import com.example.marvelapp.di.ApplicationComponent
import com.example.marvelapp.di.DaggerApplicationComponent
import com.marvel.ui.di.MarvelComponent
import com.marvel.ui.di.MarvelComponentProvider

class MarvelApplication:
    Application(),
    MarvelComponentProvider
{

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
            .builder()
            .marvelApplication(this)
            .build()
            .apply {
                inject(this@MarvelApplication)
            }

    }

    override fun provideMarvelComponent(): MarvelComponent {
        return applicationComponent.plusMarvelComponentBuilder().build()
    }

}