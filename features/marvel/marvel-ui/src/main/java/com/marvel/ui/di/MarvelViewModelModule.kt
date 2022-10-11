package com.marvel.ui.di

import androidx.lifecycle.ViewModel
import com.marvel.common_ui.di.ViewModelKey
import com.marvel.ui.screen.characterdetail.MarvelCharacterDetailViewModel
import com.marvel.ui.screen.characterlist.MarvelCharacterListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MarvelViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MarvelCharacterListViewModel::class)
    fun bindMarvelCharacterListViewModel(viewModel: MarvelCharacterListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MarvelCharacterDetailViewModel::class)
    fun bindMarvelCharacterDetailViewModel(viewModel: MarvelCharacterDetailViewModel): ViewModel

}