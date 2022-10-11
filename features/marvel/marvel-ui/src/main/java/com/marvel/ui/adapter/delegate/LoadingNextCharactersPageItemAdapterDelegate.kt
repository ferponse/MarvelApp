package com.marvel.ui.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.marvel.ui.adapter.item.MarvelCharacterHomeItem
import com.marvel.ui.databinding.LoadingNextCharactersPageItemBinding

internal class LoadingNextCharactersPageItemAdapterDelegate : AdapterDelegate<List<MarvelCharacterHomeItem>>() {

    override fun isForViewType(items: List<MarvelCharacterHomeItem>, position: Int) =
        items[position] is MarvelCharacterHomeItem.LoadingNextCharactersPageItem

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = LoadingNextCharactersPageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarvelCharacterHomeNormalItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        items: List<MarvelCharacterHomeItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as MarvelCharacterHomeNormalItemViewHolder).bind(
            items[position] as MarvelCharacterHomeItem.LoadingNextCharactersPageItem
        )
    }

    internal class MarvelCharacterHomeNormalItemViewHolder(
        private val binding: LoadingNextCharactersPageItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MarvelCharacterHomeItem.LoadingNextCharactersPageItem) {

        }
    }
}
