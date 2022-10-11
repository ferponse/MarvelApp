package com.marvel.ui.di

import com.marvel.ui.screen.characterdetail.MarvelCharacterDetailFragment
import com.marvel.ui.screen.characterlist.MarvelCharacterListFragment
import dagger.Subcomponent

@MarvelScope
@Subcomponent(modules = [MarvelViewModelModule::class])
interface MarvelComponent {
    fun inject(fragment: MarvelCharacterListFragment)
    fun inject(fragment: MarvelCharacterDetailFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): MarvelComponent
    }
}

interface MarvelComponentProvider {
    fun provideMarvelComponent(): MarvelComponent
}
