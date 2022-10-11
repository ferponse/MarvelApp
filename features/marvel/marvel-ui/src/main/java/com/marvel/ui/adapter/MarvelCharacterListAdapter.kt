package com.marvel.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.marvel.ui.adapter.delegate.MarvelCharacterHomeNormalItemAdapterDelegate
import com.marvel.ui.adapter.item.MarvelCharacterHomeItem
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.marvel.ui.adapter.delegate.LoadingNextCharactersPageItemAdapterDelegate

internal class MarvelCharacterListAdapter :
    AsyncListDifferDelegationAdapter<MarvelCharacterHomeItem>(DIFF_UTIL_CALLBACK) {

    companion object {
        private val DIFF_UTIL_CALLBACK: DiffUtil.ItemCallback<MarvelCharacterHomeItem> =
            object : DiffUtil.ItemCallback<MarvelCharacterHomeItem>() {
                override fun areItemsTheSame(oldItem: MarvelCharacterHomeItem, newItem: MarvelCharacterHomeItem) =
                    when {
                        oldItem is MarvelCharacterHomeItem.MarvelCharacterHomeNormalItem &&
                                newItem is MarvelCharacterHomeItem.MarvelCharacterHomeNormalItem -> areContentItemsTheSame(oldItem, newItem)
                        else -> oldItem.javaClass.simpleName === newItem.javaClass.simpleName
                }

                private fun areContentItemsTheSame(
                    oldItem: MarvelCharacterHomeItem.MarvelCharacterHomeNormalItem,
                    newItem: MarvelCharacterHomeItem.MarvelCharacterHomeNormalItem
                ) = oldItem.itemUIModel.id == newItem.itemUIModel.id

                override fun areContentsTheSame(
                    oldItem: MarvelCharacterHomeItem,
                    newItem: MarvelCharacterHomeItem
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    init {
        delegatesManager.addDelegate(MarvelCharacterHomeNormalItemAdapterDelegate())
        delegatesManager.addDelegate(LoadingNextCharactersPageItemAdapterDelegate())
    }
}
