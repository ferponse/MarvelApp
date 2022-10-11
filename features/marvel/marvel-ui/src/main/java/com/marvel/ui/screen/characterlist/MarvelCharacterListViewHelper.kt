package com.marvel.ui.screen.characterlist

import com.marvel.ui.adapter.item.MarvelCharacterHomeItem
import com.marvel.ui.model.CharacterUIModel
import javax.inject.Inject

class MarvelCharacterListViewHelper @Inject constructor() {

    fun getCharacterAdapterItems(
        characterUIModel: List<CharacterUIModel>,
        onCharacterClick: ((characterUIModel: CharacterUIModel) -> Unit)? = null,
        loading: Boolean = false
    ): List<MarvelCharacterHomeItem> =
        mutableListOf<MarvelCharacterHomeItem>().apply {
            addAll(characterUIModel.map { resultUIItem ->
                MarvelCharacterHomeItem.MarvelCharacterHomeNormalItem(
                    itemUIModel = resultUIItem,
                    onClickItemListener = onCharacterClick
                )
            })
            if (loading) add(MarvelCharacterHomeItem.LoadingNextCharactersPageItem)
        }

}