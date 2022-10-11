package com.marvel.ui.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.marvel.ui.adapter.item.MarvelCharacterHomeItem
import com.marvel.ui.databinding.MarvelCharacterHomeNormalItemBinding

internal class MarvelCharacterHomeNormalItemAdapterDelegate : AdapterDelegate<List<MarvelCharacterHomeItem>>() {

    override fun isForViewType(items: List<MarvelCharacterHomeItem>, position: Int) =
        items[position] is MarvelCharacterHomeItem.MarvelCharacterHomeNormalItem

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = MarvelCharacterHomeNormalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarvelCharacterHomeNormalItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        items: List<MarvelCharacterHomeItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as MarvelCharacterHomeNormalItemViewHolder).bind(
            items[position] as MarvelCharacterHomeItem.MarvelCharacterHomeNormalItem
        )
    }

    internal class MarvelCharacterHomeNormalItemViewHolder(
        private val binding: MarvelCharacterHomeNormalItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MarvelCharacterHomeItem.MarvelCharacterHomeNormalItem) {
            with(binding) {
                name.text = item.itemUIModel.name
                itemContainer.setOnClickListener { item.onClickItemListener?.invoke(item.itemUIModel) }
                characterImage.load(item.itemUIModel.thumbnail.thumbnailURL)
            }
        }
    }
}
