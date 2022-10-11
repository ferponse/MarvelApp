package com.marvel.ui.adapter.item

import com.marvel.ui.model.CharacterUIModel

sealed class MarvelCharacterHomeItem {

    data class MarvelCharacterHomeNormalItem(
        val itemUIModel: CharacterUIModel,
        val onClickItemListener: ((characterUIModel: CharacterUIModel) -> Unit)? = null
    ): MarvelCharacterHomeItem()

    object LoadingNextCharactersPageItem: MarvelCharacterHomeItem()

}