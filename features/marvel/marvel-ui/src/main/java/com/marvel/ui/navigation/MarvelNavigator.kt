package com.marvel.ui.navigation

import androidx.navigation.NavController
import com.marvel.ui.model.CharacterUIModel
import com.marvel.ui.screen.characterdetail.MarvelCharacterDetailFragment

interface MarvelNavigator {
    fun navigateToCharacterDetailFragment(
        navController: NavController,
        characterUIModel: CharacterUIModel
    )

    fun provideCharacterDetailFragmentArgs(fragment: MarvelCharacterDetailFragment): CharacterUIModel
}