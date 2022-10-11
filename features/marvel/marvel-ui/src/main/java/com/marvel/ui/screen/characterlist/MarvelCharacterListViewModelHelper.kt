package com.marvel.ui.screen.characterlist

import com.marvel.ui.model.CharacterUIModel
import javax.inject.Inject

class MarvelCharacterListViewModelHelper @Inject constructor() {

    fun combineCharacterUIModels(oldList: List<CharacterUIModel>, newList: List<CharacterUIModel>): List<CharacterUIModel> =
        mutableListOf<CharacterUIModel>().apply {
            addAll(oldList)
            addAll(newList)
        }

}